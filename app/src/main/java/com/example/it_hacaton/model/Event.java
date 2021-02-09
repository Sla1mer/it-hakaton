package com.example.it_hacaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {
    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("description") private String description;
    @Expose
    @SerializedName("to_object") private String to_object;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTo_subject() {
        return to_object;
    }

    public void setTo_subject(String to_subject) {
        this.to_object = to_object;
    }
}
