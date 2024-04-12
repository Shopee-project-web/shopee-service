package com.shopee.service;

import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.CategoryRequest;

public interface CategoryService {

   CommonResponse findAllCategory();

   CommonResponse createCategory(CategoryRequest request);

   CommonResponse getCategoryById(Long id);

   CommonResponse updateCategory(Long id, CategoryRequest request);


}
