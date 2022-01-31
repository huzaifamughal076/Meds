package com.MP.meds;

public class MedsData {

    String Company_name;
    String Product_code;

    public MedsData(String company_name, String product_code) {
        Company_name = company_name;
        Product_code = product_code;
    }


    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public String getProduct_code() {
        return Product_code;
    }

    public void setProduct_code(String product_code) {
        Product_code = product_code;
    }
}
