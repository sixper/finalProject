package com.softtech.graduationproject.prd.enums;

import com.softtech.graduationproject.gen.enums.BaseErrorMessage;

public enum PrdProductErrorMessage implements BaseErrorMessage {

    INVALID_PRODUCT("Invalid product!", "Product fields are not valid"),
    INVALID_PRODUCT_PRICE("Invalid price!", "Price can not be zero or below"),
    INVALID_PRODUCT_VAT("Invalid VAT!", "VAT can not be negative"),
    PRODUCT_TYPE_NOT_FOUND("Product not found!", "Could not find any product with provided product type"),
    PRODUCT_PRICE_RANGE_NOT_FOUND("Product not found!", "Could not find any product with provided price range"),
    ;

    private final String message;
    private final String detailMessage;

    PrdProductErrorMessage(String message, String detailMessage) {
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
