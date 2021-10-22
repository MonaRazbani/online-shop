package models;

public class Address {
    String city ;
    String street ;
    String alley ;
    String postCode;
    int houseNumber;

    public Address(String city, String street, String alley, int houseNumber,String postCode) {
        this.city = city;
        this.street = street;
        this.alley = alley;
        this.houseNumber = houseNumber;
        this.postCode=postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
