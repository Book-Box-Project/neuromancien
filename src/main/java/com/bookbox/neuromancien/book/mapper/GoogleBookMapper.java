package com.bookbox.neuromancien.book.mapper;

import org.springframework.stereotype.Component;

import com.bookbox.neuromancien.book.dto.BookDetailResponse;
import com.bookbox.neuromancien.book.dto.BookSearchResult;
import com.bookbox.neuromancien.book.dto.GoogleBookItem;

@Component
public class GoogleBookMapper {

    public BookSearchResult mapToSearchResult(GoogleBookItem item) {
        GoogleBookItem.VolumeInfo info = item.getVolumeInfo();

        return BookSearchResult.builder()
                .externalId(item.getId())
                .title(info.getTitle())
                .authors(info.getAuthors())
                .publishedDate(info.getPublishedDate())
                .coverUrl(info.getImageLinks() != null ? info.getImageLinks().getThumbnail() : null)
                .categories(info.getCategories())
                .build();
    }

    public BookDetailResponse mapToDetailResponse(GoogleBookItem item) {
        GoogleBookItem.VolumeInfo info = item.getVolumeInfo();

        return BookDetailResponse.builder()
                .externalId(item.getId())
                .title(info.getTitle())
                .authors(info.getAuthors())
                .publishedDate(info.getPublishedDate())
                .description(info.getDescription())
                .coverUrl(info.getImageLinks() != null ? info.getImageLinks().getThumbnail() : null)
                .categories(info.getCategories())
                .pageCount(info.getPageCount())
                .build();
    }
}
