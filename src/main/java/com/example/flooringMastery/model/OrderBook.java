package com.example.flooringMastery.model;


import java.lang.reflect.Array;
import java.util.*;

public class OrderBook {

    private final Map<String, Orders> orderBook = new HashMap<String, Orders>();


    public Map<String, Orders> getOrderBook(){
        return this.orderBook;
    }


    public Orders getOrdersForDate(String date){
        Orders found = this.orderBook.get(date);
        return found;
    }
    public void addOrder(Order newOrder, String date){
        if(this.orderBook.containsKey(date)){
            this.orderBook.get(date).addOrder(newOrder);
        }else{
            Orders newOrders = new Orders();
            newOrders.addOrder(newOrder);
            this.orderBook.put(date, newOrders);
        }
    }

    public void addDayOrders(String date, Orders orders){
        this.orderBook.put(String.valueOf(date), orders);
    }

    public boolean removeOrder(Order toRemove, String date) {
        Orders orders = this.orderBook.get(date);
        orders.getOrders().remove(toRemove);
        //if the last order was deleted, remove key from OB to avoid empty keys and send bool
        if(orders.getOrders().size() == 0){
            this.orderBook.remove(date);
            return true;
        }
        return false;
    }

    public void replaceOrder(Order old, Order newOrder, String date) {
        removeOrder(old, date);

        Orders newOrders = this.orderBook.get(date).addOrder(newOrder);
        this.orderBook.replace(date, newOrders);
    }

    public List<String> getOrdersAsList() {
        List<String> asList = new ArrayList<>();
        String[] keySet = orderBook.keySet().toArray(new String[0]);
        for (int i = 0; i < keySet.length; i++) {
            ArrayList<String> asListDate = new ArrayList<>();
            Orders oneDate = orderBook.get(keySet[i]);
            for (int j = 0; j < oneDate.getOrders().size(); j++) {
                asListDate.add(keySet[i] + oneDate.getOrders().get(j).toString());
                //System.out.println(keySet[i] + oneDate.getOrders().get(j).toString());
            }
            asList.addAll(asListDate);
        }
        return asList;
    }

}
