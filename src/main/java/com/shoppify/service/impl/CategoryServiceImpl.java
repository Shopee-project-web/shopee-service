package com.shoppify.service.impl;

import com.shoppify.converter.CategoryConverter;
import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CategoryRequest;
import com.shoppify.dto.payload.response.CategoryResponse;
import com.shoppify.entity.Category;
import com.shoppify.repository.CategoryRepository;
import com.shoppify.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
   private final CategoryRepository categoryRepository;
   private final CategoryConverter categoryConverter;

   @Override
   public CommonResponse findAllCategory() {
      CommonResponse commonResponse = new CommonResponse();

      List<Category> categoryList = categoryRepository.findAll();

      if (categoryList.isEmpty()) {

         commonResponse.setData(null);
         commonResponse.setMessage("Categories not found");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
      } else {
         List<CategoryResponse> categoryResponseList = categoryConverter.toDtoCategoryList(categoryList);

         commonResponse.setData(categoryResponseList);
         commonResponse.setMessage("Accessed the categories successfully");
         commonResponse.setStatusCode(HttpStatus.OK);
      }
      return commonResponse;
   }

   @Override
   public CommonResponse addCategory(CategoryRequest request) {

      if (categoryRepository.existsByName(request.getName())) {

         return CommonResponse.builder()
                 .data(null)
                 .message("Category with name " + request.getName() + " already exists")
                 .statusCode(HttpStatus.BAD_REQUEST).build();
      }

      Category category = categoryConverter.toEntityCategory(request);
      categoryRepository.save(category);

      CategoryResponse categoryResponse = categoryConverter.toDtoCategory(category);

      return CommonResponse.builder()
              .data(categoryResponse)
              .message("Add category successfully")
              .statusCode(HttpStatus.CREATED)
              .build();
   }

   @Override
   public CommonResponse updateCategory(Long id, CategoryRequest request) {
      try {
         Category existingCategory = categoryRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Category not found"));

         Category updatedCategory = categoryConverter.toEntityCategory(request);

         existingCategory.setName(updatedCategory.getName());
         existingCategory.setShow(updatedCategory.isShow());

         categoryRepository.save(existingCategory);

         return CommonResponse.builder()
                 .data(categoryConverter.toDtoCategory(existingCategory))
                 .message("Category updated successfully")
                 .statusCode(HttpStatus.OK).build();
      } catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Error updating category: " + e.getMessage())
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build();
      }
   }

   @Override
   public CommonResponse getCategoryById(Long id) {
      try {
         Optional<Category> optionalCategory = categoryRepository.findById(id);

         if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            CategoryResponse categoryShowResponse = categoryConverter.toDtoCategory(category);

            return CommonResponse.builder()
                    .data(categoryShowResponse)
                    .message("Category retrieved successfully")
                    .statusCode(HttpStatus.OK)
                    .build();
         } else {

            return CommonResponse.builder()
                    .data(null)
                    .message("Category not found")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }
      } catch (Exception e) {

         return CommonResponse.builder()
                 .data(null)
                 .message("Error retrieving category: " + e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }
}
