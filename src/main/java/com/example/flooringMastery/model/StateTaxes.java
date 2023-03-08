package com.example.flooringMastery.model;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.HashMap;

public class StateTaxes {
    private final HashMap<String, StateTax> taxes;

    public StateTaxes(HashMap<String, StateTax> txs){
        this.taxes = txs;
    }
    public HashMap<String, StateTax> getTaxes(){
        return taxes;
    }

    public static StateTaxes fromList(ArrayList<String> taxList) {
        HashMap<String, StateTax> taxes = new HashMap<>();
        for (int i = 0; i < taxList.size(); i++) {
            String abbr = taxList.get(i);
            i++;
            String name = taxList.get(i);
            i++;
            String rate = taxList.get(i);
            StateTax newTax = new StateTax(abbr,name,rate);
            taxes.put(abbr, newTax);
        }
        return new StateTaxes(taxes);
    }
}
