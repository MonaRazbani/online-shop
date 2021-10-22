package models.enums;

public enum ElectronicType {
    TELEVISION(1,"TV"), RADIO(2,"radio"),NOT_SET(3,"not_set");


    private int id ;
    private String name ;

    ElectronicType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public ElectronicType getVal (String name ){
        switch (name.trim().toLowerCase()){
            case "tv":
                return TELEVISION;
            case "female":
                return RADIO;
            default:
                return NOT_SET;
        }
}

}
