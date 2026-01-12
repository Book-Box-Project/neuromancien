package com.bookbox.neuromancien.book.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bookbox.neuromancien.book.dto.BookDetailResponse;
import com.bookbox.neuromancien.book.dto.BookSearchResult;
import com.bookbox.neuromancien.book.dto.GoogleBookItem;
import com.bookbox.neuromancien.book.dto.GoogleBooksResponse;
import com.bookbox.neuromancien.book.mapper.GoogleBookMapper;
import com.bookbox.neuromancien.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleBooksService {

    private static final String VOLUMES_ENDPOINT = "/volumes";

    private static final String PARAM_QUERY = "q";
    private static final String PARAM_PRINT_TYPE = "printType";
    private static final String VALUE_PRINT_TYPE = "books";
    private static final String PARAM_MAX_RESULTS = "maxResults";
    private static final String PARAM_API_KEY = "key";

    private static final int DEFAULT_MAX_RESULTS = 40;

    private final RestTemplate restTemplate;
    private final GoogleBookMapper googleBookMapper;

    @Value("${google.books.api.url}")
    private String apiUrl;

    @Value("${google.books.api.key}")
    private String apiKey;

    public List<BookSearchResult> searchBooks(String query, Integer maxResults) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(apiUrl + VOLUMES_ENDPOINT)
                    .queryParam(PARAM_QUERY, query)
                    .queryParam(PARAM_PRINT_TYPE, VALUE_PRINT_TYPE)
                    .queryParam(PARAM_MAX_RESULTS, maxResults != null ? maxResults : DEFAULT_MAX_RESULTS)
                    .queryParam(PARAM_API_KEY, apiKey)
                    .build(false)
                    .toUriString();

            GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class);

            if (response == null || response.getItems() == null) {
                return Collections.emptyList();
            }

            return response.getItems().stream()
                    .map(googleBookMapper::mapToSearchResult)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error searching books: {}", e.getMessage());
            throw new RuntimeException("Failed to search books from Google Books API", e);
        }
    }

    public BookDetailResponse getBookById(String externalId) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(apiUrl + VOLUMES_ENDPOINT + "/" + externalId)
                    .queryParam(PARAM_API_KEY, apiKey)
                    .toUriString();

            GoogleBookItem item = restTemplate.getForObject(url, GoogleBookItem.class);

            if (item == null) {
                throw new ResourceNotFoundException("Book not found with id: " + externalId);
            }

            return googleBookMapper.mapToDetailResponse(item);

        } catch (Exception e) {
            log.error("Error fetching book: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch book from Google Books API", e);
        }
    }
}