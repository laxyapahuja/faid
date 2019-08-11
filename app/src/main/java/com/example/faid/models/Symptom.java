package com.example.faid.models;

public class Symptom {
    private String Name;
    private int ID;

    public Symptom(String name, int ID) {
        Name = name;
        this.ID = ID;
    }

    public Symptom() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "Name='" + Name + '\'' +
                ", ID=" + ID +
                '}';
    }
}
