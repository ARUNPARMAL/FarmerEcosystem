package com.arunparmal.farmerecosystem.model;

public class RentModel {

    String Company;
    String Details;
    String ID;
    String ImageUrl;
    String LenderID;
    String Name;
    String Purpose;
    String Rate;

    public RentModel(String company, String details, String ID, String imageUrl, String lenderID, String name, String purpose, String rate) {
        Company = company;
        Details = details;
        this.ID = ID;
        ImageUrl = imageUrl;
        LenderID = lenderID;
        Name = name;
        Purpose = purpose;
        Rate = rate;
    }

    public RentModel() {
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getLenderID() {
        return LenderID;
    }

    public void setLenderID(String lenderID) {
        LenderID = lenderID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }
}
