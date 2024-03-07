package com.shoppify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEW")
public class Review {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "CONTENT")
   private String content;

   @Column(name = "TIME")
   private LocalDateTime time;

   @Column(name = "STAR")
   private int star;

   @Column(name = "IS_SHOW")
   private boolean isShow;

   @ManyToOne
   @JoinColumn(name = "USER_ID")
   private User user;

   @ManyToOne
   @JoinColumn(name = "PRODUCT_ID")
   private Product product;
}
