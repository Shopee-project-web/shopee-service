package com.shopee.exception;

public class ProductNotFoundException extends RuntimeException {
   public ProductNotFoundException() {
      super("Truy cập Product hệ thống LÕI!");
   }

   public ProductNotFoundException(String message) {
      super(message);
   }

   public ProductNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   public ProductNotFoundException(Throwable cause) {
      super(cause);
   }
}

