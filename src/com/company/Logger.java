package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public Logger(String transactionType,String str,Product product){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            FileWriter logger = new FileWriter("src\\com\\company\\Transactions.txt",true);
            if (transactionType.equals("purchase")){
                logger.write("purchase,"+str+date.format(now)+"\n");

            }else if(transactionType.equals("sale")){
                logger.write("sale,"+str+date.format(now)+"\n");
            }
            logger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Logger(String transactionType,String str){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            FileWriter logger = new FileWriter("src\\com\\company\\Transactions.txt",true);
            if (transactionType.equals("purchase")){
                logger.write("purchase,"+str+date.format(now)+"\n");
            }else if(transactionType.equals("sale")){

                logger.write("sale,"+str+date.format(now)+"\n");
            }
            logger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
