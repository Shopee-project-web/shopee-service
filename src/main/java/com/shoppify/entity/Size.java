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
@Table(name = "SIZE")
public class Size {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "NAME")
   private String name;

   @Column(name = "IS_SHOW")
   private boolean isShow;

   @OneToMany(mappedBy = "size",fetch = FetchType.LAZY)
   private List<Variant> variantList;

}
