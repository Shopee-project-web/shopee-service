package com.shoppify.service;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CategoryRequest;
import com.shoppify.dto.payload.request.SubCategoryRequest;

public interface SubCategoryService {

   CommonResponse findAllSubCategory();
   CommonResponse addSubCategory(SubCategoryRequest request);
   CommonResponse getSubCategoryById(Long id);

}
