package com.shopee.repository;

import com.shopee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
   List<Product> findProductByCategoryId(long categoryId);

   List<Product> findProductBySubCategoryId(long subCategoryId);

   boolean existsByName(String name);

   List<Product> findAllByNameLike(String name);
}
