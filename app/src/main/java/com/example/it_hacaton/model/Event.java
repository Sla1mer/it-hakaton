package com.example.it_hacaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {
    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("description") private String description;
    @Expose
    @SerializedName("to_subject") private String to_subject;

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
        return to_subject;
    }

    public void setTo_subject(String to_subject) {
        this.to_subject = to_subject;
    }
}
