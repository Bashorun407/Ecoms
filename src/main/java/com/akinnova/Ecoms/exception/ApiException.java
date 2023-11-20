package com.akinnova.Ecoms.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
