package com.bookbox.neuromancien.book.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBookDetailItem {
    private String id;
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private String subtitle;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private List<IndustryIdentifier> industryIdentifiers;
        private Integer pageCount;
        private String printType;
        private List<String> categories;
        private Double averageRating;
        private Integer ratingsCount;
        private String maturityRating;
        private String language;
        private ImageLinks imageLinks;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class IndustryIdentifier {
            private String type; // ISBN_10, ISBN_13
            private String identifier;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ImageLinks {
            private String smallThumbnail;
            private String thumbnail;
            private String small;
            private String medium;
            private String large;
            private String extraLarge;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SaleInfo {
        private String country;
        private String saleability;
        private Boolean isEbook;
    }
}
