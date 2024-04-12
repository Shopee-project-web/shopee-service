package com.shopee.service.impl;

import com.shopee.converter.CategoryConverter;
import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.CategoryRequest;
import com.shopee.dto.payload.response.CategoryResponse;
import com.shopee.entity.Category;
import com.shopee.repository.CategoryRepository;
import com.shopee.service.CategoryService;
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
         commonResponse.setMessage("Truy cập các danh mục hệ thống không tìm thấy.");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
      } else {
         List<CategoryResponse> categoryResponseList = categoryConverter.toDtoCategoryList(categoryList);

         commonResponse.setData(categoryResponseList);
         commonResponse.setMessage("Truy cập các danh mục hệ thống thành công.");
         commonResponse.setStatusCode(HttpStatus.OK);
      }
      return commonResponse;
   }

   @Override
   public CommonResponse createCategory(CategoryRequest request) {

      if (categoryRepository.existsByName(request.getName())) {

         return CommonResponse.builder()
                 .data(null)
                 .message("Danh mục với tên " + request.getName() + " hệ thống đã tồn tại.")
                 .statusCode(HttpStatus.BAD_REQUEST).build();
      }

      Category category = categoryConverter.toEntityCategory(request);
      categoryRepository.save(category);

      CategoryResponse categoryResponse = categoryConverter.toDtoCategory(category);

      return CommonResponse.builder()
              .data(categoryResponse)
              .message("Thêm danh mục vào hệ thông thành công.")
              .statusCode(HttpStatus.CREATED)
              .build();
   }

   @Override
   public CommonResponse updateCategory(Long id, CategoryRequest request) {
      try {
         Category existingCategory = categoryRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Truy cập danh mục hệ thống không tìm thấy."));

         Category updatedCategory = categoryConverter.toEntityCategory(request);

         existingCategory.setName(updatedCategory.getName());
         existingCategory.setImageUrl(updatedCategory.getImageUrl());
         existingCategory.setShow(updatedCategory.isShow());

         categoryRepository.save(existingCategory);

         return CommonResponse.builder()
                 .data(categoryConverter.toDtoCategory(existingCategory))
                 .message("Cập nhật danh mục hệ thống thành công.")
                 .statusCode(HttpStatus.OK)
                 .build();
      } catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Cập nhật danh mục hệ thống bị lỗi: " + e.getMessage())
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
                    .message("Truy cập danh mục hệ thống thành công.")
                    .statusCode(HttpStatus.OK)
                    .build();
         } else {
            return CommonResponse.builder()
                    .data(null)
                    .message("Truy cập danh mục hệ thống không thành công.")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }
      } catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Truy cập danh mục hệ thống lỗi: " + e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }
}
