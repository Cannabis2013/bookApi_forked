package com.example.googlebooksapi.services.openai;

import com.example.googlebooksapi.dtos.openai.requests.OpenAiDavinciPrompt;
import org.springframework.stereotype.Service;

@Service
public class BookApiPromptMessages {
    public OpenAiDavinciPrompt summary(String author, String title, int length){
        var message = String.format("""
                Give me a summary of the book '%s' by '%s' in no more than %d words
                """,title,author,length);
        return new OpenAiDavinciPrompt(length,message);
    }

    public OpenAiDavinciPrompt similarBooks(String description, int length){
        var messages = String.format("""
                Please provide a list of books which description is similar to the following description:
                
                '%s'
                
                Each element in the list should be separated by a semicolon and should be of no more than 5 elements.
                
                An example of format:
                
                    1. Vesten mod vesten by Rune Lykkeberg;2. Eliternes triumf by lars Olsen;
                """,description,length);
        return new OpenAiDavinciPrompt(length,messages);
    }
}
