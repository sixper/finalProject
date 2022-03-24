package com.softtech.graduationproject.prd.entity;

import com.softtech.graduationproject.gen.entity.BaseEntity;
import com.softtech.graduationproject.prd.enums.PrdProductType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRD_PRODUCT")
@Data
public class PrdProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE", length = 30, nullable = false)
    private PrdProductType prdProductType;

    @Column(name = "PRODUCT_NAME", length = 30, nullable = false)
    private String productName;

    @Column(name = "TAX_FREE_PRICE", precision = 19, scale = 2, nullable = false)
    private BigDecimal taxFreePrice;

    @Column(name = "VAT_RATE", precision = 19, scale = 2)
    private BigDecimal vatRate;

    @Column(name = "PRICE", precision = 19, scale = 2, nullable = false)
    private BigDecimal price;
}
