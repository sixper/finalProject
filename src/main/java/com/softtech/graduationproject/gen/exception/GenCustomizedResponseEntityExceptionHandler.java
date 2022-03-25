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
    public final ResponseEntity<Object> handleDuplicateEntityExceptions(DuplicateUserException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.CONFLICT.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("DuplicateUserException");
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidUsernameException(InvalidUsernameException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.CONFLICT.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("InvalidUsernameException");
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidPasswordExceptions(InvalidPasswordException ex){
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
    public final ResponseEntity<Object> handleInvalidFieldException(InvalidFieldException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.CONFLICT.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("InvalidFieldException");
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidVatException(InvalidVatException ex){
        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detailMessage = ex.getBaseErrorMessage().getDetailMessage();
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate,message,detailMessage, HttpStatus.CONFLICT.value());
        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage("InvalidVatException");
        return new ResponseEntity<>(restResponse, HttpStatus.CONFLICT);
    }




}
