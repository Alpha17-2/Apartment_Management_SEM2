package Models;

public class Apartment {
    private int apartmentId;
    private String address;
    private int numberOfRooms;
    private double rentAmount;

    // Constructors, getters, and setters

    public Apartment(int apartmentId, String address, int numberOfRooms, double rentAmount) {
        this.apartmentId = apartmentId;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.rentAmount = rentAmount;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    // Additional methods if needed
}

