package com.shoppify.dto.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {
   private long id;

   private String name;

   private double price;

   private int stock;

   private int star;

   private String description;

   private boolean isShow;

   private CategoryResponse category;

   private SubCategoryResponse subCategory;

   private List<ProductImageResponse> productImageList;

   private List<VariantResponse> variantList;
}