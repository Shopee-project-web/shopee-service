package com.shopee.controller.admin;

import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.CategoryRequest;
import com.shopee.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryAdminController {
   private final CategoryService categoryService;

   @GetMapping()
   public ResponseEntity<CommonResponse> getAllCategory() {
      CommonResponse commonResponse = categoryService.findAllCategory();
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

   @PostMapping("/create")
   public ResponseEntity<CommonResponse> createCategory(@RequestBody CategoryRequest request) {
      CommonResponse commonResponse = categoryService.createCategory(request);
      return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
   }

   @GetMapping("/{id}")
   public ResponseEntity<CommonResponse> getCategoryById(@PathVariable Long id) {
      CommonResponse commonResponse = categoryService.getCategoryById(id);
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

   @PutMapping("/{id}")
   public ResponseEntity<CommonResponse> updateCategory(@PathVariable Long id,
                                                        @RequestBody CategoryRequest request) {
      CommonResponse commonResponse = categoryService.updateCategory(id,request);
      return new ResponseEntity<>(commonResponse, commonResponse.getStatusCode());
   }
}
