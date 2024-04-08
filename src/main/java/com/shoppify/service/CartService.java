package com.shoppify.service;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CartItemRequest;
import com.shoppify.dto.payload.request.CartItemUpdateRequest;

public interface CartService {
   CommonResponse addToCart(CartItemRequest cartItemRequest );

   CommonResponse findCartItemByUsername(String username);

   CommonResponse updateCartItem( CartItemUpdateRequest request);

   CommonResponse deleteCartItem (Long itemId);
}
