package com.company;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws ParseException {
        ProductList productList = new ProductList();
        ProductList inventoryList = new ProductList();
        ProductList transaction = new ProductList();
        FileReader fileReader = new FileReader();
        Writer writer = new Writer();
        fileReader.readProduct(productList);
        fileReader.readInventory(inventoryList);
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            printMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    inventoryList.printProduct();
                    System.out.println("Select ID you want to sell:");
                    int sellID= scanner.nextInt();
                    System.out.println("Enter quantity:");
                    int sellQuantity= scanner.nextInt();
                    inventoryList.sellProduct(sellID,sellQuantity,transaction);
                    inventoryList.setID();
                    writer.save(inventoryList,"Inventory");

                }
                case 2 ->{
                    printManageInventoryMenu();
                    option = scanner.nextInt();
                    switch (option){ // nested switch
                        case 1 -> {
                            productList.printProduct();
                            System.out.println("Select ID you want to buy:");
                            int buyID = scanner.nextInt();
                            System.out.println("Enter quantity:");
                            int buyQuantity = scanner.nextInt();
                            inventoryList.buyProduct(productList,buyID,buyQuantity);
                            inventoryList.setID();
                            productList.setID();
                            writer.save(inventoryList,"Inventory");
                            writer.save(productList,"Products");

                        }
                        case 2 ->{
                            inventoryList.printProduct();
                            System.out.println("Select ID you want to update:");
                            int updateID=scanner.nextInt();
                            System.out.println("Enter new stock:");
                            int updateStock=scanner.nextInt();
                            inventoryList.updateProduct(updateID,updateStock);
                        }

                        case 3 ->{
                            inventoryList.printProduct();
                            System.out.println("Select ID you want to delete:");
                            int deleteID=scanner.nextInt();
                            inventoryList.remove(deleteID);
                            inventoryList.setID();
                            writer.save(inventoryList,"Inventory");
                        }
                    }
                }
                case 3 -> {
                    printManageProductsMenu();
                    option = scanner.nextInt();
                    switch (option){
                        case 1 -> productList.printProduct();
                        case 2 -> {
                            try {
                                System.out.println("Purchase Price:");
                                double purchasePrice = scanner.nextDouble();
                                System.out.println("Selling Price:");
                                double sellingPrice = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Product Name:");
                                String productName = scanner.nextLine();
                                productList.addItem(new Product(1,productName,purchasePrice,sellingPrice));
                                productList.setID();
                                writer.save(productList,"Products");
                            }catch (NumberFormatException e){
                                e.printStackTrace();
                            }
                        }
                        case 3 -> {
                            productList.printProduct();
                            System.out.println("Select ID you want to delete:");
                            int deleteID = scanner.nextInt();
                            productList.remove(deleteID);
                            productList.setID();
                            writer.save(productList,"Products");
                        }

                        case 4 -> {
                            productList.printProduct();
                            System.out.println("Select ID you want to update:");
                            int updateID=scanner.nextInt();
                            System.out.println("Enter new stock:");
                            int updateStock=scanner.nextInt();
                            productList.updateProduct(updateID,updateStock);

                        }

                    }

                }
                case 4 -> {
                    printReportMenu();
                    option = scanner.nextInt();
                    switch (option){
                        case 1 ->{
                            System.out.println("Enter first time exp:10/12/2021");
                            String firstDate = scanner.next();
                            System.out.println("Enter last time exp:15/12/2021");
                            String lastDate = scanner.next();
                            transaction.printListBetweenTwoTime(firstDate,lastDate);
                        }
                        case 2 ->{
                            System.out.println(inventoryList.printBestSellingProduct());
                        }
                        case 3 ->{
                            System.out.println("Use your computer language for ex:Temmuz");
                            System.out.println("Enter month:");
                            inventoryList.printListSpecifiedMonth(scanner.next());
                        }
                        case 4 ->{
                            System.out.println("Use your computer language for ex:Perşembe");
                            System.out.println("Enter week day:");
                            inventoryList.printListSpecifiedWeekDay(scanner.next());
                        }

                    }

                }
            }


        } while (option != 5);
    }

    public static void printMainMenu() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.print("Main Menu (Time:" + time.format(now) + " Date:" + date.format(now) + ")\n" +
                "1. Sell Product\n" +
                "2. Manage Inventory\n" +
                "3. Manage Products\n" +
                "4. Reports\n" +
                "5. Exit\n" +
                "Option:");
    }

    public static void printManageInventoryMenu(){
        System.out.println("Manage Inventory Menu\n" +
                "1. Add (buy) Product\n" +
                "2. Update Product in Inventory\n" +
                "3. Delete Product from Inventory\n" +
                "4. Return to Main Menu\n" +
                "Option:\n");
    }

    public static void printManageProductsMenu(){
        System.out.println("Manage Products Menu\n" +
                "1. Print all products\n" +
                "2. Add new Product\n" +
                "3. Remove Product\n" +
                "4. Update Product\n" +
                "5. Return to Main Menu\n" +
                "Option:\n");
    }

    public static void printReportMenu(){
        System.out.println("Report Menu\n" +
                "1. Print a list of product names that are sold between two time period\n" +
                "2. Print the name of the Best Selling Product\n" +
                "3. Print list of products sold on specified Month (Jan, Feb, etc.)\n" +
                "4. Print all products sold on specified week day (Mon, Tue, Wed, etc.)\n" +
                "5. Return to Main Menu\n" +
                "Option:");
    }

    public static void editTransaction(){

    }
}
