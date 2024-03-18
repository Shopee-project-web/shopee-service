package com.shoppify.repository;

import com.shoppify.entity.Size;
import com.shoppify.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size,Long> {
}
