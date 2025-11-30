package com.lucas.screenmatch2.model;

public enum Category {
    ACTION("Action"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    ROMANCE("Romance"),
    DRAMA("Drama"),
    ADVENTURE("Adventure"),
    THRILLER("Thriller"),
    MYSTERY("Mystery"),
    HORROR("Horror");

    private String categoryOmdb;

    Category(String categoryOmdb) {
        this.categoryOmdb = categoryOmdb;
    }

    public static Category fromString(String strOmdb) {
        for(Category category : Category.values()) {
            if(category.categoryOmdb.equalsIgnoreCase(strOmdb)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category: " + strOmdb);
    }

    public String getCategoryOmdb() {
        return categoryOmdb;
    }
}
