package com.shopee.dto.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubCategoryRequest {
   private String name;
   private Long categoryId;
   private boolean isShow;
}
