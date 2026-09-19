package com.agri.smartagri.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity representing crop listings posted by farmers on the Mandi marketplace.
 */
@Entity
@Table(name = "mandi_listings")
public class MandiListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "farmer_name", nullable = false, length = 100)
    private String farmerName;

    @Column(name = "crop_name", nullable = false, length = 100)
    private String cropName;

    @Column(name = "quantity_quintals", nullable = false)
    private Double quantityQuintals;

    @Column(nullable = false)
    private Double price; // Expected price per quintal in INR

    @Column(nullable = false, length = 150)
    private String location; // Mandi / Town location

    @Column(nullable = false, length = 20)
    private String phone; // Farmer contact phone number

    @Column(name = "listed_date")
    private LocalDate listedDate;

    public MandiListing() {
        this.listedDate = LocalDate.now();
    }

    public MandiListing(Long id, String farmerName, String cropName, Double quantityQuintals, 
                        Double price, String location, String phone, LocalDate listedDate) {
        this.id = id;
        this.farmerName = farmerName;
        this.cropName = cropName;
        this.quantityQuintals = quantityQuintals;
        this.price = price;
        this.location = location;
        this.phone = phone;
        this.listedDate = (listedDate != null) ? listedDate : LocalDate.now();
    }

    @PrePersist
    public void prePersist() {
        if (this.listedDate == null) {
            this.listedDate = LocalDate.now();
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Double getQuantityQuintals() {
        return quantityQuintals;
    }

    public void setQuantityQuintals(Double quantityQuintals) {
        this.quantityQuintals = quantityQuintals;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getListedDate() {
        return listedDate;
    }

    public void setListedDate(LocalDate listedDate) {
        this.listedDate = listedDate;
    }
}
