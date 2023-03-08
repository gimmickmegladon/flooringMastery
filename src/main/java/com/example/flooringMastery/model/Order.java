package com.example.flooringMastery.model;

import java.math.BigDecimal;

public class Order {

    private int orderNum;
    private String customerName;
    private String state;
    //taxRate should be a double, was initially String
    private Double taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSqFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;


    //dummy constructor
    public Order(){
        this.orderNum = -1; //for signifying non-existent orders
    }


    //addOrder constructor
    public Order(int orderN, String custName, String state, String prodType, String area){
        this.orderNum = orderN;
        this.customerName = custName;
        this.state = state;
        this.productType = prodType;
        this.area = new BigDecimal(area);
    }

    //readFromFile constructor
    public Order(String orderN, String custName, String state, String taxRate, String prodType, String area,
                 String costPerSqFoot, String laborCostPerSquareFoot, String materialCost, String laborCost, String tax, String total){
        this.orderNum = Integer.valueOf(orderN);
        this.customerName = custName;
        this.state = state;
        this.taxRate = Double.valueOf(taxRate);
        this.productType = prodType;
        this.area = new BigDecimal(area);
        this.costPerSqFoot = new BigDecimal(costPerSqFoot);
        this.laborCostPerSquareFoot = new BigDecimal(laborCostPerSquareFoot);
        this.materialCost = new BigDecimal(materialCost);
        this.laborCost = new BigDecimal(laborCost);
        this.tax = new BigDecimal(tax);
        this.total = new BigDecimal(total);
    }
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqFoot() {
        return costPerSqFoot;
    }

    public void setCostPerSqFoot(BigDecimal costPerSqFoot) {
        this.costPerSqFoot = costPerSqFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void calculateCosts(){
        //MaterialCost = (Area * CostPerSquareFoot)
        this.materialCost = this.area.multiply(this.costPerSqFoot);

        //LaborCost = (Area * LaborCostPerSquareFoot)
        this.laborCost = this.area.multiply(this.laborCostPerSquareFoot);

        //Tax = (MaterialCost + LaborCost) * (TaxRate/100)
        BigDecimal left = this.materialCost.add(laborCost);
        this.tax = left.multiply(BigDecimal.valueOf(this.taxRate/100));

        //Total = (MaterialCost + LaborCost + Tax)
        this.total = this.materialCost.add(this.laborCost).add(this.tax);
    }

    @Override
    public String toString() {
        return ""+orderNum +
                ","+ customerName+
                ","+ state+
                ","+ taxRate+
                ","+ productType+
                ","+ area +
                ","+ costPerSqFoot+
                ","+ laborCostPerSquareFoot+
                ","+ materialCost+
                ","+ laborCost+
                ","+ tax+
                ","+ total;
    }
}
