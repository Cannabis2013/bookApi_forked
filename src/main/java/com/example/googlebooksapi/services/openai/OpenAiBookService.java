package com.example.googlebooksapi.services.openai;

import com.example.googlebooksapi.dtos.openai.response.OpenAiChoices;
import com.example.googlebooksapi.dtos.openai.response.OpenAiResponse;
import com.example.googlebooksapi.dtos.openai.requests.OpenAiDavinciPrompt;
import com.example.googlebooksapi.services.http.HttpOpenAiPost;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class OpenAiBookService {
    private final HttpOpenAiPost _httpFetch;
    private final String Uri = "https://api.openai.com/v1/completions";
    private final BookApiPromptMessages _bookPromptMessages;

    public OpenAiBookService(HttpOpenAiPost httpFetch, BookApiPromptMessages bookPromptMessages) {
        _httpFetch = httpFetch;
        _bookPromptMessages = bookPromptMessages;
    }

    public List<String> recommendedBooks(String description){
        var prompt = _bookPromptMessages.similarBooks(description, 150);
        var response = _httpFetch.fetch(Uri, OpenAiDavinciPrompt.class,prompt,OpenAiResponse.class);
        var content = response.block();
        if(content == null)
            return null;
        var choices = content.getChoices();
        var first = choices.stream().findFirst().orElse(null);
        if(first == null)
            return null;
        return separate(first.getText());
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
