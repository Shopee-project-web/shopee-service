package com.shoppify.service.impl;

import com.shoppify.converter.SubCategoryConverter;
import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.SubCategoryRequest;
import com.shoppify.dto.payload.response.SubCategoryResponse;
import com.shoppify.entity.SubCategory;
import com.shoppify.repository.CategoryRepository;
import com.shoppify.repository.SubCategoryRepository;
import com.shoppify.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
   private final SubCategoryRepository subCategoryRepository;
   private final SubCategoryConverter subCategoryConverter;
   private final CategoryRepository categoryRepository;

   @Override
   public CommonResponse findAllSubCategory() {


      CommonResponse commonResponse = new CommonResponse();

      List<SubCategory> subCategoryList = subCategoryRepository.findAll();


      if (subCategoryList.isEmpty()) {

         commonResponse.setData(null);
         commonResponse.setMessage("SubCategories not found");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
      } else {
         List<SubCategoryResponse> subCategoryResponseList = subCategoryConverter.toDtoSubCategoryList(subCategoryList);

         commonResponse.setData(subCategoryResponseList);
         commonResponse.setMessage("Accessed the subCategories successfully");
         commonResponse.setStatusCode(HttpStatus.OK);
      }
      return commonResponse;
   }



   @Override
   public CommonResponse createSubCategory(SubCategoryRequest request) {

      if (subCategoryRepository.existsByName(request.getName())) {

         return CommonResponse.builder()
                 .data(null)
                 .message("Danh mục con với tên " + request.getName() + " hệ thống đã tồn tại.")
                 .statusCode(HttpStatus.BAD_REQUEST).build();
      }

      SubCategory subCategory = subCategoryConverter.toEntitySubCategory(request);

      subCategoryRepository.save(subCategory);

      SubCategoryResponse subCategoryResponse = subCategoryConverter.toDtoSubCategory(subCategory);

      return CommonResponse.builder()
              .data(subCategoryResponse)
              .message("Thêm danh mục con vào hệ thông thành công.")
              .statusCode(HttpStatus.CREATED)
              .build();
   }


   @Override
   public CommonResponse getSubCategoryById(Long id) {
      try {
         Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);

         if (optionalSubCategory.isPresent()) {

            SubCategory subCategory = optionalSubCategory.get();
            SubCategoryResponse subCategoryResponse = subCategoryConverter.toDtoSubCategory(subCategory);

            return CommonResponse.builder()
                    .data(subCategoryResponse)
                    .message("Truy cập danh mục con hệ thống thành công.")
                    .statusCode(HttpStatus.OK)
                    .build();
         } else {

            return CommonResponse.builder()
                    .data(null)
                    .message("Truy cập danh mục con hệ thống không thành công.")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }
      } catch (Exception e) {

         return CommonResponse.builder()
                 .data(null)
                 .message("Truy cập danh mục hệ thống lỗi: " + e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

   @Override
   public CommonResponse updateSubcategory(Long id, SubCategoryRequest request) {
      try   {
         SubCategory existingSubCategory = subCategoryRepository.findById(id)
                 .orElseThrow( () -> new RuntimeException(" Truy cập danh mục con hệ thống không tìm thấy."));
         SubCategory updateSubcategory = subCategoryConverter.toEntitySubCategory(request);
         existingSubCategory.setName(updateSubcategory.getName());
         existingSubCategory.setCategory(updateSubcategory.getCategory());
         existingSubCategory.setShow(updateSubcategory.isShow());

         subCategoryRepository.save(existingSubCategory);

         return CommonResponse.builder()
                 .data(subCategoryConverter.toDtoSubCategory(existingSubCategory))
                 .message("Cập nhật danh mục con hệ thống thành công.")
                 .statusCode(HttpStatus.OK)
                 .build();

      }catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Cập nhật danh mục con hệ thống bị lỗi: " + e.getMessage())
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build();

      }
   }
}
