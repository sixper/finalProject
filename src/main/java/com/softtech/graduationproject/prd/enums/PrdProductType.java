package com.softtech.graduationproject.prd.enums;

import java.math.BigDecimal;

public enum PrdProductType {

    FOOD(1),
    STATIONARY(8),
    CLOTHING(8),
    TECHNOLOGY(18),
    CLEANING(18),
    OTHER(8),
    ;

    private int select;

    public int getVat() {
        return select;
    }

    PrdProductType(int select) {
        this.select = select;
    }
}
