package com.shopee.converter;

import com.shopee.dto.payload.response.CartItemResponse;
import com.shopee.entity.CartItem;
import com.shopee.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemConverter {
   public CartItemResponse toDto (CartItem cartItem) {
      CartItemResponse cartItemResponse = new CartItemResponse();

      cartItemResponse.setId(cartItem.getId());
      cartItemResponse.setName(cartItem.getProduct().getName());
      cartItemResponse.setProductId(cartItem.getProduct().getId());
//      cartItemResponse.setImage(null);

      // Lấy đường dẫn hình ảnh từ danh sách hình ảnh của sản phẩm
      String imageUrl = null;
      List<ProductImage> productImageList = cartItem.getProduct().getProductImageList();

      if (productImageList != null && !productImageList.isEmpty()) {
         imageUrl = productImageList.get(0).getUrl();
      }
      cartItemResponse.setProductImage(imageUrl);

      cartItemResponse.setColor(cartItem.getVariant().getColor().getName());
      cartItemResponse.setSize(cartItem.getVariant().getSize().getName());
      cartItemResponse.setQuantity(cartItem.getQuantity());
      cartItemResponse.setPrice(cartItem.getPrice());
      cartItemResponse.setSubtotal(cartItem.getSubtotal());
      return cartItemResponse;
   }

   public List<CartItemResponse> toDtoList (List<CartItem> cartItemList) {
      List<CartItemResponse> list = new ArrayList<>();
      for (CartItem cartItem : cartItemList) {
         list.add(toDto(cartItem));
      }

      return list;
   }
}
