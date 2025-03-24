package com.natursalas.natursalassystem.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class SaleDTO {
    private String idSale;
    private String DNI;
    private String diagnosis;
    private String category;
    private Timestamp saleDate;
    private String idLocation;
    private BigDecimal subtotal;

    public SaleDTO() {
    }

    public SaleDTO(String idSale, String DNI, String diagnosis, String category, Timestamp saleDate, String idLocation, BigDecimal subtotal) {
        this.idSale = idSale;
        this.DNI = DNI;
        this.diagnosis = diagnosis;
        this.category = category;
        this.saleDate = saleDate;
        this.idLocation = idLocation;
        this.subtotal = subtotal;
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

    public void setSaleDate() {
        this.saleDate = new Timestamp(System.currentTimeMillis());
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "SaleDTO{" + "idSale='" + idSale + '\'' + ", DNI='" + DNI + '\'' + ", diagnosis='" + diagnosis + '\'' + ", category='" + category + '\'' + ", saleDate=" + saleDate + ", idLocation='" + idLocation + '\'' + ", subtotal=" + subtotal + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDTO saleDTO = (SaleDTO) o;
        return Objects.equals(subtotal, saleDTO.subtotal) && Objects.equals(idSale, saleDTO.idSale) && Objects.equals(DNI, saleDTO.DNI) && Objects.equals(diagnosis, saleDTO.diagnosis) && Objects.equals(category, saleDTO.category) && Objects.equals(saleDate, saleDTO.saleDate) && Objects.equals(idLocation, saleDTO.idLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSale, DNI, diagnosis, category, saleDate, idLocation, subtotal);
    }
}