package com.bookbox.neuromancien.book.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailResponse {
    private String externalId;
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String description;
    private String coverUrl;
    private List<String> categories;
    private Integer pageCount;
}
