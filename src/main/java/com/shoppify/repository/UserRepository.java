package com.shoppify.repository;

import com.shoppify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
     User findByUsername(String username);

     boolean existsUserByUsername(String username);

}
