package com.natursalas.natursalassystem.model.dto;

import java.sql.Timestamp;

public class SaleSpecialDTO {
    private String idSale;
    private String idLocation;
    private Timestamp saleDate;
    private String DNI;
    private String firstName;
    private String diagnosis;
    private int subtotal;

    public SaleSpecialDTO() {
    }

    public SaleSpecialDTO(String idSale, String idLocation, Timestamp saleDate, String DNI, String firstName, String diagnosis, int subtotal) {
        this.idSale = idSale;
        this.idLocation = idLocation;
        this.saleDate = saleDate;
        this.DNI = DNI;
        this.firstName = firstName;
        this.diagnosis = diagnosis;
        this.subtotal = subtotal;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
