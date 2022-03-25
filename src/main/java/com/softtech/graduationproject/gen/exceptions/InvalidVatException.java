package com.softtech.graduationproject.gen.exceptions;

import com.softtech.graduationproject.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidVatException extends GenBusinessException{

    public InvalidVatException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }

}
