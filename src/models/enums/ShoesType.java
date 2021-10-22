package models.enums;

public enum ShoesType {
    FORMAL(1,"formal"),SPORT(2 , "sport"), NOT_SET(3 , "not_set");
    private int id ;
    private String name ;

    ShoesType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public ShoesType getVal (String name ){
        switch (name.trim().toLowerCase()){
            case "formal":
                return FORMAL;
            case "sport":
                return SPORT;
            default:
                return NOT_SET;
        }
    }

}

