package com.shoppify.repository;

import com.shoppify.entity.Role;
import com.shoppify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{
     User findUserByUsername(String username);

     boolean existsUserByUsername(String username);

}
