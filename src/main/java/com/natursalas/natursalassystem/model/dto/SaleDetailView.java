package com.natursalas.natursalassystem.model.dto;

import java.sql.Timestamp;

public class SaleDetailView {
    private Timestamp saleDate;
    private String idLocation;
    private String diagnosis;
    private int subtotal;

    public SaleDetailView(Timestamp saleDate, String idLocation, String diagnosis, int subtotal) {
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
}