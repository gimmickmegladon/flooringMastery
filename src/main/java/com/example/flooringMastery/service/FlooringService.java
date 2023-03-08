package com.example.flooringMastery.service;

import com.example.flooringMastery.DAO.OrderDAO;
import com.example.flooringMastery.DAO.ProductDAO;
import com.example.flooringMastery.DAO.StateTaxDAO;
import com.example.flooringMastery.model.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class FlooringService {

    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final StateTaxDAO stateTaxDAO;

    private OrderBook orderBook;
    private Products products;
    private StateTaxes stateTaxes;
    public FlooringService(){
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
        this.stateTaxDAO = new StateTaxDAO();
        this.orderBook = new OrderBook();
    }
    public void getData(String f1, String f2, String f3){
        ArrayList productList = productDAO.readAll(f2);
        ArrayList taxList = stateTaxDAO.readAll(f3);
        this.products = Products.fromList(productList);
        this.stateTaxes = StateTaxes.fromList(taxList);
    }
    public Orders getOrdersForDay(String date) {
        //look if orders are already loaded in!
        if(orderBook.getOrderBook().containsKey(date)){
            return orderBook.getOrderBook().get(date);
        }else{//if orders not in OrderBook, look for them in file via DAO
            List found = orderDAO.findOrdersForDate(date);

            //if orders aren't found as file, return Dummy Orders
            if(found.isEmpty() || found == null){
                return new Orders();
            }else{
                //if orders are found as file, read them in and store them in OrderBook
                Orders foundOrders = Orders.fromList((ArrayList<String>) found, date);
                foundOrders = populateData(foundOrders);
                this.orderBook.addDayOrders(date,foundOrders);
                return foundOrders;
            }

        }
    }
    public Orders populateData(Orders orders){
        orders.getTax(this.stateTaxes);
        orders.getProducts(this.products);
        orders.doCalculations();
        return orders;
    }
    public void populateDataForOrder(Order newOrder) {
        Product prod = this.products.getProducts().get(newOrder.getProductType());
        newOrder.setCostPerSqFoot(prod.getCostPerSqFoot());
        newOrder.setLaborCostPerSquareFoot(prod.getLaborCostPerSqFoot());
        BigDecimal taxRate = this.stateTaxes.getTaxes().get(newOrder.getState()).getTaxRate();
        newOrder.setTaxRate(Double.valueOf(String.valueOf(taxRate)));
        newOrder.calculateCosts();
    }
    public Order findOrder(String date, int id) {
        //check for order in memory(OrderBook first)
        if(orderBook.getOrderBook().containsKey(date)){
            Order toReturn = orderBook.getOrderBook().get(date).getOrderByID(id);
            if(toReturn.getOrderNum() != -1){
                return toReturn;
            }
        }
        //else, look in file system
        Orders foundOrders = Orders.fromList(orderDAO.findOrdersForDate(date), date);
        //add to OrderBook
        orderBook.addDayOrders(date, foundOrders);
        return foundOrders.getOrderByID(id);
    }
    public void removeOrder(Order toRemove, String date){
        boolean deleted = orderBook.removeOrder(toRemove,date);
        //if order was last order in the book, delete the file too!
        if(deleted){
            orderDAO.deleteFile(date);
        }else{
            orderDAO.rewriteOrders(date, orderBook.getOrdersForDate(date).getOrdersArrayList());
        }
    }
    public void replaceOrder(String date, Order order, Order editedOrder) {

        try {
            orderBook.replaceOrder(order, editedOrder, date);
            orderDAO.rewriteOrders(date, orderBook.getOrdersForDate(date).getOrdersArrayList());
        }catch(Exception e){
            // try catch for testing without an existing orderBook
        }
    }

    public Order editOrderValidation(String date, int id, Order o, String[] toEdit) {
        boolean legal = false;
        if(isNameLegal(toEdit[0])){
            if(stateTaxes.getTaxes().containsKey(toEdit[1])){
                if(products.getProducts().containsKey(toEdit[2])){
                    try{
                        BigDecimal test = new BigDecimal(toEdit[3]);
                        legal = true;
                    } catch (Exception e) {
                        //don't even throw an exception
                        //just do nothing - illegal area - handled in controller
                    }
                }
            }
        }
        if (legal) {
            //setting up a new order with the edited values
            Order editedOrder = new Order(String.valueOf(o.getOrderNum()),toEdit[0],toEdit[1],
                    String.valueOf(o.getTaxRate()), toEdit[2], toEdit[3],String.valueOf(o.getCostPerSqFoot()),
                    String.valueOf(o.getLaborCostPerSquareFoot()), String.valueOf(o.getMaterialCost()),
                    String.valueOf(o.getLaborCost()),String.valueOf(o.getTax()),String.valueOf(o.getTotal()));
            editedOrder.calculateCosts();

            return editedOrder;
        }else{
            //return dummy
            return new Order();
        }
    }

    private boolean isNameLegal(String name) {
        //https://www.w3schools.com/java/java_regex.asp
        return (!name.equals(null)) && name.matches("^[a-zA-Z0-9., ]*$");
    }
    public boolean isDateLegal(String date){
        // code lifted and adapted off of Eugene's Slack posts
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        Calendar c = Calendar.getInstance();
        String curr_date = dateFormat.format(c.getTime());
        try {
            return curr_date.compareTo(date) <= 0;
        } catch (Exception e) {
            // do nothing
        }
        return false;

    }

    public Order addValidation(String[] toAdd) {
        boolean legal = false;
        if(isDateLegal(toAdd[0])){
            if(isNameLegal(toAdd[1])){
                if(stateTaxes.getTaxes().containsKey(toAdd[2])){
                    if(products.getProducts().containsKey(toAdd[3])){
                        try{
                            BigDecimal test = new BigDecimal(toAdd[4]);
                            legal = true;
                        } catch (Exception e) {
                            //don't even throw an exception
                            //just do nothing - illegal area - handled in controller
                        }
                    }
                }
            }
        }
        if (legal) {
            //setting up a new order with the edited values
            Order newOrder = new Order(0,toAdd[1],toAdd[2],toAdd[3],toAdd[4]);

            return newOrder;
        }else{
            //return dummy
            return new Order();
        }
    }

    public void addOrder(Order newOrder, String date) {
        if(this.orderBook.getOrderBook().containsKey(date)){
            this.orderBook.getOrderBook().get(date).addOrder(newOrder);
        }else{
            this.orderBook.addOrder(newOrder,date);
        }
        this.orderDAO.rewriteOrders(date, this.orderBook.getOrdersForDate(date).getOrdersArrayList());
        }

    public void setOrders(Orders orders, String date) {
        this.orderBook = new OrderBook();
        orderBook.addDayOrders(date, orders);
    }

    public void exportAll() {
        String fn = "SampleFileData/Backup/DataExport2.txt";
        this.orderDAO.writeAll(fn, orderBook.getOrdersAsList());
    }
}
