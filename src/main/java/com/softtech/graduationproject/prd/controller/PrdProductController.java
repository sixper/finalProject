package com.softtech.graduationproject.prd.controller;

import com.softtech.graduationproject.gen.dto.RestResponse;
import com.softtech.graduationproject.prd.dto.PrdProductDetailDto;
import com.softtech.graduationproject.prd.dto.PrdProductDto;
import com.softtech.graduationproject.prd.dto.PrdProductSaveDto;
import com.softtech.graduationproject.prd.enums.PrdProductType;
import com.softtech.graduationproject.prd.service.PrdProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class PrdProductController {

    private final PrdProductService prdProductService;

    @GetMapping
    public ResponseEntity findAll(){

        List<PrdProductDto> prdProductDtoList = prdProductService.findAll();

        return ResponseEntity.ok(RestResponse.go(prdProductDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        PrdProductDto prdProductDto = prdProductService.findById(id);

        return ResponseEntity.ok(RestResponse.go(prdProductDto));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody PrdProductSaveDto prdProductSaveDto){

        PrdProductDto prdProductDto = prdProductService.save(prdProductSaveDto);

        return ResponseEntity.ok((RestResponse.go(prdProductDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        prdProductService.delete(id);

        return ResponseEntity.ok((RestResponse.empty()));
    }

    @GetMapping("/productType")
    public ResponseEntity findByProductType(@RequestParam PrdProductType prdProductType){

        List<PrdProductDto> prdProductDtoList = prdProductService.findByProductType(prdProductType);

        return ResponseEntity.ok((RestResponse.go(prdProductDtoList)));
    }

    @GetMapping("/listBetween")
    public ResponseEntity findAllBetween(@RequestParam BigDecimal lower, @RequestParam BigDecimal upper){

        List<PrdProductDto> prdProductDtoList = prdProductService.findAllBetween(lower, upper);

        return ResponseEntity.ok((RestResponse.go(prdProductDtoList)));
    }

    @GetMapping("/productDetails")
    public ResponseEntity findProductDetail(@RequestParam PrdProductType prdProductType){

        PrdProductDetailDto prdProductDetailDto = prdProductService.findProductDetail(prdProductType);

        return ResponseEntity.ok((RestResponse.go(prdProductDetailDto)));
    }
}
