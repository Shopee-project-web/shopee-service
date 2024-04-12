package com.shopee.dto.payload.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SizeRequest {
   private long id;
   private String name;
   private boolean isShow;
}
