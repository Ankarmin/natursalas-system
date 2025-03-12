package com.natursalas.natursalassystem.model.dto;

public class ViewSaleDetailDTO {
    private String idLocation;
    private String productName;
    private int price;
    private int quantity;
    private int subtotal;

    public ViewSaleDetailDTO() {
    }

    public ViewSaleDetailDTO(String idLocation, String productName, int price, int quantity, int subtotal) {
        this.idLocation = idLocation;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
