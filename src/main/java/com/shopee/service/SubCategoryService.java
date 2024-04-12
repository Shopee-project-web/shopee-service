package com.shopee.service;

import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.SubCategoryRequest;

public interface SubCategoryService {

   CommonResponse findAllSubCategory();
   CommonResponse createSubCategory(SubCategoryRequest request);
   CommonResponse getSubCategoryById(Long id);
   CommonResponse updateSubcategory(Long id, SubCategoryRequest request);

}
