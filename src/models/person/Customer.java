package models.person;

import models.Cart;
import models.enums.CartStatus;
import models.enums.Gender;
import models.enums.PaymentStatus;

public class Customer extends Person {
    private Cart cart =new Cart();
    private double wallet ;
    public Customer(String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Gender gender) {
        super(firstName, lastName, userName, password, nationalCode, phoneNumber,gender);
        cart.setNumOfCartPurchases(0);
        cart.setTotalCost(0);
        cart.setPaymentStatus(PaymentStatus.NOT_PAID);
        cart.setStatus(CartStatus.NOT_CONFIRM);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return super.toString() +
                " wallet: " + wallet ;
    }
}
