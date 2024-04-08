package com.shoppify.service;

import com.shoppify.dto.CommonResponse;

public interface ProductService {
   CommonResponse findAllProduct();

   CommonResponse getProductById(Long productId);

   CommonResponse getProductByCategoryId(Long categoryId);

   CommonResponse getProductBySubCategoryId(Long subCategoryId);

   //   CommonResponse addProduct(ProductRequest productRequest);
   CommonResponse searchProductsByName(String name);

}
