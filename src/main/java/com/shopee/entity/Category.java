package com.shopee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY")
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "NAME")
   private String name;

   @Column(name = "IMAGE_URL")
   private String imageUrl;

   @Column(name = "IS_SHOW")
   private boolean isShow;

   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
   private List<SubCategory> subCategories ;

   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
   private List<Product> productList ;
}
