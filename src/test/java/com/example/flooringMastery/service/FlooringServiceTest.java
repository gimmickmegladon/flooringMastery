package com.example.flooringMastery.service;

import com.example.flooringMastery.FlooringMasteryApplication;
import com.example.flooringMastery.model.Order;
import com.example.flooringMastery.model.OrderBook;
import com.example.flooringMastery.model.Orders;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FlooringServiceTest {

    @Test
    void orderCalculationsTest(){
        Order o = new Order(1, "nameName", "CA","Tile","249.0");
        o.setTaxRate(25.0);
        o.setCostPerSqFoot(BigDecimal.valueOf(3.50));
        o.setLaborCostPerSquareFoot(BigDecimal.valueOf(4.15));
        o.calculateCosts();
        assertEquals(new BigDecimal("476.21250"), o.getTax());
        assertEquals(new BigDecimal("2381.06250") ,o.getTotal());
    }
    @Test
    void getOrdersForDayTest() {
        FlooringService s = new FlooringService();
        OrderBook ob = new OrderBook();
        Orders newOrders = new Orders();
        Order o = new Order(1, "nameName", "TX","Carpet","230.2");
        newOrders.addOrder(o);
        ob.getOrderBook().put("06062023", newOrders);
        s.setOrders(newOrders, "06062023");
        assertEquals("TX",s.findOrder("06062023", 1).getState());
    }

    @Test
    void findOrder() {
        FlooringService s = new FlooringService();
        OrderBook ob = new OrderBook();
        Orders newOrders = new Orders();
        Order o = new Order(1, "nameName", "TX","Carpet","230.2");
        newOrders.addOrder(o);
        ob.getOrderBook().put("06062023", newOrders);
        s.setOrders(newOrders, "06062023");
        assertEquals(o,s.findOrder("06062023", 1));
    }

    @Test
    void replaceOrder() {
        FlooringService s = new FlooringService();
        OrderBook ob = new OrderBook();
        Orders newOrders = new Orders();
        Order o = new Order(1, "nameName", "TX","Carpet","230.2");
        Order o2 = new Order(2, "name222", "WA","Tiles","250");
        o.setTaxRate(9.5);
        newOrders.addOrder(o);
        ob.getOrderBook().put("06062023", newOrders);
        s.setOrders(newOrders, "06062023");

    }

    @Test
    void isDateLegal() {
        FlooringService s = new FlooringService();
        assertTrue(s.isDateLegal("03102023"));
        assertFalse(s.isDateLegal("03032023"));
        assertFalse(s.isDateLegal(""));
    }
}