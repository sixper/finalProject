package com.softtech.graduationproject.prd.service.entityservice;

import com.softtech.graduationproject.gen.service.BaseEntityService;
import com.softtech.graduationproject.prd.dao.PrdProductDao;
import com.softtech.graduationproject.prd.entity.PrdProduct;
import com.softtech.graduationproject.prd.enums.PrdProductType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PrdProductEntityService extends BaseEntityService<PrdProduct, PrdProductDao> {

    public PrdProductEntityService(PrdProductDao dao) {
        super(dao);
    }

    public List<PrdProduct> findByProductType(PrdProductType prdProductType){

        return getDao().findByPrdProductType(prdProductType);
    }

    public List<PrdProduct> findAllByPriceBetween(BigDecimal lower, BigDecimal upper){

        return getDao().findAllByPriceBetween(lower, upper);
    }
}
