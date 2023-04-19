package com.example.googlebooksapi.services.http;

public interface IHttpRequest {
    <T, U> T postRequest(String uri, Class<U> requestDescriptor,
                         U requestData,
                         Class<T> responseDescriptor,
                         String apiKey);

    <T> T getRequest(String uri, Class<T> descriptor);
}
