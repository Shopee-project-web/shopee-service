package com.shopee.controller.admin;

import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.SubCategoryRequest;
import com.shopee.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/sub-categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SubCategoryAdminController {
   private final SubCategoryService subCategoryService;

   @GetMapping
   public ResponseEntity<CommonResponse> getAllSubCategory() {
      CommonResponse commonResponse = subCategoryService.findAllSubCategory();
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

   @PostMapping("/create")
   public ResponseEntity<CommonResponse> createSubCategory(@RequestBody SubCategoryRequest request) {
      CommonResponse commonResponse = subCategoryService.createSubCategory(request);
      return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
   }
   @GetMapping("/{id}")
   public ResponseEntity<CommonResponse> getCategoryById(@PathVariable Long id) {
      CommonResponse commonResponse = subCategoryService.getSubCategoryById(id);
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

   @PutMapping("/{id}")
   public ResponseEntity<CommonResponse> updateSubCategory(@PathVariable Long id, @RequestBody SubCategoryRequest request) {
      CommonResponse commonResponse = subCategoryService.updateSubcategory(id, request);
      return new ResponseEntity<>(commonResponse, commonResponse.getStatusCode());
   }

}
