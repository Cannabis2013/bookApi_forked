package com.example.googlebooksapi.api;

import com.example.googlebooksapi.dtos.googleBooks.response.BookResponse;
import com.example.googlebooksapi.services.googleBooks.GoogleApiBookService;
import com.example.googlebooksapi.services.openai.OpenAiBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class HomeController {

    private GoogleApiBookService bookService;
    private OpenAiBookService openApiBookService;

    @GetMapping("/")
    public RedirectView welcome()
    {
        return new RedirectView("/swagger-ui/index.html");
    }

    public HomeController(GoogleApiBookService bookService, OpenAiBookService openApiBookService) {
        this.bookService = bookService;
        this.openApiBookService = openApiBookService;
    }

    @GetMapping("/searchByKeyword")
    public ArrayList<BookResponse> searchByKeyword(@RequestParam String query) {
        return bookService.booksByKeyword(query);
    }

    @GetMapping("searchSpecific")
    public BookResponse searchSpecific(String author, String title){
        var book = bookService.book(author,title);
        if(book == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        return book;
    }

    @GetMapping("/recommendationByDescription")
    public List<String> getRecommendation(@RequestParam String author, String title, int maxResults) {
        var description = bookService.bookDescription(author,title);
        var result = openApiBookService.recommendedBooks(description,maxResults);
        if(result == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        return result;
    }

    @GetMapping("/recommendationByBook")
    public List<String> getRecommendationByBook(String author, String title, int maxResults) {
        var result = openApiBookService.recommendedBooks(author,title,maxResults);
        if(result == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        return result;
    }

    @GetMapping("/summary")
    public String getSummaryByBook(String author, String title, int length){
        var result = openApiBookService.bookSummary(author,title,length);
        if(result == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        return result;
    }
}
