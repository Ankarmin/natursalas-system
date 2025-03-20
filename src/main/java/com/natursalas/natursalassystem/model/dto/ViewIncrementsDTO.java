package com.natursalas.natursalassystem.model.dto;

import java.sql.Timestamp;

public class ViewIncrementsDTO {

    private Timestamp dateOfEntry;
    private String idLocation;
    private String idProduct;
    private String productName;
    private String category;
    private int quantity;

    public ViewIncrementsDTO() {
    }

    public ViewIncrementsDTO(Timestamp dateOfEntry, String idLocation, String idProduct, String productName, String category, int quantity) {
        this.dateOfEntry = dateOfEntry;
        this.idLocation = idLocation;
        this.idProduct = idProduct;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
    }

    public Timestamp getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Timestamp dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
