package com.example.flooringMastery.DAO;

import java.io.*;
import java.util.ArrayList;

public class StateTaxDAO implements DAO {
    @Override
    public ArrayList<String> readAll(String filename) {
        File file = new File(filename);
        FileReader fileReader;
        String[] line;
        ArrayList taxes = new ArrayList();
        try{
            fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            br.readLine(); //CONSUME EMPTY LINE
            String str = "";
            while(str != null){
                str = br.readLine();
                if(str != null){
                    line = str.split(",");

                    String abbr = line[0].trim();
                    String name = line[1].trim();
                    String rate = line[2].trim();


                    taxes.add(abbr);
                    taxes.add(name);
                    taxes.add(rate);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            e.printStackTrace();
        }
        return taxes;
    }

}
