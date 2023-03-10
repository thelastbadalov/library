package com.company.repository;
import com.company.model.Book;
import com.company.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>{
    Optional<Category> findByName(String categoryName);

}


