package com.example.urlshortener.controller;

import com.example.urlshortener.dto.ReturnStats;
import com.example.urlshortener.dto.ShortenRequest;
import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/shorten")
    public UrlMapping convertToCode(@Valid @RequestBody ShortenRequest request) {
        return urlService.convertToCode(request.url());
    }

    @GetMapping("/{code}")
    public ResponseEntity<String> getOriginalUrlAndIncrementClicks(@PathVariable("code") String code) {
        String targetUrl = urlService.getOriginalUrlAndIncrementClicks(code);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(targetUrl)).build();
    }

    @GetMapping("/api/status/{code}")
    public ResponseEntity<ReturnStats> getNoClicksForUrl(@PathVariable("code") String code) {
        ReturnStats urlStats = urlService.getStats(code);
        return ResponseEntity.ok(urlStats);
    }
}
