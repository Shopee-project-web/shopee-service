package com.shopee.exception;

public class VariantNotFoundException extends RuntimeException {
   public VariantNotFoundException() {
      super("Truy cập Variant hệ thống LÕI!");
   }

   public VariantNotFoundException(String message) {
      super(message);
   }

   public VariantNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   public VariantNotFoundException(Throwable cause) {
      super(cause);
   }
}

