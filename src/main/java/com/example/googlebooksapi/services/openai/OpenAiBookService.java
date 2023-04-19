package com.example.googlebooksapi.services.openai;

import com.example.googlebooksapi.dtos.openai.response.OpenAiResponse;
import com.example.googlebooksapi.dtos.openai.requests.OpenAiDavinciPrompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class OpenAiBookService {
    private final HttpFetch _httpFetch;
    private final BookApiPromptMessages _bookPromptMessages;
    @Value("${OpenAiToken}")
    private String apiToken;

    private final String Uri = "https://api.openai.com/v1/completions";

    public OpenAiBookService(HttpFetch httpFetch, BookApiPromptMessages bookPromptMessages) {
        _httpFetch = httpFetch;
        _bookPromptMessages = bookPromptMessages;
    }

    public List<String> recommendedBooks(String description){
        if(description == null)
            return null;
        var prompt = _bookPromptMessages.similarBooks(description, 150);
        var content = _httpFetch.postRequest(Uri, OpenAiDavinciPrompt.class,prompt,OpenAiResponse.class,apiToken);
        var result = getText(content);
        return formatResult(result);
    }

    private String getText(OpenAiResponse content){
        var choices = content.getChoices();
        var first = choices.stream().findFirst().orElse(null);
        return first != null ? first.getText() : null;
    }

    private List<String> formatResult(String src){
        var strings = new ArrayList<String>();
        var scan = new Scanner(src).useDelimiter(";");
        while (scan.hasNext()){
            var str = scan.next();
            var nStr = str.replace("\n","");
            strings.add(nStr);
        }
        return strings;
    }
}
