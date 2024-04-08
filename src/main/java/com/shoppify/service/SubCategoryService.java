package com.shoppify.service;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.SubCategoryRequest;

public interface SubCategoryService {

   CommonResponse findAllSubCategory();
   CommonResponse createSubCategory(SubCategoryRequest request);
   CommonResponse getSubCategoryById(Long id);
   CommonResponse updateSubcategory(Long id, SubCategoryRequest request);

}
