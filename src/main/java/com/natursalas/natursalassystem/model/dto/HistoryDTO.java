package com.natursalas.natursalassystem.model.dto;

import java.sql.Timestamp;

public class HistoryDTO {
    private String idSale;
    private Timestamp saleDate;
    private String idLocation;
    private String diagnosis;
    private int subtotal;

    public HistoryDTO(String idSale, Timestamp saleDate, String idLocation, String diagnosis, int subtotal) {
        this.idSale = idSale;
        this.saleDate = saleDate;
        this.idLocation = idLocation;
        this.diagnosis = diagnosis;
        this.subtotal = subtotal;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }
}