package Models;
public class Tenant {
    private int tenantId;
    private String name;
    private String contactNumber;
    private String email;

    // Constructors, getters, and setters

    public Tenant(int tenantId, String name, String contactNumber, String email) {
        this.tenantId = tenantId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Additional methods if needed
}

