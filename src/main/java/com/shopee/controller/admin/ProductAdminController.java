package com.shopee.controller.admin;

import com.shopee.dto.CommonResponse;
import com.shopee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/products")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductAdminController {
   private final ProductService productService;

   @GetMapping
   public ResponseEntity<CommonResponse> getAllProduct() {
      CommonResponse commonResponse = productService.findAllProduct();
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }
   @GetMapping("/{productId}")
   public ResponseEntity<CommonResponse> getProductById(@PathVariable Long productId) {
      CommonResponse commonResponse = productService.getProductById(productId);
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

//   @PostMapping("/add")
//   public ResponseEntity<CommonResponse> addProduct(@RequestBody ProductRequest request) {
//      CommonResponse commonResponse = productService.addProduct(request);
//      return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
//   }

}
