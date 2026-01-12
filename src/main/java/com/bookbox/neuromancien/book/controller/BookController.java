package com.bookbox.neuromancien.book.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookbox.neuromancien.book.dto.BookDetailResponse;
import com.bookbox.neuromancien.book.dto.BookSearchResult;
import com.bookbox.neuromancien.book.service.GoogleBooksService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final GoogleBooksService googleBooksService;

    @GetMapping()
    public ResponseEntity<List<BookSearchResult>> search(
            @RequestParam String query,
            @RequestParam(required = false) Integer maxResults) {
        List<BookSearchResult> results = googleBooksService.searchBooks(query, maxResults);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailResponse> getBook(@PathVariable String id) {
        BookDetailResponse book = googleBooksService.getBookById(id);
        return ResponseEntity.ok(book);
    }

}