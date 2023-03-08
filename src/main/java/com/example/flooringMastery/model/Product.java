package com.example.flooringMastery.model;

import java.math.BigDecimal;

public class Product {
    private String productType;
    private BigDecimal costPerSqFoot;
    private BigDecimal laborCostPerSqFoot;

    public Product(String sProdType, String sCost, String sLabor) {
        this.productType = sProdType;
        this.costPerSqFoot = new BigDecimal(sCost);
        this.laborCostPerSqFoot = new BigDecimal(sLabor);
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSqFoot() {
        return costPerSqFoot;
    }

    public void setCostPerSqFoot(BigDecimal costPerSqFoot) {
        this.costPerSqFoot = costPerSqFoot;
    }

    public BigDecimal getLaborCostPerSqFoot() {
        return laborCostPerSqFoot;
    }

    public void setLaborCostPerSqFoot(BigDecimal laborCostPerSqFoot) {
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }
}
