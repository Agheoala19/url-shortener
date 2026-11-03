package com.example.urlshortener;

import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.exception.UrlNotFoundException;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @Mock
    private UrlRepository repository;

    @InjectMocks
    private UrlService service;

    @Test
    public void shouldReturnOriginalUrlAndIncrementClicks_WhenCodeExists() {
        String code = "abc1234567";
        UrlMapping mapping = new UrlMapping("https://google.com", code, LocalDateTime.now(), 0);

        when(repository.findByCode(code)).thenReturn(Optional.of(mapping)); //Arrange

        String result = service.getOriginalUrlAndIncrementClicks(code);//Act

        assertEquals("https://google.com", result);//Assert
        assertEquals(1, mapping.getNoClicks());

        verify(repository).save(mapping);
    }

    @Test
    public void shouldThrowUrlNotFoundException_WhenCodeDoesNotExist() {
        String code = "inexistent";
        when(repository.findByCode(code)).thenReturn(Optional.empty());

        assertThrows(UrlNotFoundException.class, () -> {
            service.getOriginalUrlAndIncrementClicks(code);
        });

        verify(repository, never()).save(any());
    }
}
