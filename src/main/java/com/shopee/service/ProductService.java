package com.shopee.service;

import com.shopee.dto.CommonResponse;

public interface ProductService {
   CommonResponse findAllProduct();

   CommonResponse getProductById(Long productId);

   CommonResponse getProductByCategoryId(Long categoryId);

   CommonResponse getProductBySubCategoryId(Long subCategoryId);

   //   CommonResponse addProduct(ProductRequest productRequest);
   CommonResponse searchProductsByName(String name);

}
