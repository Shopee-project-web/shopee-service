package com.shoppify.converter;

import com.shoppify.dto.payload.request.SubCategoryRequest;
import com.shoppify.dto.payload.response.SubCategoryResponse;
import com.shoppify.entity.Category;
import com.shoppify.entity.SubCategory;
import com.shoppify.exception.EntityNotFoundException;
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

   public SubCategory toEntity(SubCategoryRequest dto) {

      SubCategory subCategory = new SubCategory();

      subCategory.setName(dto.getName());

      Category category = categoryRepository.findById(dto.getCategoryId())
              .orElseThrow(() -> new EntityNotFoundException("Category not found"));
      subCategory.setCategory(category);

      subCategory.setShow(dto.isShow());
      return subCategory;
   }

   public SubCategoryResponse toDto(SubCategory entity) {
      return SubCategoryResponse.builder()
              .id(entity.getId())
              .name(entity.getName())
              .category(categoryConverter.toDto(entity.getCategory()))
              .isShow(entity.isShow())
              .build();
   }

   public List<SubCategoryResponse> toListDto(List<SubCategory> subCategoryList) {

      List<SubCategoryResponse> list = new ArrayList<>();

      for (SubCategory subCategory : subCategoryList) {
         list.add(toDto(subCategory));
      }
      return list;
   }
}
