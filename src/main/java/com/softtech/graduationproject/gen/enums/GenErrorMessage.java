package com.softtech.graduationproject.gen.enums;

public enum GenErrorMessage implements BaseErrorMessage{

    ENTITY_NOT_FOUND("Entity not found!", "Could not found an entity with this ID"),
    ;

    private final String message;
    private final String detailMessage;


    GenErrorMessage(String message, String detailMessage) {
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
