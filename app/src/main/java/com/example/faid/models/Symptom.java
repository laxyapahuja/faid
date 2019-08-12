package com.example.faid.models;

import java.io.Serializable;

public class Symptom implements Serializable {
    private String Name;
    private String ID;
    private boolean select;

    public Symptom(String name, String ID, boolean select) {
        Name = name;
        this.ID = ID;
        this.select = select;
    }

    public Symptom() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "Name='" + Name + '\'' +
                ", ID=" + ID +
                '}';
    }
}
