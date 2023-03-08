package com.example.flooringMastery.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Orders {
    private final List<Order> orders;
    private String date;

    //dummy constructor
    public Orders(){
        this.orders = new ArrayList<>();
        this.date = "";
    }

    //create orders from ArrayList<Order>
    public Orders(List<Order> orders, String date){
        this.orders = orders;
        this.date = date;
        //getTax();
        doCalculations();
    }

    //addOrder via add Order main Menu functionality
    public void addOrder(String custName, String state, String prodType, String area){
        Order toAdd = new Order(getNextNum(), custName, state, prodType, area);
        this.orders.add(toAdd);
    }



    //get next available order number
    public int getNextNum(){
        int highest = 0;
        for(Order o:this.orders){
            if(o.getOrderNum() > highest){
                highest = o.getOrderNum();
            }
        }
        return highest+1;

    }

    //read in orderfile and create Orders from an ArrayList
    public static Orders fromList(ArrayList<String> found, String date) {
        Orders orders = new Orders();
        orders.setDate(date);
        for (int i = 0; i < found.size(); i++) {
            Order newOrder;
            String line = found.get(i);
            String []o = line.split(",");

            newOrder = new Order(o[0],o[1],o[2],o[3],o[4],o[5],o[6],o[7],o[8],o[9],o[10],o[11]);
            orders.getOrders().add(newOrder);
        }
        return orders;
    }


    public String getDate(){
        return this.date;
    }
    private void setDate(String date) {
        this.date = date;
    }
    public List<Order> getOrders(){
        return orders;
    }


    //find order by ID
    public Order getOrderByID(int ID){
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getOrderNum() == ID){
                return orders.get(i);
            }
        }
        return new Order();
    }

    //to be used when reading in taxes from files
    public void getTax(StateTaxes taxes){
        for (Order o:this.orders) {
            o.getProductType();
        }
    }

    //to be used when reading in products from files
    public void getProducts(Products prods){

    }

    //one taxes and products are loaded, calculate rest of fields to get total cost
    public void doCalculations(){
        for(Order o:this.orders){
            o.calculateCosts();
        }
    }

    public ArrayList<String> getOrdersArrayList() {
        ArrayList<String> newList = new ArrayList<>();
        for (Order o :this.getOrders()) {
            newList.add(o.toString());
        }
        return newList;
    }

    public Orders addOrder(Order newOrder) {
        this.orders.add(newOrder);
        return this;
    }
}
