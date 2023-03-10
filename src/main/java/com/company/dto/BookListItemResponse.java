package com.company.dto;
import com.company.model.BookStatus;
import com.company.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.File;
@Data
@Builder
@AllArgsConstructor
public class BookListItemResponse {
    private Long bookId;
    private String title;
    private String authorName;
    private Category category;
    private BookStatus bookStatus;
    private Integer lastPageNumber;
    private File image;
    private Integer totalPage;
    private String publisher;
    private String categoryName;
}
