package com.natursalas.natursalassystem.model.dto;

import java.sql.Date;

public class ProductsIncreaseDTO {
    private int idProductIncrease;
    private String idProduct;
    private Date dateOfEntry;
    private int quantity;
    private String idLocation;

    public ProductsIncreaseDTO() {
    }

    public ProductsIncreaseDTO(int idProductIncrease, String idProduct, Date dateOfEntry, int quantity, String idLocation) {
        this.idProductIncrease = idProductIncrease;
        this.idProduct = idProduct;
        this.dateOfEntry = dateOfEntry;
        this.quantity = quantity;
        this.idLocation = idLocation;
    }

    public int getIdProductIncrease() {
        return idProductIncrease;
    }

    public void setIdProductIncrease(int idProductIncrease) {
        this.idProductIncrease = idProductIncrease;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }
}