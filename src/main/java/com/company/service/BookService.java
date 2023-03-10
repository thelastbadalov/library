package com.company.service;

import com.company.dto.BookListItemResponse;
import com.company.dto.SaveBookRequest;
import com.company.model.Book;
import com.company.model.Category;
import com.company.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
private final BookRepository bookRepository;
private final CategoryService categoryService;


@Transactional
public BookListItemResponse saveBook(SaveBookRequest request){
    Category category=categoryService.loadCategory(
            request.getCategoryId());
    Book book=Book.builder()
            .category(category)
            .status(request.getBookStatus())
            .title(request.getTitle())
            .lastPageNumber(request.getLastPageNumber())
            .totalPages(request.getTotalPages())
            .authorName(request.getAuthorName())
            .build();
Book fromDb=bookRepository.save(book);



return BookListItemResponse.builder()
        .categoryName(book.getCategory().getName())
        .bookId(fromDb.getId())
        .bookStatus(fromDb.getStatus())
        .authorName(fromDb.getAuthorName())
        .publisher(fromDb.getPublisher())
        .totalPage(fromDb.getTotalPages())
        .lastPageNumber(fromDb.getLastPageNumber())
        .title(fromDb.getTitle())
        .build();
}


}
