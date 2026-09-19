package com.agri.smartagri.service;

import com.agri.smartagri.dto.FertilizerRequest;
import com.agri.smartagri.dto.FertilizerResponse;
import com.agri.smartagri.dto.WaterRequest;
import com.agri.smartagri.dto.WaterResponse;
import com.agri.smartagri.model.Crop;
import com.agri.smartagri.repository.CropRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CalculatorService {

    private final CropRepository cropRepository;
    private static final DecimalFormat DF = new DecimalFormat("#.##");

    public CalculatorService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public java.util.List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    /**
     * Calculates recommended N-P-K fertilizer quantity based on Crop, Soil Type, and Acreage.
     */
    public FertilizerResponse calculateFertilizer(FertilizerRequest request) {
        String cropName = request.getCropName().trim();
        String soilType = request.getSoilType().trim();
        double acreage = request.getAcreage();

        // 1. Try to find exact crop & soil match from database
        Optional<Crop> cropOpt = cropRepository.findFirstByNameIgnoreCaseAndSoilTypeIgnoreCase(cropName, soilType);

        double baseN;
        double baseP;
        double baseK;

        if (cropOpt.isPresent()) {
            Crop c = cropOpt.get();
            baseN = c.getBaseN();
            baseP = c.getBaseP();
            baseK = c.getBaseK();
        } else {
            // Standard Agricultural Baseline defaults (kg per acre) if not in DB
            Map<String, double[]> cropDefaults = new HashMap<>();
            cropDefaults.put("rice", new double[]{40.0, 20.0, 20.0});
            cropDefaults.put("wheat", new double[]{50.0, 25.0, 16.0});
            cropDefaults.put("cotton", new double[]{48.0, 24.0, 24.0});
            cropDefaults.put("maize", new double[]{48.0, 24.0, 20.0});
            cropDefaults.put("sugarcane", new double[]{100.0, 40.0, 48.0});

            double[] defaults = cropDefaults.getOrDefault(cropName.toLowerCase(), new double[]{40.0, 20.0, 20.0});
            baseN = defaults[0];
            baseP = defaults[1];
            baseK = defaults[2];

            // Soil multiplier adjustments
            double soilMultiplier = switch (soilType.toLowerCase()) {
                case "black" -> 0.90;   // High nutrient and clay content
                case "clay" -> 0.95;    // Good nutrient retention
                case "red" -> 1.10;     // Moderate leaching, requires 10% higher N
                case "sandy" -> 1.25;   // High porosity and leaching, requires 25% higher N
                default -> 1.0;         // Alluvial / Loamy is standard
            };

            baseN *= soilMultiplier;
            baseP *= soilMultiplier;
            baseK *= soilMultiplier;
        }

        double totalN = round(baseN * acreage);
        double totalP = round(baseP * acreage);
        double totalK = round(baseK * acreage);

        String schedule = "Apply 50% Nitrogen and 100% Phosphorus & Potassium at the time of sowing (Basal dose). " +
                "Top-dress remaining 25% Nitrogen during active vegetative growth and final 25% at flowering stage.";

        String remarks = String.format("Calculated for %.1f acre(s) of %s grown in %s soil.", acreage, cropName, soilType);

        return new FertilizerResponse(cropName, soilType, acreage, totalN, totalP, totalK, schedule, remarks);
    }

    /**
     * Calculates daily water requirement: Water (L) = Acreage * Growth Stage Factor * 1000
     */
    public WaterResponse calculateWater(WaterRequest request) {
        String cropName = request.getCropName().trim();
        double acreage = request.getAcreage();
        String stage = request.getGrowthStage().trim();

        // Growth Stage Factor mapping
        double factor;
        String normalizedStage;
        switch (stage.toLowerCase()) {
            case "germination", "initial" -> {
                factor = 2.5;
                normalizedStage = "Initial / Germination";
            }
            case "vegetative" -> {
                factor = 4.0;
                normalizedStage = "Vegetative Growth";
            }
            case "flowering", "mid-season", "mid season" -> {
                factor = 6.0;
                normalizedStage = "Flowering / Mid-Season";
            }
            case "maturity", "late-season", "harvesting" -> {
                factor = 3.0;
                normalizedStage = "Maturity / Late-Season";
            }
            default -> {
                factor = 3.5;
                normalizedStage = stage;
            }
        }

        // Daily water in Liters = Acreage * Growth Stage Factor * 1000
        double dailyWaterLiters = round(acreage * factor * 1000.0);

        String methodSuggestion = switch (cropName.toLowerCase()) {
            case "rice" -> "Continuous shallow standing water (2-5 cm) or Alternate Wetting and Drying (AWD).";
            case "cotton", "sugarcane" -> "Drip irrigation recommended to save 40% water and avoid root rot.";
            case "wheat", "maize" -> "Sprinkler irrigation or furrow irrigation at critical growth stages.";
            default -> "Drip or furrow irrigation depending on slope and soil porosity.";
        };

        String notes = String.format("Estimated daily water consumption for %.1f acre(s) during %s stage.", acreage, normalizedStage);

        return new WaterResponse(cropName, acreage, normalizedStage, factor, dailyWaterLiters, methodSuggestion, notes);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
