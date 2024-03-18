package com.shoppify.converter;

import com.shoppify.dto.payload.request.ProductImageRequest;
import com.shoppify.dto.payload.response.ProductImageResponse;
import com.shoppify.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductImageConverter {

   public ProductImageResponse toDtoProductImage(ProductImage productImage) {
      return ProductImageResponse.builder()
              .id(productImage.getId())
              .url(productImage.getUrl())
              .build();
   }

   public List<ProductImageResponse> toDtoProductImageList(List<ProductImage> productImageList) {
      List<ProductImageResponse> productImageResponseList = new ArrayList<>();
      for (ProductImage productImage : productImageList) {
         productImageResponseList.add(toDtoProductImage(productImage));
      }
      return productImageResponseList;
   }

   public ProductImage toEntityProductImage(ProductImageRequest request) {
      return ProductImage.builder()
              .url(request.getUrl())
              .build();
   }

   public List<ProductImage> toEntityProductImageList(List<ProductImageRequest> productImageRequestList ) {
      List<ProductImage> productImageList = new ArrayList<>();
      for (ProductImageRequest productImageRequest : productImageRequestList) {
         productImageList.add(toEntityProductImage(productImageRequest));
      }
      return productImageList;
   }
}
