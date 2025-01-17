package com.shopee.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CART_ITEM")
public class CartItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "QUANTITY")
   private int quantity;

   @Column(name = "PRICE")
   private double price;

   @Column(name = "SUBTOTAL")
   private double subtotal;

   @ManyToOne
   @JoinColumn(name = "CART_ID")
   private Cart cart;

   @ManyToOne
   @JoinColumn(name = "PRODUCT_ID")
   private Product product;

   @ManyToOne
   @JoinColumn(name = "VARIANT_ID")
   private Variant variant;

   @Column(name = "IS_DELETED")
   private boolean isDeleted;
}
