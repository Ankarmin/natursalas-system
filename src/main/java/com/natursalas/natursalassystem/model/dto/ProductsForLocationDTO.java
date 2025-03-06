package com.natursalas.natursalassystem.model.dto;

public class ProductsForLocationDTO {
    private String idProduct;
    private String category;
    private String productName;
    private int price;
    private String idLocation;
    private int stock;

    public ProductsForLocationDTO() {
    }

    public ProductsForLocationDTO(String idProduct, String category, String productName, int price, String idLocation, int stock) {
        this.idProduct = idProduct;
        this.category = category;
        this.productName = productName;
        this.price = price;
        this.idLocation = idLocation;
        this.stock = stock;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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