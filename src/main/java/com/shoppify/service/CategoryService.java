package com.shoppify.service;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CategoryRequest;

public interface CategoryService {

   CommonResponse findAllCategory();
   CommonResponse addCategory(CategoryRequest request);

   CommonResponse updateCategory(Long id, CategoryRequest request);

   CommonResponse getCategoryById(Long id);

}
