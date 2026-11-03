package com.example.urlshortener.dto;

import java.time.LocalDateTime;

public record ReturnStats(String originalUrl,
                          String code,
                          int noClicks,
                          LocalDateTime createdAt) {
}
