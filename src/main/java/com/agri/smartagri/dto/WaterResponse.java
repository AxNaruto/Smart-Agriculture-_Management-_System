package com.agri.smartagri.dto;

public class WaterResponse {

    private String cropName;
    private Double acreage;
    private String growthStage;
    private Double growthStageFactor;
    private Double dailyWaterLiters;
    private String irrigationMethodSuggestion;
    private String notes;

    public WaterResponse() {
    }

    public WaterResponse(String cropName, Double acreage, String growthStage, 
                         Double growthStageFactor, Double dailyWaterLiters, 
                         String irrigationMethodSuggestion, String notes) {
        this.cropName = cropName;
        this.acreage = acreage;
        this.growthStage = growthStage;
        this.growthStageFactor = growthStageFactor;
        this.dailyWaterLiters = dailyWaterLiters;
        this.irrigationMethodSuggestion = irrigationMethodSuggestion;
        this.notes = notes;
    }

    // Getters and Setters
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

    public Double getGrowthStageFactor() {
        return growthStageFactor;
    }

    public void setGrowthStageFactor(Double growthStageFactor) {
        this.growthStageFactor = growthStageFactor;
    }

    public Double getDailyWaterLiters() {
        return dailyWaterLiters;
    }

    public void setDailyWaterLiters(Double dailyWaterLiters) {
        this.dailyWaterLiters = dailyWaterLiters;
    }

    public String getIrrigationMethodSuggestion() {
        return irrigationMethodSuggestion;
    }

    public void setIrrigationMethodSuggestion(String irrigationMethodSuggestion) {
        this.irrigationMethodSuggestion = irrigationMethodSuggestion;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
