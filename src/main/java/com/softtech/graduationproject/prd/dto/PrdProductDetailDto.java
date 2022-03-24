package com.softtech.graduationproject.prd.dto;

import com.softtech.graduationproject.prd.enums.PrdProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrdProductDetailDto {

    private PrdProductType prdProductType;
    private BigDecimal vatRate;
    private BigDecimal minimumPrice;
    private BigDecimal maximumPrice;
    private BigDecimal averagePrice;
    private Long productCount;

}
