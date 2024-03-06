package com.shoppify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
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
   private long id;

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

   @Column(name = "FULL_NAME")
   private String fullName;

   @Column(name = "BIRTH_DATE")
   private Date birthDate;

   @Column(name = "GENDER", unique = true)
   private String gender;

   @Column(name = "USER_IMAGE_URL")
   private String userImageUrl;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "USER_ROLE",
           joinColumns = @JoinColumn(name = "USER_ID"),
           inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
   private List<Role> roleList;


   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   private List<Address> addressList ;


}
