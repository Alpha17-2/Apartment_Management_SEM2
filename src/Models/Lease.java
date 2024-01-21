package Models;

import java.util.Date;

public class Lease {
    private int leaseId;
    private int apartmentId;
    private int tenantId;
    private Date startDate;
    private Date endDate;
    private double monthlyRent;

    // Constructors, getters, and setters

    public Lease(int leaseId, int apartmentId, int tenantId, Date startDate, Date endDate, double monthlyRent) {
        this.leaseId = leaseId;
        this.apartmentId = apartmentId;
        this.tenantId = tenantId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyRent = monthlyRent;
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    // Additional methods if needed
}
