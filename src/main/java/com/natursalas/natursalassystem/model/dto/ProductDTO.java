package com.natursalas.natursalassystem.model.dto;

public class ProductDTO {
    private String idProduct;
    private String category;
    private String productName;
    private int stock;
    private int price;
    private String idLocation;

    public ProductDTO() {
    }

    public ProductDTO(String idProduct, String category, String productName, int stock, int price, String idLocation) {
        this.idProduct = idProduct;
        this.category = category;
        this.productName = productName;
        this.stock = stock;
        this.price = price;
        this.idLocation = idLocation;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
}