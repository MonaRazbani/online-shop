package models;

import models.enums.CartStatus;
import models.enums.PaymentStatus;
import models.person.Customer;
import models.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> listPurchases = new ArrayList<>();
    private double totalCost ;
    private int numOfCartPurchases;
    private Customer owner;
    private CartStatus status;
    private PaymentStatus paymentStatus ;

    public Cart( Customer owner ) {
        this.totalCost = 0;
        this.numOfCartPurchases = 0;
        this.owner = owner;
        this.status = CartStatus.CREAT;
        this.paymentStatus =PaymentStatus.NOT_PAID;
    }

    public Cart() {
        
    }

    public List<Product> getListPurchases() {
        return listPurchases;
    }

    public void setListPurchases(List<Product> listPurchases) {
        this.listPurchases = listPurchases;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getNumOfCartPurchases() {
        return numOfCartPurchases;
    }

    public void setNumOfCartPurchases(int numOfCartPurchases) {
        this.numOfCartPurchases = numOfCartPurchases;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void AddNewPurchase (Product purchase){
        listPurchases.add(purchase);
        setNumOfCartPurchases(getNumOfCartPurchases()+1);
        setTotalCost(purchase.getCost()+getTotalCost());
    }


}
