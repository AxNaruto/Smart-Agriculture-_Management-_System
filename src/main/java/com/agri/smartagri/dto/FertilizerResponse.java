package com.agri.smartagri.dto;

public class FertilizerResponse {

    private String cropName;
    private String soilType;
    private Double acreage;
    private Double totalNitrogenKg;
    private Double totalPhosphorusKg;
    private Double totalPotassiumKg;
    private String recommendedSchedule;
    private String remarks;

    public FertilizerResponse() {
    }

    public FertilizerResponse(String cropName, String soilType, Double acreage, 
                              Double totalNitrogenKg, Double totalPhosphorusKg, Double totalPotassiumKg, 
                              String recommendedSchedule, String remarks) {
        this.cropName = cropName;
        this.soilType = soilType;
        this.acreage = acreage;
        this.totalNitrogenKg = totalNitrogenKg;
        this.totalPhosphorusKg = totalPhosphorusKg;
        this.totalPotassiumKg = totalPotassiumKg;
        this.recommendedSchedule = recommendedSchedule;
        this.remarks = remarks;
    }

    // Getters and Setters
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

    public Double getTotalNitrogenKg() {
        return totalNitrogenKg;
    }

    public void setTotalNitrogenKg(Double totalNitrogenKg) {
        this.totalNitrogenKg = totalNitrogenKg;
    }

    public Double getTotalPhosphorusKg() {
        return totalPhosphorusKg;
    }

    public void setTotalPhosphorusKg(Double totalPhosphorusKg) {
        this.totalPhosphorusKg = totalPhosphorusKg;
    }

    public Double getTotalPotassiumKg() {
        return totalPotassiumKg;
    }

    public void setTotalPotassiumKg(Double totalPotassiumKg) {
        this.totalPotassiumKg = totalPotassiumKg;
    }

    public String getRecommendedSchedule() {
        return recommendedSchedule;
    }

    public void setRecommendedSchedule(String recommendedSchedule) {
        this.recommendedSchedule = recommendedSchedule;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
