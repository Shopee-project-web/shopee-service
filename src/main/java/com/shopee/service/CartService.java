package com.shopee.service;

import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.CartItemRequest;
import com.shopee.dto.payload.request.CartItemUpdateRequest;

public interface CartService {
   CommonResponse addToCart(CartItemRequest cartItemRequest );

   CommonResponse findCartItemByUsername(String username);

   CommonResponse updateCartItem( CartItemUpdateRequest request);

   CommonResponse deleteCartItem (Long itemId);
}
