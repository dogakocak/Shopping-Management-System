package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProductList {
    private Node head;

    public void addItem(Product product) {
        addItem(head, product);
    }

    private boolean addItem(Node n, Product product) {
        if (n == null) {
            head = new Node(product);
            return true;
        }
        if (n.getNext() == null) {
            n.setNext(new Node(product));
            return true;
        }
        return addItem(n.getNext(), product);


    }

    public void remove(int productId) {
        head = remove(head, productId);
    }

    private Node remove(Node n, int productId) {
        if (n == null) {
            return null;
        }
        if (n.getProduct().getProductId() == productId) {
            return n.getNext();
        }
        n.setNext(remove(n.getNext(), productId));
        return n;
    }

    public void addProduct(Product product, int quantity) {
        if (contains(product) == false) {
            addItem(head, product);
        } else {
            product.setStock(product.getStock() + quantity);
        }
    }

    public void setID() {
        Node walk = head;
        int id = 1;
        while (walk != null) {
            walk.getProduct().setProductId(id);
            id++;
            walk = walk.getNext();
        }
    }

    public boolean contains(Product product) {
        Node walk = head;
        while (walk != null) {
            if (walk.getProduct().getProductName().equals(product.getProductName())) {
                return true;
            }
            walk = walk.getNext();
        }
        return false;
    }

    public void sellProduct(int productId, int quantity, ProductList transaction) {
        Node walk = head;
        DateTimeFormatter sellDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter sellMonth = DateTimeFormatter.ofPattern("MMMM");
        DateTimeFormatter sellWeekDay = DateTimeFormatter.ofPattern("EEEE");
        LocalDateTime now = LocalDateTime.now();
        while (walk != null) {
            if (walk.getProduct().getProductId() == productId) {
                if (walk.getProduct().getStock() - quantity > 0) {
                    walk.getProduct().setSellingDate(sellDate.format(now));
                    walk.getProduct().setSellingMonth(sellMonth.format(now));
                    walk.getProduct().setSellingWeekDay(sellWeekDay.format(now));
                    transaction.addItem(walk.getProduct());
                    walk.getProduct().setSalesQuantity(walk.getProduct().getSalesQuantity() + quantity);
                    walk.getProduct().setStock(walk.getProduct().getStock() - quantity);
                    new Logger("sale", walk.getProduct().getProductId() + "," + quantity + ",", walk.getProduct());
                } else if (walk.getProduct().getStock() - quantity == 0) {
                    walk.getProduct().setSellingDate(sellDate.format(now));
                    walk.getProduct().setSellingMonth(sellMonth.format(now));
                    walk.getProduct().setSellingWeekDay(sellWeekDay.format(now));
                    transaction.addItem(walk.getProduct());
                    walk.getProduct().setSalesQuantity(walk.getProduct().getSalesQuantity() + quantity);
                    remove(productId);
                    new Logger("sale", walk.getProduct().getProductId() + "," + quantity + ",", walk.getProduct());
                } else {
                    System.out.println("it is out of stock");
                }
            }
            walk = walk.getNext();
        }
    }

    public Product printBestSellingProduct() {
        Node walk = head;
        Product maxSellingProduct = null;
        int quantityTemp = -1;
        while (walk != null) {
            if (walk.getProduct().getSalesQuantity() > quantityTemp) {
                quantityTemp = walk.getProduct().getSalesQuantity();
                maxSellingProduct = walk.getProduct();
            }
            walk = walk.getNext();
        }
        return maxSellingProduct;
    }

    public void printListSpecifiedMonth(String month) {
        Node walk = head;
        while (walk != null) {
            if (walk.getProduct().getSellingMonth().equals(month)){
                System.out.println("List of products sold in: "+month);
                System.out.println(walk.getProduct());
            }
            walk=walk.getNext();
        }
    }

    public void printListSpecifiedWeekDay(String weekDay) {
        Node walk = head;
        while (walk != null) {
            if (walk.getProduct().getSellingWeekDay().equals(weekDay)){
                System.out.println("List of products sold on: "+weekDay);
                System.out.println(walk.getProduct());
            }
            walk=walk.getNext();
        }
    }

    public void buyProduct(ProductList productList, int productId, int quantity) {
        Node walk = productList.head;
        while (walk != null) {
            if (walk.getProduct().getProductId() == productId) {
                if (walk.getProduct().getStock() - quantity > 0) {
                    addProduct(walk.getProduct(), quantity);
                    walk.getProduct().setStock(walk.getProduct().getStock() - quantity);
                    new Logger("purchase", walk.getProduct().getProductId() + "," + quantity + ",");

                } else if (walk.getProduct().getStock() - quantity == 0) {
                    productList.remove(productId);
                    addProduct(walk.getProduct(), quantity);
                    new Logger("purchase", walk.getProduct().getProductId() + "," + quantity + ",");
                } else {
                    System.out.println("it is out of stock");
                }


            }
            walk = walk.getNext();
        }

    }

    public void updateProduct(int id, int stock) {
        Node walk = head;
        while (walk != null) {
            if (walk.getProduct().getProductId() == id) {
                walk.getProduct().setStock(stock);
            }
            walk = walk.getNext();
        }
    }

    public void printProduct() {
        Node walk = head;
        while (walk != null) {
            System.out.println("ID:" + walk.getProduct().getProductId() + " NAME:" +
                    walk.getProduct().getProductName() + " STOCK: " + walk.getProduct().getStock());
            walk = walk.getNext();
        }
    }

    public void printListBetweenTwoTime(String firstDate, String lastDate) throws ParseException {
        Node walk = head;
        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
        while (walk != null) {
            Date d1 = sdformat.parse(firstDate);
            Date d2 = sdformat.parse(walk.getProduct().getSellingDate());
            Date d3 = sdformat.parse(lastDate);
            if (d1.compareTo(d2) < 0 && d3.compareTo(d2) > 0) {
                System.out.println(walk.getProduct());
            }
            walk = walk.getNext();
        }
    }

    public String toString() {
        Node walk = head;
        String str = "";
        while (walk != null) {
            str += walk.getProduct().getProductId() + "," + walk.getProduct().getProductName() + "," +
                    walk.getProduct().getPurchasePrice() + "," + walk.getProduct().getSellingPrice() + "\n";
            walk = walk.getNext();
        }
        return str;
    }
}
