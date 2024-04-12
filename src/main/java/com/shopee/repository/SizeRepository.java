package com.shopee.repository;

import com.shopee.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size,Long> {
//   Optional<Size> findByName(String name);

   Size findByName(String size);
}
