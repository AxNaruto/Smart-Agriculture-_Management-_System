package com.agri.smartagri.repository;

import com.agri.smartagri.model.PestAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PestAdviceRepository extends JpaRepository<PestAdvice, Long> {

    List<PestAdvice> findByCropNameIgnoreCase(String cropName);

    List<PestAdvice> findBySymptomContainingIgnoreCase(String symptom);
}
