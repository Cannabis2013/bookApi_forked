package com.example.googlebooksapi.services.googleBooks;

import org.springframework.stereotype.Service;

@Service
public class GoogleBooksApiInfo {
    private final String searchUrl = "https://www.googleapis.com/books/v1/volumes?q=";

    public String uri(String keyword){
        return searchUrl + keyword;
    }

    public String uri(String author, String title) {
        var query = String.format("inauthor:%s+intitle:%s",author,title);
        return searchUrl + query;
    }
}
