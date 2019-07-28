package com.atguigu.interview.enums;

import lombok.Getter;

/**
 * @author Administrator
 * @create 2019-07-28 15:54
 * 枚举就是数据版的MySQL
 */
public enum  CountryEnum {

    // 齐 楚 燕 韩 赵 魏 秦
    ONE(1,"齐"),TWO(2,"楚"),THRED(3,"燕"),FOUT(4,"韩"),FIVE(5,"赵"),SIX(6,"魏");

    @Getter
    private Integer retCode;
    @Getter private String retMessage;

    private CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum for_countryEnum(int index){

        //枚举类用法
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum : countryEnums) {
            if (countryEnum.getRetCode() == index){
                return countryEnum;
            }

        }

        return null;

    }
}
