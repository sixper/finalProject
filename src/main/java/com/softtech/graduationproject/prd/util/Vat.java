package com.softtech.graduationproject.prd.util;

import com.softtech.graduationproject.prd.enums.PrdProductType;

import java.math.BigDecimal;

public class Vat {

    private static BigDecimal food = BigDecimal.valueOf(1);
    private static BigDecimal stationary = BigDecimal.valueOf(8);
    private static BigDecimal clothing = BigDecimal.valueOf(8);
    private static BigDecimal cleaning = BigDecimal.valueOf(18);
    private static BigDecimal technology = BigDecimal.valueOf(18);
    private static BigDecimal other = BigDecimal.valueOf(18);

    public static BigDecimal getVat(PrdProductType prdProductType){

        String type = prdProductType.toString();
        BigDecimal vat = null;

        switch (type){

            case "FOOD":
                vat = food;
                break;

            case "STATIONARY":
                vat = stationary;
                break;

            case "CLOTHING":
                vat = clothing;
                break;

            case "CLEANING":
                vat = cleaning;
                break;

            case "TECHNOLOGY":
                vat = technology;
                break;

            case "OTHER":
                vat = other;
                break;
        }

        return vat;
    }

    public static void setVat(PrdProductType prdProductType, BigDecimal vat){

        String type = prdProductType.toString();

        switch (type){

            case "FOOD":
                food = vat;
                break;

            case "STATIONARY":
                stationary = vat;
                break;

            case "CLOTHING":
                clothing = vat;
                break;

            case "CLEANING":
                cleaning = vat;
                break;

            case "TECHNOLOGY":
                technology = vat;
                break;

            case "OTHER":
                other = vat;
                break;
        }




    }
}
