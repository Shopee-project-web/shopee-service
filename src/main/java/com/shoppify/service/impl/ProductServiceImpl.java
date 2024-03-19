package com.shoppify.service.impl;

import com.shoppify.repository.ProductRepository;
import com.shoppify.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
}
