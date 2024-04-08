package com.shoppify.repository;

import com.shoppify.entity.Color;
import com.shoppify.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color,Long> {
//   Optional<Color> findByName(String name);
   Color findByName(String name);
}
