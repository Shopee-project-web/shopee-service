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
@Table(name = "CATEGORY")
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "NAME")
   private String name;

   @Column(name = "IS_SHOWN")
   private boolean isShown;

   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
   private List<SubCategory> subCategories ;

   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
   private List<Product> productList ;
}
