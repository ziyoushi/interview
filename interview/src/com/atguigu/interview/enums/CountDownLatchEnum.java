package com.atguigu.interview.enums;

import lombok.Getter;

/**
 * @author Administrator
 * @create 2019-07-01 8:07
 *
 * 正确使用枚举类
 * 就是缓存 数据
 */
public enum CountDownLatchEnum {

    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"韩"),
    FIVE(5,"赵"),
    SIX(6,"魏");

    @Getter
    private Integer retCode;
    @Getter private String retMsg;

    CountDownLatchEnum(Integer retCode,String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public static CountDownLatchEnum forEachEnum(int index){
        CountDownLatchEnum[] enums = CountDownLatchEnum.values();
        for(CountDownLatchEnum countEnum : enums){
            if (countEnum.getRetCode() == index){
                return countEnum;
            }
        }

        return null;
    }
}
