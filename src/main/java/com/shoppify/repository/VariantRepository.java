package com.shoppify.repository;

import com.shoppify.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant,Long> {
   Variant findByProductIdAndColorIdAndSizeId (Long product_id, Long color_id, Long size_id);
}
