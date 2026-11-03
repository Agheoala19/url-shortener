package com.example.urlshortener.dto;


import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ShortenRequest(@NotBlank(message = "URL is mandatory") @URL String url) {
}
