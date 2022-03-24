package com.softtech.graduationproject.prd.converter;

import com.softtech.graduationproject.prd.dto.PrdProductDto;
import com.softtech.graduationproject.prd.dto.PrdProductSaveDto;
import com.softtech.graduationproject.prd.entity.PrdProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrdProductMapper {

    PrdProductMapper INSTANCE = Mappers.getMapper(PrdProductMapper.class);

    List<PrdProductDto> convertToPrdProductDtoList(List<PrdProduct> prdProductList);

    PrdProductDto convertToPrdProductDto(PrdProduct prdProduct);

    PrdProduct convertToPrdProduct(PrdProductSaveDto prdProductSaveDto);
}
