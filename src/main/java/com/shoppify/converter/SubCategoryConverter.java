package com.shoppify.converter;

import com.shoppify.dto.payload.request.SubCategoryRequest;
import com.shoppify.dto.payload.response.SubCategoryResponse;
import com.shoppify.entity.Category;
import com.shoppify.entity.SubCategory;
import com.shoppify.exception.CategoryNotFoundException;
import com.shoppify.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SubCategoryConverter {
   private final CategoryConverter categoryConverter;
   private final CategoryRepository categoryRepository;

//   public SubCategory toEntitySubCategory(SubCategoryRequest dto) {
//
//      SubCategory subCategory = new SubCategory();
//
//      subCategory.setName(dto.getName());
//
//      Category category = categoryRepository.findById(dto.getCategoryId())
//              .orElseThrow(() -> new EntityNotFoundException("Category not found"));
//      subCategory.setCategory(category);
//
//      subCategory.setShow(dto.isShow());
//      return subCategory;
//   }

   public SubCategory toEntitySubCategory(SubCategoryRequest dto) {
      Category category = categoryRepository.findById(dto.getCategoryId())
              .orElseThrow(() -> new CategoryNotFoundException("Truy cập danh mục hệ thống KHÔNG tìm thấy!"));

      return SubCategory.builder()
              .name(dto.getName())
              .category(category)
              .isShow(dto.isShow())
              .build();
   }

   public SubCategoryResponse toDtoSubCategory(SubCategory entity) {
      return SubCategoryResponse.builder()
              .id(entity.getId())
              .name(entity.getName())
              .category(categoryConverter.toDtoCategory(entity.getCategory()))
              .isShow(entity.isShow())
              .build();
   }

   public List<SubCategoryResponse> toDtoSubCategoryList(List<SubCategory> subCategoryList) {

      List<SubCategoryResponse> subCategoryResponseList = new ArrayList<>();

      for (SubCategory subCategory : subCategoryList) {
         subCategoryResponseList.add(toDtoSubCategory(subCategory));
      }
      return subCategoryResponseList;
   }
}
