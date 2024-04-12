package com.shopee.repository;


import com.shopee.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
   CartItem findByProductIdAndVariantIdAndCartId(Long productId, Long variantId, Long cartId);
}
