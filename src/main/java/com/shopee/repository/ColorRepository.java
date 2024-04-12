package com.shopee.repository;

import com.shopee.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Long> {
//   Optional<Color> findByName(String name);
   Color findByName(String name);
}
