package com.shoppify.repository;


import com.shoppify.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
   CartItem findByProductIdAndVariantIdAndCartId(Long productId, Long variantId, Long cartId);
}
