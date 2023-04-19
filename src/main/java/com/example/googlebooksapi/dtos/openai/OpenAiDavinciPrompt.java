package com.example.googlebooksapi.dtos.openai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpenAiDavinciPrompt {
    public OpenAiDavinciPrompt(int max_tokens, String prompt) {
        this.max_tokens = max_tokens;
        this.prompt = prompt;
    }

    private final String model = "text-davinci-003";
    private int max_tokens = 100;
    private String prompt;
}
