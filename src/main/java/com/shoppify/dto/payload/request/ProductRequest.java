package com.shoppify.dto.payload.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
   private String name;
   private double price;
   private int stock;
   private int star;
   private String description;
   private boolean isShow;
   private Long categoryId;
   private Long subCategoryId;
   private List<ProductImageRequest> productImageList;
   private List<VariantRequest> variantList;
}
