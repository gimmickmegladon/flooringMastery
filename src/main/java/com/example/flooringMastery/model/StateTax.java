package com.example.flooringMastery.model;

import java.math.BigDecimal;

public class StateTax {
    private String stateName;
    private String abbreviation;
    private BigDecimal taxRate;

    public StateTax(String sAbbr, String sName, String sTaxRate) {
        this.abbreviation = sAbbr;
        this.stateName = sName;
        this.taxRate = new BigDecimal(sTaxRate);
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}
