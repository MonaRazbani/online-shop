package models.products;

import models.enums.ElectronicType;
import models.enums.ProductStatus;

public class Electronic extends Product{
    private ElectronicType type;

    public Electronic(double cost, String name, ProductStatus status, String companyName, ElectronicType type,int wiki) {
        super(cost, name, status, companyName,wiki);
        this.type = type;
    }

    public Electronic() {

    }


    public Electronic(double cost, String name, ProductStatus status, String companyName, int wiki) {
        super(cost, name, status, companyName, wiki);
    }

    public ElectronicType getType() {
        return type;
    }

    public void setType(ElectronicType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Electronic :  " + super.toString()+"  "+
                type +
                '\n';
    }
}
