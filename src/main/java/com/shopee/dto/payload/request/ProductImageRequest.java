package com.shopee.dto.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageRequest {
   private String url;
   private boolean isShow;
}
