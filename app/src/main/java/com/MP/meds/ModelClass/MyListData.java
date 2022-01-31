package com.MP.meds.ModelClass;

import android.widget.TextView;

public class MyListData {

    private String recycle_company;
    private String recycle_Medicine;
    private String recycle_qty;
    private String recycle_discount;
    private String recycle_code;


    public MyListData(String recycle_company, String recycle_Medicine,String recycle_code, String recycle_qty, String recycle_discount) {
        this.recycle_company = recycle_company;
        this.recycle_Medicine = recycle_Medicine;
        this.recycle_qty = recycle_qty;
        this.recycle_discount = recycle_discount;
        this.recycle_code = recycle_code;
    }

    public String getRecycle_code() {
        return recycle_code;
    }

    public void setRecycle_code(String recycle_code) {
        this.recycle_code = recycle_code;
    }

    public String getRecycle_company() {
        return recycle_company;
    }

    public void setRecycle_company(String recycle_company) {
        this.recycle_company = recycle_company;
    }

    public String getRecycle_Medicine() {
        return recycle_Medicine;
    }

    public void setRecycle_Medicine(String recycle_Medicine) {
        this.recycle_Medicine = recycle_Medicine;
    }

    public String getRecycle_qty() {
        return recycle_qty;
    }

    public void setRecycle_qty(String recycle_qty) {
        this.recycle_qty = recycle_qty;
    }

    public String getRecycle_discount() {
        return recycle_discount;
    }

    public void setRecycle_discount(String recycle_discount) {
        this.recycle_discount = recycle_discount;
    }
}