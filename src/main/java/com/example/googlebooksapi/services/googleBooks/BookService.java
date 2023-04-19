package com.example.googlebooksapi.services.googleBooks;

import com.example.googlebooksapi.dtos.googleBooks.response.BookResponse;
import com.example.googlebooksapi.dtos.googleBooks.response.GoogleBooksAPIResponse;
import com.example.googlebooksapi.services.openai.HttpFetch;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class BookService {
    private final HttpFetch _http;
    private final GoogleBooksApiInfo _googleBooksApiInfo;

    public BookService(HttpFetch http, GoogleBooksApiInfo googleBooksApiInfo) {
        _http = http;
        _googleBooksApiInfo = googleBooksApiInfo;
    }

    public ArrayList<BookResponse> booksByKeyword(String keyword) {
        var response = _http.getRequest(_googleBooksApiInfo.uri(keyword), GoogleBooksAPIResponse.class);
        return response.getItems();
    }

    public BookResponse book(String author, String title){
        var uri = _googleBooksApiInfo.uri(author,title);
        var response = _http.getRequest(uri,GoogleBooksAPIResponse.class);
        return response.getItems().stream().findFirst().orElse(null);
    }

    public BookResponse firstBookBySearch(String keyword) {
        var response = _http.getRequest(_googleBooksApiInfo.uri(keyword), GoogleBooksAPIResponse.class);
        return response.getItems().stream().findFirst().orElse(null);
    }

    public String bookDescription(String author, String title){
        var uri = _googleBooksApiInfo.uri(author,title);
        var response = _http.getRequest(uri,GoogleBooksAPIResponse.class);
        BookResponse book = response.getItems().stream().findFirst().orElse(null);
        return book != null ? book.getVolumeInfo().getDescription() : null;
    }
}
