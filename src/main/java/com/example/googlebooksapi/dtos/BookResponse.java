package com.example.googlebooksapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class BookResponse {
    private String id;
    private String etag;
    private BookVolumeInfo volumeInfo;


    @Getter
    @Setter
    @NoArgsConstructor
    private static class BookVolumeInfo {
        private String title;
        private ArrayList<String> authors = new ArrayList<>();
        private String publisher;
        private String publishedDate;
        private String description;
        private ArrayList<String> categories = new ArrayList<>();
        private double averageRating;
        private double ratingsCount;
        private String maturityRating;
        private String language;
    }
}
