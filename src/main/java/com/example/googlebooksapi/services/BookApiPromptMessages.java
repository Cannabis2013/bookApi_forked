package com.example.googlebooksapi.services;

import com.example.googlebooksapi.dtos.openai.OpenAiDavinciPrompt;
import org.springframework.stereotype.Service;

@Service
public class BookApiPromptMessages {
    public OpenAiDavinciPrompt summary(String title, String author, int length){
        var message = String.format("Give me a summary of '%s' by '%s' in no more than %d words",title,author,length);
        return new OpenAiDavinciPrompt(length,message);
    }
}
