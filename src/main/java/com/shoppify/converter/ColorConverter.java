package com.shoppify.converter;

import com.shoppify.dto.payload.request.ColorRequest;
import com.shoppify.dto.payload.response.ColorResponse;
import com.shoppify.entity.Color;
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
