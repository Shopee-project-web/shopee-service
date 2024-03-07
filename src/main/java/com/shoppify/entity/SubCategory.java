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
@Table(name = "SUB_CATEGORY")
public class SubCategory {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "NAME")
   private String name;

   @Column(name = "IS_SHOW")
   private boolean isShow;

   @ManyToOne
   @JoinColumn(name = "CATEGORY_ID")
   private Category category;

   @OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
   private List<Product> productList ;

}
