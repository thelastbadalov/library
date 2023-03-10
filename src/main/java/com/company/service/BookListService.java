package com.company.service;

import com.company.dto.BookResponse;
import com.company.model.Book;
import com.company.model.BookStatus;
import com.company.model.Category;
import com.company.model.CategoryType;
import com.company.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookListService {


private final BookRepository repository;

   private final CategoryService categoryService;

    public List<BookResponse> listBooks( int size,int page){
        return repository.findAll(PageRequest.of(size,page))
                .getContent().
                stream().
                map(BookListService::convertResponse)
                .collect(Collectors.toList());
    }

public List<BookResponse> searchCategoryByName(CategoryType categoryType){
    Category category=categoryService.findByName(categoryType.getValue());
    return category.getBooks().stream().map(BookListService::convertResponse).collect(Collectors.toList());
}
private static BookResponse convertResponse(Book model){
        return BookResponse.builder()
                .authorName(model.getAuthorName())
                .title(model.getTitle())
                .imageUrl(model.getImage().getImageUrl())
                .build();
}

public List<BookResponse> searchBookStatus(BookStatus bookStatus){
        return repository.searchBookByStatus(bookStatus).stream().map(
                each->
                    BookResponse.builder()
                            .bookId(each.getId())
                            .imageUrl(each.getImage().getImageUrl())
                            .build()).collect(Collectors.toList());
}

    public List<BookResponse> searchBookTitle(String title){
        return repository.findByTitle(title).stream().map(
                each->
                        BookResponse.builder()
                                .bookId(each.getId())
                                .imageUrl(each.getImage().getImageUrl())
                                .build()).collect(Collectors.toList());
    }

}
