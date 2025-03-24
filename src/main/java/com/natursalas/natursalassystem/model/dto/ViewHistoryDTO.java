package com.natursalas.natursalassystem.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ViewHistoryDTO {
    private String idSale;
    private Timestamp saleDate;
    private String idLocation;
    private String diagnosis;
    private BigDecimal subtotal;

    public ViewHistoryDTO() {
    }

    public ViewHistoryDTO(String idSale, Timestamp saleDate, String idLocation, String diagnosis, BigDecimal subtotal) {
        this.idSale = idSale;
        this.saleDate = saleDate;
        this.idLocation = idLocation;
        this.diagnosis = diagnosis;
        this.subtotal = subtotal;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}