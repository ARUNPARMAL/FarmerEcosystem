package com.arunparmal.farmerecosystem.model;

public class ChemicalModel {

    String ID;
    String Name;
    String Desc;
    double Price;
    String Brand;
    String Use;
    String NetQuantity;
    String ItemWeight;
    String Stock;
    String VendorID;
    String Category;
    String ImageUrl;


    public ChemicalModel() {
    }

    public ChemicalModel(String ID, String name, String desc, double price, String brand, String use, String netQuantity, String itemWeight, String stock, String vendorID, String category, String imageUrl) {
        this.ID = ID;
        Name = name;
        Desc = desc;
        Price = price;
        Brand = brand;
        Use = use;
        NetQuantity = netQuantity;
        ItemWeight = itemWeight;
        Stock = stock;
        VendorID = vendorID;
        Category = category;
        ImageUrl = imageUrl;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getUse() {
        return Use;
    }

    public void setUse(String use) {
        Use = use;
    }

    public String getNetQuantity() {
        return NetQuantity;
    }

    public void setNetQuantity(String netQuantity) {
        NetQuantity = netQuantity;
    }

    public String getItemWeight() {
        return ItemWeight;
    }

    public void setItemWeight(String itemWeight) {
        ItemWeight = itemWeight;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getVendorID() {
        return VendorID;
    }

    public void setVendorID(String vendorID) {
        VendorID = vendorID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
