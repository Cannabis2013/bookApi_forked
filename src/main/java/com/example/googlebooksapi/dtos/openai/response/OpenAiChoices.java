package com.example.googlebooksapi.dtos.openai.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpenAiChoices {
    private String text;
}
