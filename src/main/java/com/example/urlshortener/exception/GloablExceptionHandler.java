package com.example.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GloablExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UrlNotFoundException.class)
    public Map<String, String> handleUrlNotFound(UrlNotFoundException ex) {
        return Map.of("error", "URL not found for the provided short code");
    }
}
