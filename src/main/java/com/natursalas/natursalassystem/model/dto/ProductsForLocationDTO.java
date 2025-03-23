package com.natursalas.natursalassystem.model.dto;

public class ProductsForLocationDTO {
    private String idProduct;
    private String idLocation;
    private int stock;

    public ProductsForLocationDTO() {
    }

    public ProductsForLocationDTO(String idProduct, String idLocation, int stock) {
        this.idProduct = idProduct;
        this.idLocation = idLocation;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}