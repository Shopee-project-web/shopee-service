package com.shopee.controller.home;

import com.shopee.dto.CommonResponse;
import com.shopee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SearchController {
   private final ProductService productService;
   @GetMapping("/search/products-by-name")
   public ResponseEntity<CommonResponse> searchProductsByName(@RequestParam String name) {
      try {
         CommonResponse commonResponse = productService.searchProductsByName(name);
         return new ResponseEntity<>(commonResponse, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(CommonResponse.builder()
                 .data(null)
                 .message("Đã xảy ra lỗi khi tìm kiếm sản phẩm.")
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

}
