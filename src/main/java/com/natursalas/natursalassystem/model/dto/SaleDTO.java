package com.natursalas.natursalassystem.model.dto;

import java.sql.Timestamp;

public class SaleDTO {
    private int idSale;
    private String idPatient;
    private String diagnosis;
    private String category;
    private Timestamp saleDate;
    private String idLocation;
    private int idUser;

    public SaleDTO() {
    }

    public SaleDTO(int idSale, String idPatient, String diagnosis, String category, Timestamp saleDate, String idLocation, int idUser) {
        this.idSale = idSale;
        this.idPatient = idPatient;
        this.diagnosis = diagnosis;
        this.category = category;
        this.saleDate = saleDate;
        this.idLocation = idLocation;
        this.idUser = idUser;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}