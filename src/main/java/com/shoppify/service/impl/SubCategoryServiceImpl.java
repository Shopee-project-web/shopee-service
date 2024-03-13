package com.shoppify.service.impl;

import com.shoppify.converter.CategoryConverter;
import com.shoppify.converter.SubCategoryConverter;
import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CategoryRequest;
import com.shoppify.dto.payload.request.SubCategoryRequest;
import com.shoppify.dto.payload.response.CategoryResponse;
import com.shoppify.dto.payload.response.SubCategoryResponse;
import com.shoppify.entity.Category;
import com.shoppify.entity.SubCategory;
import com.shoppify.repository.CategoryRepository;
import com.shoppify.repository.SubCategoryRepository;
import com.shoppify.service.CategoryService;
import com.shoppify.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
   private final SubCategoryRepository subCategoryRepository;
   private final SubCategoryConverter subCategoryConverter;

   @Override
   public CommonResponse findAllSubCategory() {


      CommonResponse commonResponse = new CommonResponse();

      List<SubCategory> subCategoryList = subCategoryRepository.findAll();


      if (subCategoryList.isEmpty()) {

         commonResponse.setData(null);
         commonResponse.setMessage("SubCategories not found");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
      } else {
         List<SubCategoryResponse> subCategoryResponseList = subCategoryConverter.toListDto(subCategoryList);

         commonResponse.setData(subCategoryResponseList);
         commonResponse.setMessage("Accessed the subCategories successfully");
         commonResponse.setStatusCode(HttpStatus.OK);
      }
      return commonResponse;
   }



   @Override
   public CommonResponse addSubCategory(SubCategoryRequest request) {

      if (subCategoryRepository.existsByName(request.getName())) {

         return CommonResponse.builder()
                 .data(null)
                 .message("SubCategory with name " + request.getName() + " already exists")
                 .statusCode(HttpStatus.BAD_REQUEST).build();
      }

      SubCategory subCategory = subCategoryConverter.toEntity(request);

      subCategoryRepository.save(subCategory);

      SubCategoryResponse subCategoryResponse = subCategoryConverter.toDto(subCategory);

      return CommonResponse.builder()
              .data(subCategoryResponse)
              .message("Add SubCategory successfully")
              .statusCode(HttpStatus.CREATED)
              .build();
   }


   @Override
   public CommonResponse getSubCategoryById(Long id) {
      try {
         Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);

         if (optionalSubCategory.isPresent()) {

            SubCategory subCategory = optionalSubCategory.get();
            SubCategoryResponse subCategoryResponse = subCategoryConverter.toDto(subCategory);

            return CommonResponse.builder()
                    .data(subCategoryResponse)
                    .message("SubCategory retrieved successfully")
                    .statusCode(HttpStatus.OK)
                    .build();
         } else {

            return CommonResponse.builder()
                    .data(null)
                    .message("SubCategory not found")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }
      } catch (Exception e) {

         return CommonResponse.builder()
                 .data(null)
                 .message("Error retrieving SubCategory: " + e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }
}
