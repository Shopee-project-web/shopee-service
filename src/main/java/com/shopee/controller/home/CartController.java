package com.shopee.controller.home;

import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.CartItemRequest;
import com.shopee.dto.payload.request.CartItemUpdateRequest;
import com.shopee.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/home")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {
   private final CartService cartService;

   @PostMapping("/cart/add-to-cart")
   public ResponseEntity<CommonResponse> addToCart(@Valid @RequestBody CartItemRequest request,
                                                   BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         String errorMessage = bindingResult.getAllErrors()
                 .stream()
                 .map(error -> error.getDefaultMessage())
                 .reduce("", (accumulator, message) -> accumulator + message + ". ");
         CommonResponse errorResponse = new CommonResponse();
         errorResponse.setMessage(errorMessage);
         return ResponseEntity.badRequest().body(errorResponse);
      }

      try {
         CommonResponse response = cartService.addToCart(request);

         return new ResponseEntity<>(response, HttpStatus.OK);

      } catch (Exception e) {
         CommonResponse errorResponse = new CommonResponse();
         errorResponse.setMessage("Sản phẩm đã thêm vào giỏ hàng LỖI");
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
      }
   }

   @GetMapping("/cart/user")
   public ResponseEntity<CommonResponse> getCartItemListByUsername(String username) {
      try {
         CommonResponse commonResponse = cartService.findCartItemByUsername(username);
         return new ResponseEntity<>(commonResponse, HttpStatus.OK);
      } catch (Exception e) {

         return new ResponseEntity<>(CommonResponse.builder()
                 .data(null)
                 .message("Tìm danh sách sản phẩm trong giỏ hàng LỖI!.")
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

//   @PutMapping("/cart/items")
//   public ResponseEntity<CommonResponse> updateCartItem(@RequestBody CartItemUpdateRequest request) {
//      try {
//         CommonResponse commonResponse = cartService.updateCartItem(request);
//         return new ResponseEntity<>(commonResponse, HttpStatus.OK);
//      } catch (Exception e) {
//
//         return new ResponseEntity<>(CommonResponse.builder()
//                 .data(null)
//                 .message("Tìm danh sách sản phẩm trong giỏ hàng LỖI!.")
//                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//      }
//   }

   @PutMapping("/cart/{itemId}")
   public ResponseEntity<CommonResponse> updateCartItem(@PathVariable Long itemId,
                                                        @RequestBody CartItemUpdateRequest request) {
      try {
         request.setId(itemId);
         CommonResponse commonResponse = cartService.updateCartItem(request);
         return new ResponseEntity<>(commonResponse, HttpStatus.OK);
      } catch (Exception e) {

         return new ResponseEntity<>(CommonResponse.builder()
                 .data(null)
                 .message("Tìm danh sách sản phẩm trong giỏ hàng LỖI!.")
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @DeleteMapping("/cart/items/{itemId}")
   public ResponseEntity<CommonResponse> deleteCartItem(@PathVariable Long itemId) {
      try {
         CommonResponse commonResponse = cartService.deleteCartItem(itemId);
         return new ResponseEntity<>(commonResponse, HttpStatus.OK);
      } catch (Exception e) {

         return new ResponseEntity<>(CommonResponse.builder()
                 .data(null)
                 .message("Xóa sản phẩm trong giỏ hàng LỖI!.")
                 .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                 .build(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
}
