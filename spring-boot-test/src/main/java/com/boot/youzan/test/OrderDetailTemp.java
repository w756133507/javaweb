package com.boot.youzan.test;


public class OrderDetailTemp {

   private String item_cd;

   private String price;

    public OrderDetailTemp(String item_cd, String price) {
        this.item_cd = item_cd;
        this.price = price;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public void setItem_cd(String item_cd) {
        this.item_cd = item_cd;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
