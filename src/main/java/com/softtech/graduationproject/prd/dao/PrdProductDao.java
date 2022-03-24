package com.softtech.graduationproject.prd.dao;

import com.softtech.graduationproject.prd.entity.PrdProduct;
import com.softtech.graduationproject.prd.enums.PrdProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrdProductDao extends JpaRepository<PrdProduct, Long> {

    List<PrdProduct> findByPrdProductType(PrdProductType prdProductType);

    List<PrdProduct> findAllByPriceBetween(BigDecimal lower, BigDecimal upper);
}
