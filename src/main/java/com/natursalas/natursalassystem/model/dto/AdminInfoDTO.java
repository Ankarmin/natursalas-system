package com.natursalas.natursalassystem.model.dto;

import java.util.List;

public class AdminInfoDTO {
    private String bestSellingLocation;
    private int productsSoldToday;
    private int patientsAttendedToday;
    private int newPatientsToday;
    private List<ViewAdminInfoDTO> adminInfoList;

    public AdminInfoDTO(String bestSellingLocation, int productsSoldToday, int patientsAttendedToday, int newPatientsToday, List<ViewAdminInfoDTO> adminInfoList) {
        this.bestSellingLocation = bestSellingLocation;
        this.productsSoldToday = productsSoldToday;
        this.patientsAttendedToday = patientsAttendedToday;
        this.newPatientsToday = newPatientsToday;
        this.adminInfoList = adminInfoList;
    }

    public String getBestSellingLocation() {
        return bestSellingLocation;
    }

    public void setBestSellingLocation(String bestSellingLocation) {
        this.bestSellingLocation = bestSellingLocation;
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

    public List<ViewAdminInfoDTO> getAdminInfoList() {
        return adminInfoList;
    }

    public void setAdminInfoList(List<ViewAdminInfoDTO> adminInfoList) {
        this.adminInfoList = adminInfoList;
    }
}
