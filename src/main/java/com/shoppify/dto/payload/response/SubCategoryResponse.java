package com.shoppify.dto.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryResponse {
   private long id;
   private String name;
   private CategoryResponse category;
   private boolean isShow;
}
