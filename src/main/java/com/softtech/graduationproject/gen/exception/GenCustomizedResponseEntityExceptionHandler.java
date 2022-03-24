package com.softtech.graduationproject.gen.exception;

import com.softtech.graduationproject.gen.dto.RestResponse;
import com.softtech.graduationproject.gen.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GenCustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final  ResponseEntity<Object> handleEntityNotFoundExceptions(EntityNotFoundException ex){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.NOT_FOUND.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("EntityNotFoundException");
        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler
    public final ResponseEntity<Object> handleDuplicateEntityExceptions(InvalidPasswordException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.CONFLICT.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("DuplicateUserException");
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidPasswordExceptions(DuplicateUserException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.CONFLICT.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("InvalidPasswordException");
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidProductExceptions(InvalidProductException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.CONFLICT.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("InvalidProductException");
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidProductPriceExceptions(InvalidProductPriceException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.CONFLICT.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("InvalidProductPriceException");
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }


}
