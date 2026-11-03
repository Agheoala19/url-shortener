package com.example.urlshortener.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String url;
    @Column(length = 10, nullable = false, unique = true)
    private String code;
    private LocalDateTime dateTime;
    private int noClicks;

    public UrlMapping(String url, String code, LocalDateTime dateTime, int noClicks) {
        this.url = url;
        this.code = code;
        this.dateTime = dateTime;
        this.noClicks = noClicks;
    }

    public UrlMapping() {
    }

    public String getUrl() {
        return url;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getNoClicks() {
        return noClicks;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setNoClicks(int noClicks) {
        this.noClicks = noClicks;
    }
}
