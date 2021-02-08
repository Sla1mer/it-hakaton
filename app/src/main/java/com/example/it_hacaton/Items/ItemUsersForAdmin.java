package com.example.it_hacaton.Items;

public class ItemUsersForAdmin {
    String name, middleName, cancel, lastName;

    public ItemUsersForAdmin(String name, String middleName, String lastName) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }
}
