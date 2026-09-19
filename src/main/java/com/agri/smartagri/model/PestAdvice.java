package com.agri.smartagri.model;

import jakarta.persistence.*;

/**
 * Entity representing crop pest symptoms and their biological / chemical remedies.
 */
@Entity
@Table(name = "pest_advices")
public class PestAdvice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crop_name", nullable = false, length = 100)
    private String cropName;

    @Column(nullable = false, length = 255)
    private String symptom;

    @Column(name = "bio_remedy", nullable = false, columnDefinition = "TEXT")
    private String bioRemedy;

    @Column(name = "chemical_dosage", nullable = false, columnDefinition = "TEXT")
    private String chemicalDosage;

    public PestAdvice() {
    }

    public PestAdvice(Long id, String cropName, String symptom, String bioRemedy, String chemicalDosage) {
        this.id = id;
        this.cropName = cropName;
        this.symptom = symptom;
        this.bioRemedy = bioRemedy;
        this.chemicalDosage = chemicalDosage;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getBioRemedy() {
        return bioRemedy;
    }

    public void setBioRemedy(String bioRemedy) {
        this.bioRemedy = bioRemedy;
    }

    public String getChemicalDosage() {
        return chemicalDosage;
    }

    public void setChemicalDosage(String chemicalDosage) {
        this.chemicalDosage = chemicalDosage;
    }
}
