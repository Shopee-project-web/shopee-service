package com.shopee.service.impl;

import com.shopee.converter.CartItemConverter;
import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.CartItemRequest;
import com.shopee.dto.payload.request.CartItemUpdateRequest;
import com.shopee.dto.payload.response.CartItemResponse;
import com.shopee.dto.payload.response.CartResponse;
import com.shopee.entity.*;
import com.shopee.exception.CartItemNotFoundException;
import com.shopee.exception.ProductNotFoundException;
import com.shopee.repository.*;
import com.shopee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
   private final UserRepository userRepository;
   private final CartRepository cartRepository;
   private final ProductRepository productRepository;
   private final SizeRepository sizeRepository;
   private final ColorRepository colorRepository;
   private final VariantRepository variantRepository;
   private final CartItemRepository cartItemRepository;

   private final CartItemConverter cartItemConverter;

   @Override
   public CommonResponse addToCart(CartItemRequest cartItemRequest) {
      try {
         String username = SecurityContextHolder.getContext().getAuthentication().getName();

         User user = userRepository.findByUsername(username);
         if (user == null) {
            return CommonResponse.builder()
                    .data(null)
                    .message("Tài khoản cần đăng nhập")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }

         Cart cart = user.getCart();
         if (cart == null) {
            cart = new Cart();

            cart.setUser(user);
            cartRepository.save(cart);
         }

         Product product = productRepository.findById(cartItemRequest.getProductId()).orElseThrow(() -> new ProductNotFoundException("Tìm sản phẩm KHÔNG tìm thấy với ID: " + cartItemRequest.getProductId()));

         if (product == null) {
            return CommonResponse.builder()
                    .data(null)
                    .message("Tìm sản phẩm KHÔNG tìm thấy")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }


         Size size = sizeRepository.findByName(cartItemRequest.getSize());
         Color color = colorRepository.findByName(cartItemRequest.getColor());

         Variant variant = variantRepository.findByProductIdAndColorIdAndSizeId(product.getId(), color.getId(), size.getId());
         if (variant == null) {
            return CommonResponse.builder()
                    .data(null)
                    .message("Màu sắc: " + cartItemRequest.getColor() + ". Kích thướt:  " + cartItemRequest.getSize() + " này đã hết hàng. Hãy tìm loại khác của sản phẩm")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }

         variant.setColor(color);
         variant.setSize(size);

         CartItem updateCartItem = cartItemRepository.findByProductIdAndVariantIdAndCartId(product.getId(), variant.getId(), cart.getId());

         if (updateCartItem != null) {
            updateCartItem.setQuantity(updateCartItem.getQuantity() + cartItemRequest.getQuantity());
            updateCartItem.setSubtotal(updateCartItem.getSubtotal() + cartItemRequest.getQuantity() * product.getPrice());
            cartItemRepository.save(updateCartItem);

            CartItemResponse cartItemResponse = cartItemConverter.toDto(updateCartItem);
            return CommonResponse.builder()
                    .data(cartItemResponse)
                    .message("Sản phẩm đã thêm vào giỏ hàng THÀNH CÔNG")
                    .statusCode(HttpStatus.OK).build();
         } else {
            updateCartItem = new CartItem();
            updateCartItem.setProduct(product);
            updateCartItem.setQuantity(cartItemRequest.getQuantity());
            updateCartItem.setVariant(variant);
            updateCartItem.setCart(cart);
            updateCartItem.setPrice(product.getPrice());
            updateCartItem.setSubtotal(product.getPrice() * cartItemRequest.getQuantity());
            cartItemRepository.save(updateCartItem);

            CartItemResponse cartItemResponse = cartItemConverter.toDto(updateCartItem);
            return CommonResponse.builder()
                    .data(cartItemResponse)
                    .message("Sản phẩm đã thêm vào giỏ hàng THÀNH CÔNG")
                    .statusCode(HttpStatus.OK)
                    .build();
         }

      } catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Sản phẩm đã thêm vào giỏ hàng LỖI" + e.getMessage())
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build();
      }
   }

   @Override
   public CommonResponse findCartItemByUsername(String username) {
      try {
         String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();


         User user = userRepository.findByUsername(currentUsername);
         if (user == null) {
            return CommonResponse.builder()
                    .data(null)
                    .message("Tài khoản cần đăng nhập")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }

         Cart cart = user.getCart();
         if (cart == null) {
            cart = new Cart();

            cart.setUser(user);
            cartRepository.save(cart);
         }

         CartResponse cartResponse = new CartResponse();
         cartResponse.setId(cart.getId());
         cartResponse.setCartItemResponseList(cartItemConverter.toDtoList(cart.getCartItemList()));

         int totalProduct = cart.getCartItemList().size();
         cartResponse.setTotalProduct(totalProduct);

         int totalQuantity = 0;
         double totalPrice = 0.0;
         for (CartItem cartItem : cart.getCartItemList()) {
            totalQuantity += cartItem.getQuantity();
            totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();
         }

         cartResponse.setTotalQuantity(totalQuantity);
         cartResponse.setTotalPrice(totalPrice);

         return CommonResponse.builder()
                 .data(cartResponse)
                 .message("Tìm danh sách sản phẩm trong giỏ hàng THÀNH CÔNG!")
                 .statusCode(HttpStatus.OK)
                 .build();
      } catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Tìm danh sách sản phẩm trong giỏ hàng LỖI" + e.getMessage())
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build();
      }
   }

   @Override
   public CommonResponse updateCartItem(CartItemUpdateRequest request) {
      try {
         String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();


         User user = userRepository.findByUsername(currentUsername);
         if (user == null) {
            return CommonResponse.builder()
                    .data(null)
                    .message("Tài khoản cần đăng nhập")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }
//         Cart cart = user.getCart();

         CartItem updateCartItem = cartItemRepository.findById(request.getId())
                 .orElseThrow(() -> new CartItemNotFoundException("Sản phẩm giỏ hàng không tìm thấy"));

         updateCartItem.setQuantity(request.getUpdateQuantity());

         double updateSubtotal = updateCartItem.getProduct().getPrice() * request.getUpdateQuantity();
         updateCartItem.setSubtotal(updateSubtotal);

         cartItemRepository.save(updateCartItem);

         CartItemResponse cartItemResponse = cartItemConverter.toDto(updateCartItem);

         return CommonResponse.builder()
                 .data(cartItemResponse)
                 .message("Cập nhật sản phẩm giỏ hàng THÀNH CÔNG!")
                 .statusCode(HttpStatus.OK)
                 .build();
      } catch (CartItemNotFoundException e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Không tìm thấy sản phẩm trong giỏ hàng")
                 .statusCode(HttpStatus.NOT_FOUND)
                 .build();
      } catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Cập nhật sản phẩm giỏ hàng LỖI" + e.getMessage())
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build();
      }
   }

   @Override
   public CommonResponse deleteCartItem(Long cartItemId) {
      try {
         String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
         User user = userRepository.findByUsername(currentUsername);

         if (user == null) {
            return CommonResponse.builder()
                    .data(null)
                    .message("Tài khoản cần đăng nhập")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }

         Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);

         if (!cartItemOptional.isPresent()) {
            return CommonResponse.builder()
                    .data(null)
                    .message("Không tìm thấy sản phẩm trong giỏ hàng")
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
         }

         CartItem cartItemToDelete = cartItemOptional.get();
         cartItemRepository.delete(cartItemToDelete);

         return CommonResponse.builder()
                 .data(null)
                 .message("Xóa sản phẩm khỏi giỏ hàng THÀNH CÔNG")
                 .statusCode(HttpStatus.OK)
                 .build();
      } catch (Exception e) {
         return CommonResponse.builder()
                 .data(null)
                 .message("Xóa sản phẩm khỏi giỏ hàng LỖI: " + e.getMessage())
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build();
      }
   }

}