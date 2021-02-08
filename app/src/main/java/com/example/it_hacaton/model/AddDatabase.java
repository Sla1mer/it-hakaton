package com.example.it_hacaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDatabase {
    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("database_name") private String database_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatabase_name() {
        return database_name;
    }

    public void setDatabase_name(String database_name) {
        this.database_name = database_name;
    }
}
