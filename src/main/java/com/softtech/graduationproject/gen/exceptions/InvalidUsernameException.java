package com.softtech.graduationproject.gen.exceptions;

import com.softtech.graduationproject.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidUsernameException extends GenBusinessException{
    public InvalidUsernameException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
