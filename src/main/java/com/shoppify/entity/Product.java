package com.shoppify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "NAME")
   private String name;

   @Column(name = "PRICE")
   private double price;

   @Column(name = "STOCK")
   private int stock;

   @Column(name = "DESCRIPTION")
   private String description;

   @Column(name = "IS_SHOWN")
   private boolean isShown;

   @ManyToOne
   @JoinColumn(name = "CATEGORY_ID")
   private Category category;

   @ManyToOne
   @JoinColumn(name = "SUB_CATEGORY_ID")
   private SubCategory subCategory;

   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<ProductImage> productImageList ;

   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
   private List<Review> reviews ;

   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
   private List<CartItem> cartProductList ;

   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
   private List<OrderLine> orderLineList ;

   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
   private List<Variant> variantList;

}
