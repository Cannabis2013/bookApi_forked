package com.example.googlebooksapi.services;

import com.example.googlebooksapi.dtos.openai.OpenAiDavinciPrompt;
import com.example.googlebooksapi.services.http.HttpOpenAiPost;

public class OpenAICompletePrompt {
    private final HttpOpenAiPost _httpFetch;
    private final String Uri = "https://api.openai.com/v1/completions";
    private final BookApiPromptMessages _bookPromptMessages;

    public OpenAICompletePrompt(HttpOpenAiPost httpFetch, BookApiPromptMessages bookPromptMessages) {
        _httpFetch = httpFetch;
        _bookPromptMessages = bookPromptMessages;
    }

    public <T> T prompt(){
        var prompt = _bookPromptMessages.summary("Fateful Triangle","Noam Chomsky", 150);
        var response = _httpFetch.fetch(Uri, OpenAiDavinciPrompt.class,prompt,null);
        return null;
    }
}
