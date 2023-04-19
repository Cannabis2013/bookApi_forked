package com.example.googlebooksapi.api;

import com.example.googlebooksapi.dtos.googleBooks.response.BookResponse;
import com.example.googlebooksapi.services.googleBooks.BookService;
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

    private BookService bookService;
    private OpenAiBookService openApiBookService;

    @GetMapping("/")
    public RedirectView welcome()
    {
        return new RedirectView("/swagger-ui/index.html");
    }

    public HomeController(BookService bookService, OpenAiBookService openApiBookService) {
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

    @GetMapping("/recommendation")
    public List<String> getRecommendation(@RequestParam String author, String title) {
        var description = bookService.bookDescription(author,title);
        var result = openApiBookService.recommendedBooks(description);
        if(result == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        return result;
    }
}
