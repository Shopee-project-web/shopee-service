package com.shoppify.converter;

import com.shoppify.dto.payload.request.CategoryRequest;
import com.shoppify.dto.payload.response.CategoryResponse;
import com.shoppify.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {
   public Category toEntity(CategoryRequest dto) {

      Category category = new Category();

      category.setName(dto.getName());
      category.setShow(dto.isShow());
      return category;
   }

   public CategoryResponse toDto(Category entity) {
      return CategoryResponse.builder()
              .id(entity.getId())
              .name(entity.getName())
              .isShow(entity.isShow())
              .build();
   }

   public List<CategoryResponse> toListDto(List<Category> categoryList) {

      List<CategoryResponse> list = new ArrayList<>();

      for (Category category : categoryList) {

         list.add(toDto(category));
      }

      return list;
   }
}
