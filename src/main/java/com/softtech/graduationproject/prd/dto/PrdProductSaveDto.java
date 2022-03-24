package com.softtech.graduationproject.prd.dto;

import com.softtech.graduationproject.prd.enums.PrdProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrdProductSaveDto {

    private PrdProductType prdProductType;
    private String productName;
    private BigDecimal taxFreePrice;

}
