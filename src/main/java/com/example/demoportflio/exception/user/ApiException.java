package com.example.demoportflio.exception.user;


import org.springframework.http.HttpStatus;


public class ApiException {
    private final String message;

    private final HttpStatus httpstatus;

    public ApiException(String message, HttpStatus httpstatus) {
        this.message = message;


        this.httpstatus = httpstatus;
    }



    public String getMessage() {
        return message;
    }





    public HttpStatus getHttpstatus() {
        return httpstatus;
    }
}
