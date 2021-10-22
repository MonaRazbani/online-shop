package models.products;

import models.enums.ProductStatus;
import models.enums.ReadableItemsType;

public class ReadableItems extends Product{
    private ReadableItemsType type ;
    private String subject ;

    public ReadableItems(double cost, String name, ProductStatus status, String companyName, int wiki, ReadableItemsType type, String subject) {
        super(cost, name, status, companyName, wiki);
        this.type = type;
        this.subject = subject;
    }

    public ReadableItems() {

    }

    public ReadableItems(double cost, String name, ProductStatus status, String companyName, int wiki) {
        super(cost, name, status, companyName, wiki);
    }

    public ReadableItemsType getType() {
        return type;
    }

    public void setType(ReadableItemsType type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "ReadableItems :" + super.toString()+
                "  " + type + "  "+
                "subject : " + subject + '\'' +
                '\n';
    }
}
