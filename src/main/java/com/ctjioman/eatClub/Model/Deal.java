package com.ctjioman.eatClub.Model;

public class Deal {
    private String objectId;
    private String discount;
    private String dineIn;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDineIn() {
        return dineIn;
    }

    public void setDineIn(String dineIn) {
        this.dineIn = dineIn;
    }

    public String getLightning() {
        return lightning;
    }

    public void setLightning(String lightning) {
        this.lightning = lightning;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getQtyLeft() {
        return qtyLeft;
    }

    public void setQtyLeft(String qtyLeft) {
        this.qtyLeft = qtyLeft;
    }

    private String lightning;
    private String open;
    private String close;
    private String qtyLeft;

}
