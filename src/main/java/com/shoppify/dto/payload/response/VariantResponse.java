package com.shoppify.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariantResponse {
   private long id;
   private ColorResponse color;
   private SizeResponse size;
}
