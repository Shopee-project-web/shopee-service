package com.shopee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="ID")
   private Long id;

   @Column(name = "PHONE_NUMBER" , unique = true, nullable = false)
   private String phoneNumber;

   @Column(name="USERNAME", unique = true, nullable = false)
   private String username;

   @Column(name="EMAIL", unique = true, nullable = false)
   private String email;

   @Column(name = "PASSWORD")
   private String password;

   @Column(name="IS_DELETED")
   private boolean isDeleted;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "USER_ROLE",
           joinColumns = @JoinColumn(name = "USER_ID"),
           inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
   private List<Role> roleList;

   @JsonIgnore
   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   private List<Address> addressList ;

   @JsonIgnore
   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private UserProfile userProfile;

   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   private List<Order> orderList ;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private Cart cart;


}
