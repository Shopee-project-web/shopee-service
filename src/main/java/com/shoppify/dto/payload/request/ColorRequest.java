package com.shoppify.dto.payload.request;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ColorRequest {
   private long id;
   private String name;
   private boolean isShow;

}
