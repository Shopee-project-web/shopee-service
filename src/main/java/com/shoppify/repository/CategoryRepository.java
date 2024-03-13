package com.shoppify.repository;

import com.shoppify.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
   boolean existsByName(String name);
}
