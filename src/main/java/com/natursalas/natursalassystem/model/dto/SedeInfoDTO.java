package com.natursalas.natursalassystem.model.dto;

import java.util.List;

public class SedeInfoDTO {
    private String bestSellingProduct;
    private int productsSoldToday;
    private int patientsAttendedToday;
    private int newPatientsToday;
    private List<PatientDTO> patientsAttendedTodayList;

    public SedeInfoDTO(String bestSellingProduct, int productsSoldToday, int patientsAttendedToday, int newPatientsToday, List<PatientDTO> patientsAttendedTodayList) {
        this.bestSellingProduct = bestSellingProduct;
        this.productsSoldToday = productsSoldToday;
        this.patientsAttendedToday = patientsAttendedToday;
        this.newPatientsToday = newPatientsToday;
        this.patientsAttendedTodayList = patientsAttendedTodayList;
    }

    public String getBestSellingProduct() {
        return bestSellingProduct;
    }

    public void setBestSellingProduct(String bestSellingProduct) {
        this.bestSellingProduct = bestSellingProduct;
    }

    public int getProductsSoldToday() {
        return productsSoldToday;
    }

    public void setProductsSoldToday(int productsSoldToday) {
        this.productsSoldToday = productsSoldToday;
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

    public List<PatientDTO> getPatientsAttendedTodayList() {
        return patientsAttendedTodayList;
    }

    public void setPatientsAttendedTodayList(List<PatientDTO> patientsAttendedTodayList) {
        this.patientsAttendedTodayList = patientsAttendedTodayList;
    }
}
