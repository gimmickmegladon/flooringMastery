package com.example.flooringMastery.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Products {

    private HashMap<String, Product> products;
    public Products() {

    }
    public Products(HashMap<String, Product> prods){
        this.products = prods;
    }

    public HashMap<String,Product> getProducts(){
        return products;
    }
    public static Products fromList(ArrayList<String> prdList) {
        HashMap<String, Product> prods = new HashMap<>();
        for (int i = 0; i < prdList.size(); i++) {
            String type = prdList.get(i);
            i++;
            String cost = prdList.get(i);
            i++;
            String labor = prdList.get(i);
            Product newProd = new Product(type,cost,labor);
            prods.put(type, newProd);
        }
        return new Products(prods);
    }

}
