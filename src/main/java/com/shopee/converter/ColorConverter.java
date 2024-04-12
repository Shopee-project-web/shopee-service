package com.shopee.converter;

import com.shopee.dto.payload.request.ColorRequest;
import com.shopee.dto.payload.response.ColorResponse;
import com.shopee.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorConverter {
   public ColorResponse toDtoColor(Color color) {
      return ColorResponse.builder()
              .id(color.getId())
              .name(color.getName())
              .build();
   }
   public Color toEntityColor(ColorRequest color) {
      return Color.builder()
//              .id(color.getId())
              .name(color.getName())
              .build();
   }

}
