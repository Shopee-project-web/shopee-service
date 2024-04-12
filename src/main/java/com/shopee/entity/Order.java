package com.shopee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "DATE")
   private LocalDateTime date;

   @Column(name = "PAYMENT")
   private String payment;

   @Column(name = "TOTAL_COST")
   private long totalCost;

   @Column(name = "TRANSPORT")
   private int transport;

   @Column(name = "TOTAL_PAYMENT")
   private long totalPayment;

   @ManyToOne
   @JoinColumn(name = "ADDRESS_ID")
   private Address address;

   @ManyToOne
   @JoinColumn(name = "USER_ID")
   private User user;

   @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
   private List<OrderLine> orderLineList ;

}
