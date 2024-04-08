package com.shoppify.exception;

public class CategoryNotFoundException extends RuntimeException {
   public CategoryNotFoundException() {
      super("Truy cập Category hệ thống LÕI!");
   }

   public CategoryNotFoundException(String message) {
      super(message);
   }

   public CategoryNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   public CategoryNotFoundException(Throwable cause) {
      super(cause);
   }
}

