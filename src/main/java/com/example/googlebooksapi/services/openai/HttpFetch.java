package com.example.googlebooksapi.services.openai;

import com.example.googlebooksapi.dtos.googleBooks.response.GoogleBooksAPIResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HttpFetch {
    public <T,U> T postRequest(String uri, Class<U> requestDescriptor,
                               U requestData,
                               Class<T> responseDescriptor,
                               String apiKey){
        var response = WebClient.create()
                .post()
                .uri(uri)
                .header("Authorization",authHeaderValue(apiKey))
                .body(Mono.just(requestData), requestDescriptor)
                .retrieve()
                .bodyToMono(responseDescriptor);
        return response.block();
    }

    public <T> T getRequest(String uri,Class<T> descriptor){
        var response = WebClient.create()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(descriptor);
        return response.block();
    }

    private String authHeaderValue(String apiToken){
        return "Bearer " + apiToken;
    }
}