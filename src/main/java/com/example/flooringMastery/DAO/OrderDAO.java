package com.example.flooringMastery.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO {
    //method used to read in all orderfiles for storing them all in OrderBook
    @Override
    public ArrayList<String> readAll(String filename) {
        //for file in SampleFileData/Orders/readAll
        File file = new File(filename);
        FileReader fileReader;
        List products = new ArrayList();
        try{
            fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = "";
            while(line != null){
                line = br.readLine();
                if(line != null){
                    products.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            e.printStackTrace();
        }
        return (ArrayList<String>) products;
    }

    @Override
    public void writeAll(String filename, List<String> list) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile(); // if file doesn't exist, create it
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //file (now) exists, (over)write to it
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            //write header!
            bw.write("Date,OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total"); // consume header line
            for (String s : list) {
                bw.write("\n" + s);
            }
            bw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Find Orderfile by queried Date!
    public ArrayList<String> findOrdersForDate(String date){
        ArrayList<String> found = new ArrayList();
        String filename = "SampleFileData/Orders/Orders_"+date+".txt";
        //System.out.println("FILENAME: "+filename);
        File file = new File(filename);
        if(!file.exists()){
            return found; // if file doesn't exist, return empty arrayList
        }else{
            //file exists, do stuff
            FileReader fr;
            try{
                fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine(); // consume header line
                while(line != null){
                    line = br.readLine();
                    if(line != null){
                        found.add(line);
                    }
                }
                return found;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void rewriteOrders(String date, List<String> array) {
        String filename = "SampleFileData/Orders/Orders_"+date+".txt";
        //System.out.println("FILENAME: "+filename);
        File file = new File(filename);
        if(!file.exists()){
            try{
                file.createNewFile(); // if file doesn't exist, create it
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //file (now) exists, write to it
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            //write header!
            bw.write("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total"); // consume header line
            for (String s: array) {
                bw.write("\n"+s);
            }
            bw.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public void deleteFile(String date) {
        String filename = "SampleFileData/Orders/newOrders_"+date+".txt";
        File file = new File(filename);
        if(file.exists()){
            file.delete();
        }
    }

}
