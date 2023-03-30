package com.arunparmal.farmerecosystem.model;

public class SeedOrderModel {
    String Order_Product_address;
    String Order_Product_amount;
    String Order_Product_count;
    String Order_Product_id;
    String Order_Product_pincode;
    String Order_Payment_Mode;
    String Order_Product_UserID;
    String Order_Status;
    String Order_id;
    String documentId;

    public SeedOrderModel() {
    }

    public SeedOrderModel(String order_Product_address, String order_Product_amount, String order_Product_count, String order_Product_id, String order_Product_pincode, String order_Payment_Mode, String order_Product_UserID, String order_Status, String order_id, String documentId) {
        Order_Product_address = order_Product_address;
        Order_Product_amount = order_Product_amount;
        Order_Product_count = order_Product_count;
        Order_Product_id = order_Product_id;
        Order_Product_pincode = order_Product_pincode;
        Order_Payment_Mode = order_Payment_Mode;
        Order_Product_UserID = order_Product_UserID;
        Order_Status = order_Status;
        Order_id = order_id;
        this.documentId = documentId;
    }

    public String getOrder_Product_address() {
        return Order_Product_address;
    }

    public void setOrder_Product_address(String order_Product_address) {
        Order_Product_address = order_Product_address;
    }

    public String getOrder_Product_amount() {
        return Order_Product_amount;
    }

    public void setOrder_Product_amount(String order_Product_amount) {
        Order_Product_amount = order_Product_amount;
    }

    public String getOrder_Product_count() {
        return Order_Product_count;
    }

    public void setOrder_Product_count(String order_Product_count) {
        Order_Product_count = order_Product_count;
    }

    public String getOrder_Product_id() {
        return Order_Product_id;
    }

    public void setOrder_Product_id(String order_Product_id) {
        Order_Product_id = order_Product_id;
    }

    public String getOrder_Product_pincode() {
        return Order_Product_pincode;
    }

    public void setOrder_Product_pincode(String order_Product_pincode) {
        Order_Product_pincode = order_Product_pincode;
    }

    public String getOrder_Payment_Mode() {
        return Order_Payment_Mode;
    }

    public void setOrder_Payment_Mode(String order_Payment_Mode) {
        Order_Payment_Mode = order_Payment_Mode;
    }

    public String getOrder_Product_UserID() {
        return Order_Product_UserID;
    }

    public void setOrder_Product_UserID(String order_Product_UserID) {
        Order_Product_UserID = order_Product_UserID;
    }

    public String getOrder_Status() {
        return Order_Status;
    }

    public void setOrder_Status(String order_Status) {
        Order_Status = order_Status;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(String order_id) {
        Order_id = order_id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
