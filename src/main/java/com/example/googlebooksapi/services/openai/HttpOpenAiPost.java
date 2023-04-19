package com.example.googlebooksapi.services.openai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpResponse;

@Service
public class HttpOpenAiPost {
    public <T,U> T fetch(String uri, Class<U> requestDescriptor,
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

    private String authHeaderValue(String apiToken){
        return "Bearer " + apiToken;
    }
}
