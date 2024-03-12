package com.shoppify.controller.admin;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.CategoryRequest;
import com.shoppify.dto.payload.request.SubCategoryRequest;
import com.shoppify.service.CategoryService;
import com.shoppify.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/subCategories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SubCategoryAdminController {
   private final SubCategoryService subCategoryService;

   @GetMapping("/all")
   public ResponseEntity<CommonResponse> getAllSubCategory() {
      CommonResponse commonResponse = subCategoryService.findAllSubCategory();
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

   @PostMapping("/add")
   public ResponseEntity<CommonResponse> addSubCategory(@RequestBody SubCategoryRequest request) {
      CommonResponse commonResponse = subCategoryService.addSubCategory(request);
      return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
   }
   @GetMapping("/{id}")
   public ResponseEntity<CommonResponse> getCategoryById(@PathVariable Long id) {
      CommonResponse commonResponse = subCategoryService.getSubCategoryById(id);
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }


}
