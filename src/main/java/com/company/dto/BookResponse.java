package com.company.dto;

import com.company.model.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.File;
@Data
@Builder
@AllArgsConstructor
public class BookResponse {
    private Long bookId;
    private Long categoryId;
    private String title;
    private Integer totalPages;
    private String authorName;
    private BookStatus bookStatus;
    private Integer lastPageNumber;
    private String publisher;
    private String image;
    private String imageUrl;
}
