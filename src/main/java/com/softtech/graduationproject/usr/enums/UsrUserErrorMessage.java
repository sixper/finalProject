package com.softtech.graduationproject.usr.enums;


import com.softtech.graduationproject.gen.enums.BaseErrorMessage;

public enum UsrUserErrorMessage implements BaseErrorMessage {

    DUPLICATE_USERNAME("Duplicate username!", "Username already taken"),
    INVALID_USER_PASSWORD("Invalid password!", "Password must be at least 6 character length"),
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
