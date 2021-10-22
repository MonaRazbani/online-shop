package models.enums;

public enum ReadableItemsType {
    JOURNAL(1, "journal"), BOOK(2, "book"), NOT_SET(3, "not_set");
    private int id;
    private String name;

    ReadableItemsType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ReadableItemsType getVal(String name) {
        switch (name.trim().toLowerCase()) {
            case "journal":
                return JOURNAL;
            case "book":
                return BOOK;
            default:
                return NOT_SET;
        }
    }
    }
