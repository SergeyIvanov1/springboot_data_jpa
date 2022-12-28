package com.ivanov_sergey.spring.springboot.springboot_data_jpa.exception_handling;

public class NoSuchEmployeeException extends RuntimeException{

    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
