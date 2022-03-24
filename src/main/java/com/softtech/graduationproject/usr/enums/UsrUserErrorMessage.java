package com.softtech.graduationproject.usr.enums;


import com.softtech.graduationproject.gen.enums.BaseErrorMessage;

public enum UsrUserErrorMessage implements BaseErrorMessage {

    DUPLICATE_USERNAME("Duplicate username!", "Username already taken"),
    INVALID_USER_PASSWORD("Invalid password!", "Password violates conditions"),
    INVALID_USERNAME("Invalid username!", "Username violates conditions"),
    INVALID_FIELD("Invalid field!", "Name or surname violates conditions"),
    ;

    private final String message;
    private final String detailMessage;


    UsrUserErrorMessage(String message, String detailMessage){
        this.message = message;
        this.detailMessage = detailMessage;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }


}
