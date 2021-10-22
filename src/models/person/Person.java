package models.person;

import models.Address;
import models.enums.Gender;

public class Person {
    private String firstName ;
    private String lastName ;
    private String userName ;
    private String password ;
    private String nationalCode ;
    private String phoneNumber ;
    private Address address;
    private Gender gender;

    public Person(String firstName, String lastName, String userName, String password, String nationalCode, String phoneNumber,Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.gender= gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean checkingPassword(String userName , String password ){
        if (getUserName().equals(userName)){
            if (getPassword().equals(password)){
                return true ;
            };
        }

        return false ;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return
                "  firstName:  " + firstName +
                "  lastName:  " + lastName +

                "  nationalCode:  " + nationalCode + '\'' +
                "  phoneNumber:  " + phoneNumber + '\'' +
                "  address=" + address +
                "  gender=" + gender ;
    }
}
