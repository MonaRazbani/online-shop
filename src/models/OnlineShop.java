package models;

import models.enums.*;
import models.person.Customer;
import models.products.Electronic;
import models.products.ReadableItems;
import models.products.Shoes;

import java.util.ArrayList;
import java.util.List;

public class OnlineShop {
    private List<Electronic> electronicProductList = new ArrayList<>();
    private List<Shoes> ShoesProductList = new ArrayList<>();
    private List<ReadableItems> readableItemsProductList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();

    public List<Electronic> getElectronicProductList() {
        return electronicProductList;
    }

    public void setElectronicProductList(List<Electronic> electronicProductList) {
        this.electronicProductList = electronicProductList;
    }

    public List<Shoes> getShoesProductList() {
        return ShoesProductList;
    }

    public void setShoesProductList(List<Shoes> shoesProductList) {
        ShoesProductList = shoesProductList;
    }

    public List<ReadableItems> getReadableItemsProductList() {
        return readableItemsProductList;
    }

    public void setReadableItemsProductList(List<ReadableItems> readableItemsProductList) {
        this.readableItemsProductList = readableItemsProductList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Electronic addNewProduct (double cost, String name, ProductStatus status, String companyName, ElectronicType type, int wiki){
        Electronic electronic = new Electronic(cost, name, status, companyName,type, wiki);
        electronicProductList.add(electronic);
        return electronic;
    }

    public Shoes addNewShoes (double cost, String name, ProductStatus status, String companyName, int wiki, int size, ShoesType type){
        Shoes shoes = addNewShoes(cost, name, status, companyName, wiki, size, type);
        ShoesProductList.add(shoes);
        return shoes;
    }

    public ReadableItems AddNewReadableItem (double cost, String name, ProductStatus status, String companyName, int wiki, ReadableItemsType type, String subject){
        ReadableItems readableItems = new ReadableItems(cost, name, status, companyName, wiki, type, subject);
        readableItemsProductList.add(readableItems);
        return readableItems;
    }

    public Customer addNewCustomer (String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber, Gender gender){
        Customer customer = new Customer(firstName, lastName, userName, password, nationalCode, phoneNumber, gender);
        customerList.add(customer);
        return customer;
    }


}
