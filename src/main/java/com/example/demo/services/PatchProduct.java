package com.example.demo.services;

import java.math.BigDecimal;

public class PatchProduct {
    private String productname;
    private String description;
    private BigDecimal price;

    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    } 

    
}
