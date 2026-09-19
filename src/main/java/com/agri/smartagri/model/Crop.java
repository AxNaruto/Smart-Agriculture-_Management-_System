package com.agri.smartagri.model;

import jakarta.persistence.*;

/**
 * Entity representing crop specifications and baseline N-P-K nutrient needs.
 */
@Entity
@Table(name = "crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "soil_type", nullable = false, length = 100)
    private String soilType;

    @Column(name = "base_n", nullable = false)
    private Double baseN; // Base Nitrogen requirement (kg/acre)

    @Column(name = "base_p", nullable = false)
    private Double baseP; // Base Phosphorus requirement (kg/acre)

    @Column(name = "base_k", nullable = false)
    private Double baseK; // Base Potassium requirement (kg/acre)

    @Column(length = 255)
    private String description;

    public Crop() {
    }

    public Crop(Long id, String name, String soilType, Double baseN, Double baseP, Double baseK, String description) {
        this.id = id;
        this.name = name;
        this.soilType = soilType;
        this.baseN = baseN;
        this.baseP = baseP;
        this.baseK = baseK;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public Double getBaseN() {
        return baseN;
    }

    public void setBaseN(Double baseN) {
        this.baseN = baseN;
    }

    public Double getBaseP() {
        return baseP;
    }

    public void setBaseP(Double baseP) {
        this.baseP = baseP;
    }

    public Double getBaseK() {
        return baseK;
    }

    public void setBaseK(Double baseK) {
        this.baseK = baseK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
