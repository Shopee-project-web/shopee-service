package com.shopee.repository;

import com.shopee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
     User findByUsername(String username);

     boolean existsUserByUsername(String username);

     boolean existsUserByEmail(String email);

     boolean existsUserByPhoneNumber(String phoneNumber);

}
