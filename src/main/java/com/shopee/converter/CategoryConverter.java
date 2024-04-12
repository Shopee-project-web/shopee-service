package com.shopee.converter;

import com.shopee.dto.payload.request.CategoryRequest;
import com.shopee.dto.payload.response.CategoryResponse;
import com.shopee.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {
   public Category toEntityCategory(CategoryRequest dto) {

      Category category = new Category();

      category.setName(dto.getName());
      category.setImageUrl(dto.getImageUrl());
      category.setShow(dto.isShow());
      return category;
   }

   public CategoryResponse toDtoCategory(Category entity) {
      return CategoryResponse.builder()
              .id(entity.getId())
              .name(entity.getName())
              .imageUrl(entity.getImageUrl())
              .isShow(entity.isShow())
              .build();
   }

   public List<CategoryResponse> toDtoCategoryList(List<Category> categoryList) {

      List<CategoryResponse> categoryResponseList = new ArrayList<>();

      for (Category category : categoryList) {

         categoryResponseList.add(toDtoCategory(category));
      }

      return categoryResponseList;
   }
}
