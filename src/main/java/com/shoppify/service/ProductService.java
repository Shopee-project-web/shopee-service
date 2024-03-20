package com.shoppify.service;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.ProductRequest;

public interface ProductService {
   CommonResponse findAllProduct();
   CommonResponse getProductById(Long productId);
   CommonResponse getProductByCategoryId(Long categoryId);
   CommonResponse getProductBySubCategoryId(Long subCategoryId);
   CommonResponse createProduct(ProductRequest productRequest);
}
