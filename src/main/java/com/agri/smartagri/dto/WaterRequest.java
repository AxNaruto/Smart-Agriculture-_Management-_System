package com.agri.smartagri.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WaterRequest {

    @NotBlank(message = "Crop name is required")
    private String cropName;

    @NotNull(message = "Acreage is required")
    @Min(value = 0, message = "Acreage must be greater than zero")
    private Double acreage;

    @NotBlank(message = "Growth stage is required")
    private String growthStage; // Initial/Germination, Vegetative, Flowering, Maturity

    public WaterRequest() {
    }

    public WaterRequest(String cropName, Double acreage, String growthStage) {
        this.cropName = cropName;
        this.acreage = acreage;
        this.growthStage = growthStage;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Double getAcreage() {
        return acreage;
    }

    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }

    public String getGrowthStage() {
        return growthStage;
    }

    public void setGrowthStage(String growthStage) {
        this.growthStage = growthStage;
    }
}
