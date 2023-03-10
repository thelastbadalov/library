package com.company.api;

import com.company.dto.BookListItemResponse;
import com.company.dto.BookResponse;
import com.company.dto.SaveBookRequest;
import com.company.model.BookStatus;
import com.company.model.CategoryType;
import com.company.service.BookListService;
import com.company.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/app/books")
@RequiredArgsConstructor
public class BookRestController {
private final BookListService service;
private final BookService bookService;


@PostMapping
    public ResponseEntity<BookListItemResponse> saveBook(@RequestBody SaveBookRequest bookRequest){

    return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRequest));
}
@GetMapping("/search")
public ResponseEntity<List<BookResponse>> listBook(@RequestParam(name = "size") int size,
                                                   @RequestParam(name = "page") int page){
return ResponseEntity.ok(service.listBooks(size,page));
}
@GetMapping("/list/{categoryName}")
public ResponseEntity<List<BookResponse>> searchCategoryByName(@PathVariable CategoryType categoryName){
    return ResponseEntity.ok(this.service.searchCategoryByName(categoryName));
}
    @GetMapping("/list/{status}")
    public ResponseEntity<List<BookResponse>> listByStatus(@PathVariable BookStatus  status){
        return ResponseEntity.ok(this.service.searchBookStatus(status)) ;
    }
    @GetMapping("/list/{title}")
    public ResponseEntity<List<BookResponse>> listByTitle(@PathVariable String  title){
        return ResponseEntity.ok(this.service.searchBookTitle(title));
    }
}
