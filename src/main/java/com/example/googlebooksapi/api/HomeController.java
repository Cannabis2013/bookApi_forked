package com.example.googlebooksapi.api;

import com.example.googlebooksapi.dtos.BookResponse;
import com.example.googlebooksapi.dtos.RecommendationRequest;
import com.example.googlebooksapi.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class HomeController {

    private BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public ArrayList<BookResponse> search(@RequestParam String query) {
        return bookService.booksBySearch(query);
    }

    @PostMapping("/recommendation")
    public RecommendationRequest getRecommendation(@RequestBody RecommendationRequest request) {
        // TODO: Implementation
        return request;
    }
}
