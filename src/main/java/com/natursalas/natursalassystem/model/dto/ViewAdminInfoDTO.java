package com.natursalas.natursalassystem.model.dto;

public class ViewAdminInfoDTO {
    private String idLocation;
    private int productsSoldToday;
    private String bestSellingProduct;
    private int patientsAttendedToday;
    private int newPatientsToday;

    public ViewAdminInfoDTO(String idLocation, int productsSoldToday, String bestSellingProduct, int patientsAttendedToday, int newPatientsToday) {
        this.idLocation = idLocation;
        this.productsSoldToday = productsSoldToday;
        this.bestSellingProduct = bestSellingProduct;
        this.patientsAttendedToday = patientsAttendedToday;
        this.newPatientsToday = newPatientsToday;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public int getProductsSoldToday() {
        return productsSoldToday;
    }

    public void setProductsSoldToday(int productsSoldToday) {
        this.productsSoldToday = productsSoldToday;
    }

    public String getBestSellingProduct() {
        return bestSellingProduct;
    }

    public void setBestSellingProduct(String bestSellingProduct) {
        this.bestSellingProduct = bestSellingProduct;
    }

    public int getPatientsAttendedToday() {
        return patientsAttendedToday;
    }

    public void setPatientsAttendedToday(int patientsAttendedToday) {
        this.patientsAttendedToday = patientsAttendedToday;
    }

    public int getNewPatientsToday() {
        return newPatientsToday;
    }

    public void setNewPatientsToday(int newPatientsToday) {
        this.newPatientsToday = newPatientsToday;
    }
}
