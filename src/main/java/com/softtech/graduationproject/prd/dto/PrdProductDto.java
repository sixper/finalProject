package com.softtech.graduationproject.prd.dto;

import com.softtech.graduationproject.prd.enums.PrdProductType;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class PrdProductDto {

    private PrdProductType prdProductType;
    private String productName;
    private BigDecimal taxFreePrice;
    private BigDecimal VAT;
    private BigDecimal price;

}
