package com.bookbox.neuromancien.book.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBookItem {
    private String id;
    private VolumeInfo volumeInfo;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String publishedDate;
        private String description;
        private ImageLinks imageLinks;
        private List<String> categories;
        private Integer pageCount;
        private String language;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ImageLinks {
            private String smallThumbnail;
            private String thumbnail;
        }
    }
}