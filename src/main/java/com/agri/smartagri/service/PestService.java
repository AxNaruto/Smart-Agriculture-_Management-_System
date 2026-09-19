package com.agri.smartagri.service;

import com.agri.smartagri.model.PestAdvice;
import com.agri.smartagri.repository.PestAdviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PestService {

    private final PestAdviceRepository pestAdviceRepository;

    public PestService(PestAdviceRepository pestAdviceRepository) {
        this.pestAdviceRepository = pestAdviceRepository;
    }

    public List<PestAdvice> getAllAdvice() {
        return pestAdviceRepository.findAll();
    }

    public List<PestAdvice> getAdviceByCrop(String cropName) {
        if (cropName == null || cropName.trim().isEmpty() || cropName.equalsIgnoreCase("all")) {
            return pestAdviceRepository.findAll();
        }
        return pestAdviceRepository.findByCropNameIgnoreCase(cropName.trim());
    }

    public List<PestAdvice> searchBySymptom(String symptom) {
        return pestAdviceRepository.findBySymptomContainingIgnoreCase(symptom.trim());
    }

    public Optional<PestAdvice> getAdviceById(Long id) {
        return pestAdviceRepository.findById(id);
    }
}
