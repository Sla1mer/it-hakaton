package com.example.it_hacaton;

public class Item {
    String name, midleName, description;

    public Item(){}
    public Item(String name, String midleName, String description){
        this.name = name;
        this.description = description;
        this.midleName = midleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMidleName() {
        return midleName;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
