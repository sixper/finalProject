package com.softtech.graduationproject.prd.service;

import com.softtech.graduationproject.gen.enums.GenErrorMessage;
import com.softtech.graduationproject.gen.exceptions.EntityNotFoundException;
import com.softtech.graduationproject.gen.exceptions.InvalidProductException;
import com.softtech.graduationproject.gen.exceptions.InvalidProductPriceException;
import com.softtech.graduationproject.gen.exceptions.InvalidVatException;
import com.softtech.graduationproject.prd.converter.PrdProductMapper;
import com.softtech.graduationproject.prd.dto.PrdProductDetailDto;
import com.softtech.graduationproject.prd.dto.PrdProductDto;
import com.softtech.graduationproject.prd.dto.PrdProductSaveDto;
import com.softtech.graduationproject.prd.entity.PrdProduct;
import com.softtech.graduationproject.prd.enums.PrdProductErrorMessage;
import com.softtech.graduationproject.prd.enums.PrdProductType;
import com.softtech.graduationproject.prd.service.entityservice.PrdProductEntityService;

import com.softtech.graduationproject.prd.util.Vat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PrdProductService {

    private final PrdProductEntityService prdProductEntityService;

    public List<PrdProductDto> findAll(){

        List<PrdProduct> prdProductList = prdProductEntityService.findAll();

        if(prdProductList.isEmpty()){
            throw new EntityNotFoundException(GenErrorMessage.ENTITIES_NOT_FOUND);
        }

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

    public PrdProductDto update(Long id, PrdProductSaveDto prdProductSaveDto) {

        if(prdProductEntityService.existsById(id)){

            PrdProduct prdProduct = PrdProductMapper.INSTANCE.convertToPrdProduct(prdProductSaveDto);
            prdProduct.setId(id);

            validateProduct(prdProduct);
            calculateVat(prdProduct);

            prdProduct = prdProductEntityService.save(prdProduct);

            return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);

        }else{
            throw new EntityNotFoundException(GenErrorMessage.ENTITY_NOT_FOUND);
        }

    }

    public PrdProductDto updatePrice(Long id, BigDecimal price) {

        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(id);

        if(price.compareTo(BigDecimal.ZERO) > 0){

            prdProduct.setTaxFreePrice(price);
            calculateVat(prdProduct);
            prdProduct = prdProductEntityService.save(prdProduct);

            return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);

        }else{
            throw new InvalidProductPriceException(PrdProductErrorMessage.INVALID_PRODUCT_PRICE);
        }

    }

    @Transactional
    public List<PrdProductDto> updateVat(PrdProductType prdProductType, BigDecimal vatRate) {

        if(vatRate.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidVatException(PrdProductErrorMessage.INVALID_PRODUCT_VAT);

        Vat.setVat(prdProductType, vatRate);

        List<PrdProduct> prdProductList = prdProductEntityService.findByProductType(prdProductType);

        for(PrdProduct prdProduct : prdProductList){

            prdProduct.setVatRate(vatRate);
            calculateVat(prdProduct);

            prdProductEntityService.save(prdProduct);
        }

        return PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);
    }

    public void delete(Long id){

        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(id);

        prdProductEntityService.delete(prdProduct);
    }

    public void validateProduct(PrdProduct prdProduct){

        String productType = prdProduct.getPrdProductType().toString();
        String productName = prdProduct.getProductName();
        BigDecimal taxFreePrice = prdProduct.getTaxFreePrice();

        if(productType == null || productName == null || taxFreePrice == null || productName.length() > 30){

            throw new InvalidProductException(PrdProductErrorMessage.INVALID_PRODUCT);

        }else if(taxFreePrice.compareTo(BigDecimal.ZERO) <= 0){

            throw new InvalidProductPriceException(PrdProductErrorMessage.INVALID_PRODUCT_PRICE);
        }
    }

    public void calculateVat(PrdProduct prdProduct){

        BigDecimal vatRate = Vat.getVat(prdProduct.getPrdProductType()).divide(BigDecimal.valueOf(100));

        prdProduct.setVatRate(vatRate.multiply(BigDecimal.valueOf(100)));

        BigDecimal tax = prdProduct.getTaxFreePrice().multiply(vatRate);

        prdProduct.setPrice(tax.add(prdProduct.getTaxFreePrice()));
    }

    public List<PrdProductDto> findByProductType(PrdProductType prdProductType) {

        List<PrdProduct> prdProductList = prdProductEntityService.findByProductType(prdProductType);

        if(prdProductList.isEmpty()){
            throw new EntityNotFoundException(PrdProductErrorMessage.PRODUCT_TYPE_NOT_FOUND);
        }else{
            System.out.println("Test");
        }

        return PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);
    }

    public List<PrdProductDto> findAllBetween(BigDecimal lower, BigDecimal upper) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAllByPriceBetween(lower, upper);

        if(prdProductList.isEmpty()){
            throw new EntityNotFoundException(PrdProductErrorMessage.PRODUCT_PRICE_RANGE_NOT_FOUND);
        }

        return PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);
    }

    public PrdProductDetailDto findProductDetail(PrdProductType prdProductType) {

        PrdProductDetailDto prdProductDetailDto = new PrdProductDetailDto();

        List<PrdProductDto> prdProductDtoList = findByProductType(prdProductType);

        BigDecimal vatRate = Vat.getVat(prdProductType);

        prdProductDetailDto.setVatRate(vatRate);
        prdProductDetailDto.setPrdProductType(prdProductType);

        BigDecimal min = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal average = BigDecimal.ZERO;
        Long count = 0L;
        for(PrdProductDto element : prdProductDtoList){

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
