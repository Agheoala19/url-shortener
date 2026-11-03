package com.example.urlshortener.service;

import com.example.urlshortener.dto.ReturnStats;
import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.exception.UrlNotFoundException;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class UrlService {
    private UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom random = new SecureRandom();

    private String generateCode() {
        StringBuilder code = new StringBuilder(10);

        for(int i = 0; i < 10; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return code.toString();
    }

    public UrlMapping convertToCode(String url) {
        if(Objects.equals(url, "")) {
            return null;
        }

        String code = generateCode();
        while (repository.existsByCode(code)) {
            code = generateCode();
        }
        return repository.save(new UrlMapping(url, code, LocalDateTime.now(), 0));
    }

    public String getOriginalUrlAndIncrementClicks(String code) {
        Optional<UrlMapping> element = repository.findByCode(code);
        if(element.isPresent()) {
            UrlMapping mapping = element.get();
            mapping.setNoClicks(mapping.getNoClicks() + 1);
            repository.save(mapping);
            return mapping.getUrl();
        } else {
            throw new UrlNotFoundException();
        }
    }

    public ReturnStats getStats(String code) {
        Optional<UrlMapping> element = repository.findByCode(code);
        if(element.isPresent()) {
            UrlMapping mapping = element.get();
            return new ReturnStats(mapping.getUrl(), mapping.getCode(), mapping.getNoClicks(), mapping.getDateTime());
        } else {
            throw new UrlNotFoundException();
        }
    }
}
