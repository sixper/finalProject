package com.softtech.graduationproject.prd.service;

import com.softtech.graduationproject.gen.exceptions.InvalidProductException;
import com.softtech.graduationproject.gen.exceptions.InvalidProductPriceException;
import com.softtech.graduationproject.prd.converter.PrdProductMapper;
import com.softtech.graduationproject.prd.dto.PrdProductDetailDto;
import com.softtech.graduationproject.prd.dto.PrdProductDto;
import com.softtech.graduationproject.prd.dto.PrdProductSaveDto;
import com.softtech.graduationproject.prd.entity.PrdProduct;
import com.softtech.graduationproject.prd.enums.PrdProductErrorMessage;
import com.softtech.graduationproject.prd.enums.PrdProductType;
import com.softtech.graduationproject.prd.service.entityservice.PrdProductEntityService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrdProductService {

    private final PrdProductEntityService prdProductEntityService;

    public List<PrdProductDto> findAll(){

        List<PrdProduct> prdProductList = prdProductEntityService.findAll();

        return PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);
    }

    public PrdProductDto findById(Long id){

        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(id);

        return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);
    }

    public PrdProductDto save(PrdProductSaveDto prdProductSaveDto){

        PrdProduct prdProduct = PrdProductMapper.INSTANCE.convertToPrdProduct(prdProductSaveDto);

        validateProduct(prdProduct);
        calculateVat(prdProduct);

        prdProduct = prdProductEntityService.save(prdProduct);

        return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);
    }

    public void delete(Long id){

        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(id);

        prdProductEntityService.delete(prdProduct);
    }

    public void validateProduct(PrdProduct prdProduct){

        String productType = prdProduct.getPrdProductType().toString();
        String productName = prdProduct.getProductName();
        BigDecimal taxFreePrice = prdProduct.getTaxFreePrice();

        if(productType == null || productName == null || taxFreePrice == null){

            throw new InvalidProductException(PrdProductErrorMessage.INVALID_PRODUCT);

        }else if(taxFreePrice.compareTo(BigDecimal.ZERO) < 0){

            throw new InvalidProductPriceException(PrdProductErrorMessage.INVALID_PRODUCT_PRICE);
        }
    }

    public void calculateVat(PrdProduct prdProduct){

        int VAT = prdProduct.getPrdProductType().getVat();

        BigDecimal tax = prdProduct.getTaxFreePrice().multiply(BigDecimal.valueOf(VAT)).divide(BigDecimal.valueOf(100));

        prdProduct.setVAT(tax);

        prdProduct.setPrice(tax.add(prdProduct.getTaxFreePrice()));
    }

    public List<PrdProductDto> findByProductType(PrdProductType prdProductType) {

        List<PrdProduct> prdProductList = prdProductEntityService.findByProductType(prdProductType);

        return PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);
    }

    public List<PrdProductDto> findAllBetween(BigDecimal lower, BigDecimal upper) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAllByPriceBetween(lower, upper);

        return PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);
    }

    public PrdProductDetailDto findProductDetail(PrdProductType prdProductType) {

        List<PrdProduct> prdProductList = prdProductEntityService.findByProductType(prdProductType);

        return setDetails(prdProductList);
    }

    public PrdProductDetailDto setDetails(List<PrdProduct> prdProductList){

        PrdProductDetailDto prdProductDetailDto = new PrdProductDetailDto();

        int vatRate = prdProductList.get(0).getPrdProductType().getVat();

        prdProductDetailDto.setVatRate(BigDecimal.valueOf(vatRate));
        prdProductDetailDto.setPrdProductType(prdProductList.get(0).getPrdProductType());

        BigDecimal min = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal average = BigDecimal.ZERO;
        Long count = 0L;
        for(PrdProduct element : prdProductList){

            BigDecimal price = element.getPrice();

            if(price.compareTo(max) > 0){
                max = price;
            }else if(price.compareTo(min) > 0){
                min = price;
            }

            average.add(price);
            count++;
        }

        prdProductDetailDto.setMaximumPrice(min);
        prdProductDetailDto.setMaximumPrice(max);
        prdProductDetailDto.setAveragePrice(average);
        prdProductDetailDto.setProductCount(count);

        return prdProductDetailDto;
    }
}
