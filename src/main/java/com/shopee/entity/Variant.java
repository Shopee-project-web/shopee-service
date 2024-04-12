package com.shopee.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VARIANT")
public class Variant {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @ManyToOne
   @JoinColumn(name = "COLOR_ID")
   private Color color;

   @ManyToOne
   @JoinColumn(name = "SIZE_ID")
   private Size size;

//   @ManyToOne
//   @JoinColumn(name = "CLASSIFY_ID")
//   private Classify classify;

   @ManyToOne
   @JoinColumn(name="PRODUCT_ID")
   private Product product;

   @Column(name = "IS_SHOW")
   private boolean isShow;

   @OneToMany(mappedBy = "variant", fetch = FetchType.LAZY)
   private List<CartItem> cartItemList ;

   @OneToMany(mappedBy = "variant", fetch = FetchType.LAZY)
   private List<OrderLine> orderLineList ;


}
