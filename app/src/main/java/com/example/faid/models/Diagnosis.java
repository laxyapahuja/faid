package com.example.faid.models;

public class Diagnosis {
    private String ID;
    private String Name;
    private String Accuracy;
    private String ProfName;
    private String IcdName;

    public Diagnosis() {
    }

    public Diagnosis(String ID, String name, String accuracy, String profName, String icdName) {
        this.ID = ID;
        Name = name;
        Accuracy = accuracy;
        ProfName = profName;
        IcdName = icdName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDiagnosis() {
        return Name;
    }

    public void setDiagnosis(String name) {
        Name = name;
    }

    public String getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(String accuracy) {
        Accuracy = accuracy;
    }

    public String getProfName() {
        return ProfName;
    }

    public void setProfName(String profName) {
        ProfName = profName;
    }

    public String getIcdName() {
        return IcdName;
    }

    public void setIcdName(String icdName) {
        IcdName = icdName;
    }
}
