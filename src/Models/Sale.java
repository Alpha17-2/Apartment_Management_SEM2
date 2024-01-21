package Models;

import java.util.Date;

public class Sale {
    private int saleId;
    private int apartmentId;
    private double salePrice;
    private Date saleDate;
    private int buyerId;

    // Constructors, getters, and setters

    public Sale(int saleId, int apartmentId, double salePrice, Date saleDate, int buyerId) {
        this.saleId = saleId;
        this.apartmentId = apartmentId;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
        this.buyerId = buyerId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    // Additional methods if needed
}

