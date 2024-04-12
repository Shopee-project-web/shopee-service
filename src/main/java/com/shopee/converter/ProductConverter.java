package com.shopee.converter;

import com.shopee.dto.payload.response.ProductResponse;
import com.shopee.entity.*;
import com.shopee.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductConverter {
   private final CategoryConverter categoryConverter;
   private final SubCategoryConverter subCategoryConverter;
   private final ProductImageConverter productImageConverter;
   private final VariantConverter variantConverter;
   private final CategoryRepository categoryRepository;
   private final SubCategoryRepository subCategoryRepository;
   private final ProductRepository productRepository;
   private final ProductImageRepository productImageRepository;
   private final ColorRepository colorRepository;
   private final SizeRepository sizeRepository;
   private final VariantRepository variantRepository;


   public ProductResponse toDtoProduct(Product entity) {
      return ProductResponse.builder()
              .id(entity.getId())
              .name(entity.getName())
              .price(entity.getPrice())
              .stock(entity.getStock())
              .star(entity.getStar())
              .description(entity.getDescription())
              .isShow(entity.isShow())

              .category(categoryConverter.toDtoCategory(entity.getCategory()))
              .subCategory(subCategoryConverter.toDtoSubCategory(entity.getSubCategory()))
              .productImageList(productImageConverter.toDtoProductImageList(entity.getProductImageList()))
              .variantList(variantConverter.toDtoVariantList(entity.getVariantList()))
              .build();
   }

   public List<ProductResponse> toDtoProductList(List<Product> productList) {
      List<ProductResponse> productResponseList = new ArrayList<>();
      for (Product product : productList) {
         productResponseList.add(toDtoProduct(product));
      }
      return productResponseList;
   }


//   public Product toEntityProduct(ProductRequest dto){
//      // Tạo một instance của Product và thiết lập các thuộc tính từ ProductRequest
//      Product newProduct = new Product();
//      newProduct.setName(dto.getName());
//      newProduct.setPrice(dto.getPrice());
//      newProduct.setStock(dto.getStock());
//      newProduct.setDescription(dto.getDescription());
////      newProduct.setStar(dto.getStar());
//
//      // Tìm và thiết lập Category
//      Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
//      if (category == null) {
//         throw new EntityNotFoundException("Category not found with id: " + dto.getCategoryId());
//      }
//      newProduct.setCategory(category);
//
//      // Tìm và thiết lập SubCategory
//      SubCategory subCategory = subCategoryRepository.findById(dto.getSubCategoryId()).orElse(null);
//      if (subCategory == null) {
//         throw new EntityNotFoundException("SubCategory not found with id: " + dto.getSubCategoryId());
//      }
//
//      newProduct.setSubCategory(subCategory);
//
//      // Lưu Product vào cơ sở dữ liệu
//      newProduct = productRepository.save(newProduct);
//
//      // Xử lý ImageProduct
////      List<ProductImage> productImageList = new ArrayList<>();
////      for (ProductImageRequest productImageRequest : dto.getProductImageList()) {
////         ProductImage productImage = new ProductImage();
////         productImage.setUrl(productImageRequest.getUrl());
////         productImage.setProduct(newProduct);
////         productImageRepository.save(productImage);
////         productImageList.add(productImage);
////      }
////      newProduct.setProductImageList(productImageList);
//
//      // Xử lý Variant
//      List<Variant> variantList = new ArrayList<>();
//      for (VariantRequest variantRequest : dto.getVariantList()) {
//
//         // Kiểm tra xem color có tồn tại không
////         Optional<Color> optionalColor = colorRepository.findByName(variantRequest.getColor().getName());
////         Color color;
////         if (optionalColor.isPresent()) {
////            color = optionalColor.get();
////         } else {
////            color = new Color();
////            color.setName(variantRequest.getColor().getName());
////            colorRepository.save(color);
////         }
////
////         // Kiểm tra xem size có tồn tại không
////         Optional<Size> optionalSize = sizeRepository.findByName(variantRequest.getSize().getName());
////         Size size;
////         if (optionalSize.isPresent()) {
////            size = optionalSize.get();
////         } else {
////            // Nếu size không tồn tại, tạo mới và lưu vào cơ sở dữ liệu
////            size = new Size();
////            size.setName(variantRequest.getSize().getName());
////            sizeRepository.save(size);
////         }
//
//
//         Color color = colorRepository.findById(variantRequest.getColor().getId()).orElse(null);
//         if (color == null) {
//            throw new EntityNotFoundException("Color not found with id: " + variantRequest.getColor().getId());
//         }
//         Size size = sizeRepository.findById(variantRequest.getSize().getId()).orElse(null);
//         if (size == null) {
//            throw new EntityNotFoundException("Size not found with id: " + variantRequest.getSize().getId());
//         }
//
//         // Tạo và lưu biến thể (variant) mới
//         Variant variant = new Variant();
//         variant.setColor(color);
//         variant.setSize(size);
//         variant.setProduct(newProduct);
//         variantRepository.save(variant);
//         variantList.add(variant);
//      }
//      newProduct.setVariantList(variantList);
//
//
//      return newProduct;
//   }



}
