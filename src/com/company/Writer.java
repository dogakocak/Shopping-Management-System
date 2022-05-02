package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public void save(ProductList list,String type){
        String path = "src\\com\\company\\Inventory.txt";
        if (type.equals("Products")) {
            path = "src\\com\\company\\Products.txt";
        }

        try {
            FileWriter saveList = new FileWriter(path);
            String str = list.toString();
            saveList.write(str);
            saveList.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
