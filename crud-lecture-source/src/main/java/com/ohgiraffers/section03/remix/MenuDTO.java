package com.ohgiraffers.section03.remix;

public class MenuDTO {
    private int code;
    private String name;
    private int price;
    private int categoryCode;
    private String orderblestatus;

    // 기본 생성자
    private MenuDTO(){}

    // 게터와 세터
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderblestatus() {
        return orderblestatus;
    }

    public void setOrderblestatus(String orderblestatus) {
        this.orderblestatus = orderblestatus;
    }
}
