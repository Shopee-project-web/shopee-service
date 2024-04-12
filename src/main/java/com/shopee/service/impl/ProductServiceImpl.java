package com.shopee.service.impl;

import com.shopee.converter.ProductConverter;
import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.response.ProductResponse;
import com.shopee.entity.Product;
import com.shopee.repository.ProductRepository;
import com.shopee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
   private final ProductRepository productRepository;
   private final ProductConverter productConverter;

   @Override
   public CommonResponse findAllProduct() {

      CommonResponse commonResponse = new CommonResponse();

      List<Product> productList = productRepository.findAll();

      if (productList.isEmpty()) {

         commonResponse.setData(null);
         commonResponse.setMessage("Tìm các sản phẩm KHÔNG tìm thấy.");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
      } else {

         List<ProductResponse> productResponseList = productConverter.toDtoProductList(productList);

         commonResponse.setData(productResponseList);
         commonResponse.setMessage("Tìm các sản phẩm THÀNH CÔNG.");
         commonResponse.setStatusCode(HttpStatus.OK);
      }
      return commonResponse;
   }

   @Override
   public CommonResponse getProductById(Long productId) {
      try {
         Optional<Product> optionalProduct = productRepository.findById(productId);

         if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();
            ProductResponse productResponse = productConverter.toDtoProduct(product);

            return CommonResponse.builder()
                    .data(productResponse)
                    .message("Tìm sản phẩm THÀNH CÔNG.")
                    .statusCode(HttpStatus.OK).build();
         } else {

            return CommonResponse.builder()
                    .data(null)
                    .message("Tìm sản phẩm KHÔNG tìm thấy.")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }
      } catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Tìm sản phẩm LỖI: " + e.getMessage())
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build();
      }
   }

   @Override
   public CommonResponse getProductByCategoryId(Long categoryId) {
      CommonResponse commonResponse = new CommonResponse();
      List<Product> productList = productRepository.findProductByCategoryId(categoryId);

      if (productList.isEmpty()) {

         commonResponse.setData(null);
         commonResponse.setMessage("Tìm tất cả sản phẩm theo danh mục KHÔNG tìm thấy.");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
      } else {
         List<ProductResponse> productResponseList = productConverter.toDtoProductList(productList);
         commonResponse.setData(productResponseList);
         commonResponse.setMessage("Tìm tất cả sản phẩm theo danh mục THÀNH CÔNG.");
         commonResponse.setStatusCode(HttpStatus.OK);
      }
      return commonResponse;
   }

   @Override
   public CommonResponse getProductBySubCategoryId(Long subCategoryId) {
      CommonResponse commonResponse = new CommonResponse();
      List<Product> productList = productRepository.findProductBySubCategoryId(subCategoryId);

      if (productList.isEmpty()) {

         commonResponse.setData(null);
         commonResponse.setMessage("Tìm tất cả sản phẩm theo danh mục con KHÔNG tìm thấy.");
         commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
      } else {
         List<ProductResponse> productResponseList = productConverter.toDtoProductList(productList);
         commonResponse.setData(productResponseList);
         commonResponse.setMessage("Tìm tất cả sản phẩm theo danh mục con THÀNH CÔNG.");
         commonResponse.setStatusCode(HttpStatus.OK);
      }
      return commonResponse;
   }
//   @Override
//   public CommonResponse addProduct(ProductRequest request) {
//
//      if (productRepository.existsByName(request.getName())) {
//
//         return CommonResponse.builder()
//                 .data(null)
//                 .message("Product with name " + request.getName() + " already exists")
//                 .statusCode(HttpStatus.BAD_REQUEST).build();
//      }
//
//      Product product = productConverter.toEntityProduct(request);
//      productRepository.save(product);
//
//      ProductResponse productResponse = productConverter.toDtoProduct(product);
//
//      return CommonResponse.builder()
//              .data(productResponse)
//              .message("Add product successfully")
//              .statusCode(HttpStatus.CREATED)
//              .build();
//   }

   @Override
   public CommonResponse searchProductsByName(String name) {
      String tempName = "%".concat(name).concat("%");
//      String tempName = "%" + name + "%";
      try {
         List<Product> productList = productRepository.findAllByNameLike(tempName);

         List<ProductResponse> searchProductList = new ArrayList<>();
         for (Product product : productList) {
            ProductResponse productResponse = productConverter.toDtoProduct(product);
            searchProductList.add(productResponse);   //add: dành cho danh sách, save: cho đối tượng
         }

         return CommonResponse.builder()
                 .data(searchProductList)
                 .message("Sản phẩm tìm kiếm THÀNH CÔNG")
                 .statusCode(HttpStatus.OK)
                 .build();
      } catch (Exception e) {
         // Trả về response lỗi
         return CommonResponse.builder()
                 .data(null)
                 .message("Sản phẩm tìm kiếm LỖI!")
                 .statusCode(HttpStatus.BAD_REQUEST)
                 .build();
      }
   }
}
