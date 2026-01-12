package com.bookbox.neuromancien.book.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponse {
    private Long totalItems;
    private List<GoogleBookItem> items;
}
