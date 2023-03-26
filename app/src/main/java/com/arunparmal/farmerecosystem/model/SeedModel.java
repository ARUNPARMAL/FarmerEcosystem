package com.arunparmal.farmerecosystem.model;

public class SeedModel {


    String SeedID;
    String BrandName;
    String SeedName;
    String SeedDescription;
    String Timeperiod;
    String ItemWeight;
    String NetQuantity;
    String StockStatus;
    double Price;
    String VendorID;
    String Variety;
    String ImageUrl;

    public SeedModel(String seedID, String brandName, String seedName, String seedDescription, String timeperiod, String itemWeight, String netQuantity, String stockStatus, double price, String vendorID, String variety, String imageUrl) {
        SeedID = seedID;
        BrandName = brandName;
        SeedName = seedName;
        SeedDescription = seedDescription;
        Timeperiod = timeperiod;
        ItemWeight = itemWeight;
        NetQuantity = netQuantity;
        StockStatus = stockStatus;
        Price = price;
        VendorID = vendorID;
        Variety = variety;
        ImageUrl = imageUrl;
    }
    public SeedModel(){}

    public String getSeedID() {
        return SeedID;
    }

    public void setSeedID(String seedID) {
        SeedID = seedID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getSeedName() {
        return SeedName;
    }

    public void setSeedName(String seedName) {
        SeedName = seedName;
    }

    public String getSeedDescription() {
        return SeedDescription;
    }

    public void setSeedDescription(String seedDescription) {
        SeedDescription = seedDescription;
    }

    public String getTimeperiod() {
        return Timeperiod;
    }

    public void setTimeperiod(String timeperiod) {
        Timeperiod = timeperiod;
    }

    public String getItemWeight() {
        return ItemWeight;
    }

    public void setItemWeight(String itemWeight) {
        ItemWeight = itemWeight;
    }

    public String getNetQuantity() {
        return NetQuantity;
    }

    public void setNetQuantity(String netQuantity) {
        NetQuantity = netQuantity;
    }

    public String getStockStatus() {
        return StockStatus;
    }

    public void setStockStatus(String stockStatus) {
        StockStatus = stockStatus;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getVendorID() {
        return VendorID;
    }

    public void setVendorID(String vendorID) {
        VendorID = vendorID;
    }

    public String getVariety() {
        return Variety;
    }

    public void setVariety(String variety) {
        Variety = variety;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
