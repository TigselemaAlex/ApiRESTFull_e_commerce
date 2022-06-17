package org.sauriosoft.apirestfull_e_commerce.models.exceptions;

import java.text.MessageFormat;

public class UserException extends RuntimeException{

    public UserException(String message){
        super(MessageFormat.format("Error: {0}",message));
    }

}
