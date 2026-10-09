package com.mycontent.jizhang1.utils;

import java.math.BigDecimal;

public class FloatUtils {
    /*进行除法运算，保留四位小数点*/
    public static float div(float v1,float v2){
        float v3=v1/v2;
        BigDecimal b1 = new BigDecimal(v3);    //BigDecimal可以设置保留位数
        float val = b1.setScale(4, 4).floatValue();  //setScale函数第一个参数是保留4位，第二个参数是四舍五入
        return val;
    }

    //将浮点数类型转换成百分比显示形式
    public static String radioToPercent(float val){
        float v=val*100;
        BigDecimal b1 = new BigDecimal(v);
        float v1 = b1.setScale(2, 4).floatValue();
        String per=v1+"%";
        return per;
    }
}
