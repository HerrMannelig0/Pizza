package org.pizzaproject.security;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class InvalidUserCredentialsException extends Exception{

    public InvalidUserCredentialsException() {
    }

    public InvalidUserCredentialsException(Errors errors) {

        StringBuilder message = new StringBuilder("");

        for(ObjectError error : errors.getAllErrors()){
            message.append("***************************");
            message.append(error.toString());
        }
    }
}
