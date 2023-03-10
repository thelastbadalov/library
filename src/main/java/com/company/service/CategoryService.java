package com.company.service;

import com.company.model.Category;
import com.company.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    public Category loadCategory(Long categoryId) {

    return repository.findById(categoryId).orElseThrow(()-> new RuntimeException
            ("can not find category with given categoryId"+ categoryId));

    }

public Category findByName(String value){
        return repository.findByName(value)
                .orElseThrow(RuntimeException::new);
}
}
