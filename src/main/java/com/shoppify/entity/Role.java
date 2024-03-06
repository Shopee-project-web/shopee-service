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
@Table(name = "ROLE")
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private long id;

   @Column(name = "NAME")
   private String name;

   @Column(name = "DESCRIPTION")
   private String description;

   @ManyToOne
   @JoinColumn(name = "USER_ID")
   private User user;

   @ManyToMany(mappedBy = "roleList")
   private List<User> userList;
}
