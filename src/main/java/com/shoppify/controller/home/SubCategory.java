package com.shoppify.controller.home;

import com.shoppify.dto.CommonResponse;
import com.shoppify.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/home/sub-categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SubCategory {
   private final SubCategoryService subCategoryService;

   @GetMapping
   public ResponseEntity<CommonResponse> getAllSubCategory() {
      CommonResponse commonResponse = subCategoryService.findAllSubCategory();
      return new ResponseEntity<>(commonResponse, HttpStatus.OK);
   }

}
