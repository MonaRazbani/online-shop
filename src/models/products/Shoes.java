package models.products;

import models.enums.ProductStatus;
import models.enums.ShoesType;

public class Shoes extends Product{
    private int size ;
    private ShoesType type;

    public Shoes(double cost, String name, ProductStatus status, String companyName, int wiki, int size, ShoesType type) {
        super(cost, name, status, companyName, wiki);
        this.size = size;
        this.type = type;
    }

    public Shoes() {
        super();
    }

    public Shoes(double cost, String name, ProductStatus status, String companyName, int wiki) {
        super(cost, name, status, companyName, wiki);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ShoesType getType() {
        return type;
    }

    public void setType(ShoesType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Shoes :" + super.toString()+
                "  size :  " + size +
                "  " + type +
                '\n' ;
    }
}
