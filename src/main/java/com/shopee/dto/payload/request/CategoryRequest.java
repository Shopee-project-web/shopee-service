package com.shopee.dto.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryRequest {
   private String name;
   private String imageUrl;
   private boolean isShow;
}
