package com.natursalas.natursalassystem.model.dto;

import java.math.BigDecimal;

public class ViewAddSaleDTO {
    private String idProduct;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal subtotal;

    public ViewAddSaleDTO() {
    }

    public ViewAddSaleDTO(String idProduct, String productName, BigDecimal price, int quantity, BigDecimal subtotal) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
