package com.agri.smartagri.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FertilizerRequest {

    @NotBlank(message = "Crop name is required")
    private String cropName;

    @NotBlank(message = "Soil type is required")
    private String soilType;

    @NotNull(message = "Acreage is required")
    @Min(value = 0, message = "Acreage must be greater than zero")
    private Double acreage;

    public FertilizerRequest() {
    }

    public FertilizerRequest(String cropName, String soilType, Double acreage) {
        this.cropName = cropName;
        this.soilType = soilType;
        this.acreage = acreage;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public Double getAcreage() {
        return acreage;
    }

    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }
}
