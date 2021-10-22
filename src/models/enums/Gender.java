package models.enums;

import java.util.Locale;

public enum Gender {
    MALE(1,"male"),FEMALE(2,"female"),NOT_SET(3,"not_set");
    private int id ;
    private String name ;

    Gender(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Gender getVal (String name ){
        switch (name.trim().toLowerCase()){
            case "male":
                return MALE;
            case "female":
                return FEMALE;
            default:
                return NOT_SET;
        }
    }
}
