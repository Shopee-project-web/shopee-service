package com.shopee.dto.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VariantRequest {
      private ColorRequest color;
//      private long colorId;
   private SizeRequest size;
//   private long sizeId;
//   private ProductRequest product;
}
