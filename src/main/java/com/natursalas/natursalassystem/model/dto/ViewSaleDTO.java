package com.natursalas.natursalassystem.model.dto;

import java.sql.Timestamp;

public class ViewSaleDTO {
    private String idSale;
    private String idLocation;
    private Timestamp saleDate;
    private String DNI;
    private String fullName;
    private String productName;
    private int quantity;
    private int subtotal;

    public ViewSaleDTO() {
    }

    public ViewSaleDTO(String idSale, String idLocation, Timestamp saleDate, String DNI, String fullName, String productName, int quantity, int subtotal) {
        this.idSale = idSale;
        this.idLocation = idLocation;
        this.saleDate = saleDate;
        this.DNI = DNI;
        this.fullName = fullName;
        this.productName = productName;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
