package com.example.it_hacaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetListNameDB {
    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("name_db") private String name_db;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_db() {
        return name_db;
    }

    public void setName_db(String name_db) {
        this.name_db = name_db;
    }
}
