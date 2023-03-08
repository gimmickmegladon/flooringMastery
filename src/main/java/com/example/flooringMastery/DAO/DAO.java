package com.example.flooringMastery.DAO;

import java.util.ArrayList;
import java.util.List;

public interface DAO {

    //read from file
    ArrayList<String> readAll(String filename);

    //write to file
    default void writeAll(String filename, List<String> list) {
        //do nothing
    }
}

