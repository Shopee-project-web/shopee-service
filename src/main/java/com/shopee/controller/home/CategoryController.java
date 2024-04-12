package com.shopee.controller.home;

import com.shopee.dto.CommonResponse;
import com.shopee.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/home/categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryController {
   private final CategoryService categoryService;

//   xzXZX
   @RequestMapping()
   public ResponseEntity<CommonResponse> getAllCategory() {
      CommonResponse commonResponse = categoryService.findAllCategory();
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

   @RequestMapping()
   public ResponseEntity<CommonResponse> getAllCategoryDemo() {
      CommonResponse commonResponse = categoryService.findAllCategory();
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }
}
