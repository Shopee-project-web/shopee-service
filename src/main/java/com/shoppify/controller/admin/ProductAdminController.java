package com.shoppify.controller.admin;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CategoryRequest;
import com.shoppify.dto.payload.request.ProductRequest;
import com.shoppify.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/products")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductAdminController {
   private final ProductService productService;

   @GetMapping("/all")
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
   @GetMapping("/subCategory/{subCategoryId}")
   public ResponseEntity<CommonResponse> getProductBySubCategoryId(@PathVariable Long subCategoryId) {
      CommonResponse commonResponse = productService.getProductBySubCategoryId(subCategoryId);
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }
//   @PostMapping("/add")
//   public ResponseEntity<CommonResponse> addProduct(@RequestBody ProductRequest request) {
//      CommonResponse commonResponse = productService.addProduct(request);
//      return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
//   }

}
