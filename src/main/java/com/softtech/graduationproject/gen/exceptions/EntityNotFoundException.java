package com.softtech.graduationproject.gen.exceptions;

import com.softtech.graduationproject.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends GenBusinessException{

    public EntityNotFoundException(BaseErrorMessage message) {
        super(message);
    }
}
