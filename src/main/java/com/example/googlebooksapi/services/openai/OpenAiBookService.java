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
    private final HttpOpenAiPost _httpFetch;
    private final BookApiPromptMessages _bookPromptMessages;
    @Value("${OpenAiToken}")
    private String apiToken;

    public OpenAiBookService(HttpOpenAiPost httpFetch, BookApiPromptMessages bookPromptMessages) {
        _httpFetch = httpFetch;
        _bookPromptMessages = bookPromptMessages;
    }

    public List<String> recommendedBooks(String description){
        var prompt = _bookPromptMessages.similarBooks(description, 150);
        String uri = "https://api.openai.com/v1/completions";
        var content = _httpFetch.fetch(uri, OpenAiDavinciPrompt.class,prompt,OpenAiResponse.class,apiToken);
        var text = getText(content);
        return separate(text);
    }

    private String getText(OpenAiResponse content){
        var choices = content.getChoices();
        var first = choices.stream().findFirst().orElse(null);
        return first != null ? first.getText() : null;
    }

    private List<String> separate(String src){
        var strings = new ArrayList<String>();
        var scan = new Scanner(src).useDelimiter(";");
        while (scan.hasNext()){
            var str = scan.next();
            strings.add(str);
        }
        return strings;
    }
}
