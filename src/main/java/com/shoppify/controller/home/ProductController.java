package com.shoppify.controller.home;

import com.shoppify.dto.CommonResponse;
import com.shoppify.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/home/products")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {
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

   @GetMapping("/category/{categoryId}")
   public ResponseEntity<CommonResponse> getProductByCategoryId(@PathVariable Long categoryId) {
      CommonResponse commonResponse = productService.getProductByCategoryId(categoryId);
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }
   @GetMapping("/sub-category/{subCategoryId}")
   public ResponseEntity<CommonResponse> getProductBySubCategoryId(@PathVariable Long subCategoryId) {
      CommonResponse commonResponse = productService.getProductBySubCategoryId(subCategoryId);
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }


}
