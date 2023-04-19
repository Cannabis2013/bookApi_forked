package com.example.googlebooksapi.dtos.openai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenAiResponse
{
    private String id;
    private String object;
    private int created;
    private String model;
    private ArrayList<OpenAiChoices> choices;
}
