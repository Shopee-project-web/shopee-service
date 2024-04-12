package com.shopee.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
   private Long id;
   private List<CartItemResponse> cartItemResponseList;

   private int totalProduct;
   private int totalQuantity;
   private double totalPrice;

}
