package com.example.googlebooksapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class RecommendationRequest {
    private ArrayList<Book> books = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Book {
        private String title;
        private ArrayList<String> authors = new ArrayList<>();
    }
}
