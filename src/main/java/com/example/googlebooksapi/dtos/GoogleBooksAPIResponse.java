package com.example.googlebooksapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class GoogleBooksAPIResponse {
    private String kind;
    private int totalItems;
    private ArrayList<BookResponse> items = new ArrayList<>();
}
