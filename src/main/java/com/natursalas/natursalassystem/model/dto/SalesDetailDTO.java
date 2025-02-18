package com.natursalas.natursalassystem.model.dto;

public class SalesDetailDTO {
    private String idSale;
    private String idProduct;
    private String idLocation;
    private int quantity;
    private int price;
    private int subtotal;

    public SalesDetailDTO() {
    }

    public SalesDetailDTO(String idSale, String idProduct, String idLocation, int quantity, int price, int subtotal) {
        this.idSale = idSale;
        this.idProduct = idProduct;
        this.idLocation = idLocation;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}