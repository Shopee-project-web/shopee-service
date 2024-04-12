package com.shopee.repository;

import com.shopee.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>  {
   boolean existsByName(String name);
}
