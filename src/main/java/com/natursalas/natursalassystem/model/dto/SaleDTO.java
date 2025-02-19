package com.natursalas.natursalassystem.model.dto;

import java.sql.Timestamp;

public class SaleDTO {
    private String idSale;
    private String DNI;
    private String diagnosis;
    private String category;
    private Timestamp saleDate;
    private String idLocation;

    public SaleDTO() {
    }

    public SaleDTO(String idSale, String DNI, String diagnosis, String category, Timestamp saleDate, String idLocation) {
        this.idSale = idSale;
        this.DNI = DNI;
        this.diagnosis = diagnosis;
        this.category = category;
        this.saleDate = saleDate;
        this.idLocation = idLocation;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }
}