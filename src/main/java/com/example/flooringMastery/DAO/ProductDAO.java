package com.example.flooringMastery.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO {
    @Override
    public ArrayList<String> readAll(String filename) {
        File file = new File(filename);
        FileReader fileReader;
        String[] line;
        List products = new ArrayList();
        try{
            fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            br.readLine(); //CONSUME EMPTY LINE
            String str = "";
            while(str != null){
                str = br.readLine();
                if(str != null){
                    line = str.split(",");
                    String type = line[0].trim();
                    String cost = line[1].trim();
                    String labor = line[2].trim();

                    products.add(type);
                    products.add(cost);
                    products.add(labor);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            e.printStackTrace();
        }
        return (ArrayList<String>) products;
    }

}
