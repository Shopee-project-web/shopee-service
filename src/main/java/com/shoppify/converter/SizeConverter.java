package com.shoppify.converter;

import com.shoppify.dto.payload.request.SizeRequest;
import com.shoppify.dto.payload.response.SizeResponse;
import com.shoppify.entity.Size;
import org.springframework.stereotype.Component;

@Component
public class SizeConverter {
   public SizeResponse toDtoSize(Size size) {
      return SizeResponse.builder()
              .id(size.getId())
              .name(size.getName())
              .build();
   }

   public Size toEntitySize(SizeRequest size) {
      return Size.builder()
//              .id(size.getId())
              .name(size.getName())
              .build();
   }
}
