package com.natursalas.natursalassystem.model.dto;

import java.math.BigDecimal;

public class SaleDetailDTO {
    private String idSale;
    private String idProduct;
    private String idLocation;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

    public SaleDetailDTO() {
    }

    public SaleDetailDTO(String idSale, String idProduct, String idLocation, int quantity, BigDecimal price, BigDecimal subtotal) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}