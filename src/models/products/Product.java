package models.products;

import models.enums.ProductStatus;
import models.enums.PurchaseStatus;

public class Product {
    private double cost ;
    private String name ;
    private ProductStatus status;
    private String companyName ;
    private int wiki ;
    private PurchaseStatus purchaseStatus;
    private int id =0 ;
    private int numOfPurchases= 0 ;

    public Product(double cost, String name, ProductStatus status, String companyName ,int wiki ) {
        this.cost = cost;
        this.name = name;
        this.status = status;
        this.companyName = companyName;
        this.wiki=wiki;
        this.purchaseStatus= PurchaseStatus.NOT_CONFIRM;

    }

    public Product() {

    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getWiki() {
        return wiki;
    }

    public void setWiki(int wiki) {
        this.wiki = wiki;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public int getNumOfPurchases() {
        return numOfPurchases;
    }

    public void setNumOfPurchases(int numOfPurchases) {
        this.numOfPurchases = numOfPurchases;
    }

    @Override
    public String toString() {
        return
                " name : " + name +"  "+
                 "cost : " + cost + "  " + status +"  "+
                "company : " + companyName + "  " ;
    }
}
