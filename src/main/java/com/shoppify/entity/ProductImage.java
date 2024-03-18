package com.shoppify.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT_IMAGE")
public class ProductImage {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "URL")
   private String url;

//   @Column(name = "IS_DELETED")
//   private boolean isDeleted;

   @Column(name = "IS_SHOW")
   private boolean isShow;

   @ManyToOne
   @JoinColumn(name = "PRODUCT_ID")
   private Product product;
}
