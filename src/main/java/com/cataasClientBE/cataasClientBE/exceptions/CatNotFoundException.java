package com.cataasClientBE.cataasClientBE.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cat Not Found")
public class CatNotFoundException extends Exception {
    public CatNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
