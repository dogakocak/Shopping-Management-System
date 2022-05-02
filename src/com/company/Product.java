package com.company;
public class Product {
    private int productId;
    private String productName;
    private double purchasePrice;
    private double sellingPrice;
    private int stock;
    private int salesQuantity;
    private String sellingDate;
    private String sellingMonth;
    private String sellingWeekDay;

    public Product(int productId,String productName,double purchasePrice,double sellingPrice){
        this.productId=productId;
        this.productName=productName;
        this.purchasePrice=purchasePrice;
        this.sellingPrice=sellingPrice;
        this.stock=100;
        this.salesQuantity=0;
        this.sellingMonth="";
        this.sellingWeekDay="";

    }

    public Product(){

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toString(){
        return productId + ", "+ productName+ ", "+purchasePrice+ ", "+sellingPrice;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(int lastNumbSales) {
        this.salesQuantity = lastNumbSales;
    }

    public String getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(String sellingDate) {
        this.sellingDate = sellingDate;
    }

    public String getSellingMonth() {
        return sellingMonth;
    }

    public void setSellingMonth(String sellingMonth) {
        this.sellingMonth = sellingMonth;
    }

    public String getSellingWeekDay() {
        return sellingWeekDay;
    }

    public void setSellingWeekDay(String sellingWeekDay) {
        this.sellingWeekDay = sellingWeekDay;
    }
}
