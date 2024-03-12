package com.shoppify.controller.admin;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CategoryRequest;
import com.shoppify.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryAdminController {
   private final CategoryService categoryService;

   @GetMapping("/all")
   public ResponseEntity<CommonResponse> getAllCategory() {
      CommonResponse commonResponse = categoryService.findAllCategory();
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

   @PostMapping("/add")
   public ResponseEntity<CommonResponse> addCategory(@RequestBody CategoryRequest request) {
      CommonResponse commonResponse = categoryService.addCategory(request);
      return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
   }

   @GetMapping("/{id}")
   public ResponseEntity<CommonResponse> getCategoryById(@PathVariable Long id) {
      CommonResponse commonResponse = categoryService.getCategoryById(id);
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

   @PutMapping("/put/{id}")
   public ResponseEntity<CommonResponse> updateCategory(@PathVariable Long id,
                                                        @RequestBody CategoryRequest request) {
      CommonResponse commonResponse = categoryService.updateCategory(id,request);
      return new ResponseEntity<>(commonResponse, commonResponse.getStatusCode());
   }

}
