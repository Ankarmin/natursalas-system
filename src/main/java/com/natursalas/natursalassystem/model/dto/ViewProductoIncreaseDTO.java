package com.natursalas.natursalassystem.model.dto;

public class ViewProductoIncreaseDTO {
    String idProduct;
    String productName;
    int quantity;

    public ViewProductoIncreaseDTO(String idProduct, String productName, int quantity) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
