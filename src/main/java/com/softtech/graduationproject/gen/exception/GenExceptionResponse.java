package com.softtech.graduationproject.gen.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GenExceptionResponse {

    private Date errorDate;
    private String message;
    private String detail;
    private int errorCode;
}
