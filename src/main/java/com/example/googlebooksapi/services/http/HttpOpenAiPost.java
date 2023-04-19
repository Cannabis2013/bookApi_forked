package com.example.googlebooksapi.services.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpResponse;

@Service
public class HttpOpenAiPost {
    @Value("${OpenAiToken}")
    private String apiToken;



    public <T,U> Mono<T> fetch(String uri, Class<U> requestDescriptor, U requestData, Class<T> responseDescriptor){
        Mono<T> response = WebClient.create()
                .post()
                .uri(uri)
                .header("Authorization",authHeaderValue())
                .body(Mono.just(requestData), requestDescriptor)
                .retrieve()
                .bodyToMono(responseDescriptor);
        return response;
    }

    private String authHeaderValue(){
        return "Bearer " + apiToken;
    }
}
