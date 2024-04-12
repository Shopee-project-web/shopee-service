package com.shopee.exception;

public class UserNotFoundException extends RuntimeException {
   public UserNotFoundException() {
      super("Truy cập User hệ thống LÕI!");
   }

   public UserNotFoundException(String message) {
      super(message);
   }

   public UserNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   public UserNotFoundException(Throwable cause) {
      super(cause);
   }
}

