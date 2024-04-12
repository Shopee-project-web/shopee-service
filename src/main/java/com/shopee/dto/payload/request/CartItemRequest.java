package com.shopee.dto.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemRequest {
   private long productId;
   private String name;

   @NotBlank(message = "Hãy chọn MÀU SẮC")
   private String color;

   @NotBlank(message = "Hãy chọn KÍCH THƯỚT")
   private String size;
   private int quantity;
   private double price;
   private double subtotal;
}
