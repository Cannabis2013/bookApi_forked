package com.example.googlebooksapi.services.googleBooks;

import com.example.googlebooksapi.dtos.googleBooks.response.GoogleBooksAPIResponse;
import com.example.googlebooksapi.dtos.googleBooks.response.BookResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class BookService {

    public ArrayList<BookResponse> booksBySearch(String search) {
        return callGoogleBooksApi(search).block().getItems();
    }

    public BookResponse firstBookBySearch(String search) {
        return callGoogleBooksApi(search).block().getItems().stream().findFirst().orElse(null);
    }

    public String bookDescription(String keyword){
        var response = callGoogleBooksApi(keyword).block();
        BookResponse book;
        try {
            book = response.getItems().stream().findFirst().orElseThrow(Exception::new);
        } catch (Exception e){
            return null;
        }
        return book.getVolumeInfo().getDescription();
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
