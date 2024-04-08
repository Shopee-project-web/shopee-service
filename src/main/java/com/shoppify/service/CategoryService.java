package com.shoppify.service;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CategoryRequest;

public interface CategoryService {

   CommonResponse findAllCategory();

   CommonResponse createCategory(CategoryRequest request);

   CommonResponse getCategoryById(Long id);

   CommonResponse updateCategory(Long id, CategoryRequest request);


}
