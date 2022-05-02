package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileReader {
    public void readProduct(ProductList productList){
        try {
            File productsFile = new File("src\\com\\company\\Products.txt");
            Scanner reader = new Scanner(productsFile);
            while (reader.hasNextLine()){
                StringTokenizer stringTokenizer = new StringTokenizer(reader.nextLine(),",");
                int id=Integer.parseInt(stringTokenizer.nextToken());
                String name=stringTokenizer.nextToken();
                double purchasePrice=Double.parseDouble(stringTokenizer.nextToken());
                double sellingPrice = Double.parseDouble(stringTokenizer.nextToken());
                productList.addItem(new Product(id,name,purchasePrice,sellingPrice));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void readInventory(ProductList inventoryList){
        try {
            File inventoryFile = new File("src\\com\\company\\Inventory.txt");
            Scanner reader = new Scanner(inventoryFile);
            while (reader.hasNextLine()){
                StringTokenizer stringTokenizer = new StringTokenizer(reader.nextLine(),",");
                int id=Integer.parseInt(stringTokenizer.nextToken());
                String name=stringTokenizer.nextToken();
                double purchasePrice=Double.parseDouble(stringTokenizer.nextToken());
                double sellingPrice = Double.parseDouble(stringTokenizer.nextToken());
                inventoryList.addItem(new Product(id,name,purchasePrice,sellingPrice));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
