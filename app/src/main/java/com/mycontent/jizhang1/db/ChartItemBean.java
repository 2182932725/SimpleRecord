package com.mycontent.jizhang1.db;

public class ChartItemBean {
    int sImageId;
    String type;
    float radio;   //所占比例
    float totalMoney;   //此项的总钱数

    public ChartItemBean() {
    }

    public void setsImageId(int sImageId) {
        this.sImageId = sImageId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getsImageId() {
        return sImageId;
    }

    public String getType() {
        return type;
    }

    public float getRadio() {
        return radio;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public ChartItemBean(int sImageId, String type, float radio, float totalMoney) {
        this.sImageId = sImageId;
        this.type = type;
        this.radio = radio;
        this.totalMoney = totalMoney;
    }
}
