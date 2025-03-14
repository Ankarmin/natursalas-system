package com.natursalas.natursalassystem.model.dto;

public class ProductDTO {
    private String idProduct;
    private String category;
    private String productName;
    private int price;

    public ProductDTO() {
    }

    public ProductDTO(String idProduct, String category, String productName, int price) {
        this.idProduct = idProduct;
        this.category = category;
        this.productName = productName;
        this.price = price;
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
}