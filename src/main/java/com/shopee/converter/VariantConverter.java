package com.shopee.converter;

import com.shopee.dto.payload.request.VariantRequest;
import com.shopee.dto.payload.response.VariantResponse;
import com.shopee.entity.Variant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VariantConverter {
   private final ColorConverter colorConverter;
   private final SizeConverter sizeConverter;

   public VariantResponse toDtoVariant(Variant variant) {
      return VariantResponse.builder()
              .id(variant.getId())
              .colorResponse(colorConverter.toDtoColor(variant.getColor()))
              .sizeResponse(sizeConverter.toDtoSize(variant.getSize()))
              .build();
   }

   public List<VariantResponse> toDtoVariantList(List<Variant> variantList) {
      List<VariantResponse> variantResponseList = new ArrayList<>();
      for (Variant variant : variantList) {
         variantResponseList.add(toDtoVariant(variant));
      }
      return variantResponseList;
   }

   public Variant toEntityVariant(VariantRequest request) {
      return Variant.builder()
//              .id(request.getId())
              .color(colorConverter.toEntityColor(request.getColor()))
//              .color(colorConverter.toEntityColor(request.getColorId()))
              .size(sizeConverter.toEntitySize(request.getSize()))
//              .size(sizeConverter.toEntitySize(request.getSizeId()))
//              .product(productConverter.toEntityProduct(request.getProductRequest()))
              .build();
   }

   public List<Variant> toEntityVariantList(List<VariantRequest> variantRequestList) {
      List<Variant> variantList = new ArrayList<>();
      for (VariantRequest variantRequest : variantRequestList) {
         variantList.add(toEntityVariant(variantRequest));
      }
      return variantList;
   }
}
