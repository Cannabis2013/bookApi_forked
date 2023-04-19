package com.example.googlebooksapi.services;

import com.example.googlebooksapi.dtos.GoogleBooksAPIResponse;
import com.example.googlebooksapi.dtos.BookResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class BookService {

    public ArrayList<BookResponse> booksBySearch(String search) {
        return callGoogleBooksApi(search).block().getItems();
    }


    private Mono<GoogleBooksAPIResponse> callGoogleBooksApi(String query) {
        Mono<GoogleBooksAPIResponse> response = WebClient.create()
                .get()
                .uri("https://www.googleapis.com/books/v1/volumes?q=" + query)
                .retrieve()
                .bodyToMono(GoogleBooksAPIResponse.class);
        return response;
    }
}
