package com.agri.smartagri.repository;

import com.agri.smartagri.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    Optional<Crop> findFirstByNameIgnoreCaseAndSoilTypeIgnoreCase(String name, String soilType);

    Optional<Crop> findByNameIgnoreCaseAndSoilTypeIgnoreCase(String name, String soilType);

    List<Crop> findByNameIgnoreCase(String name);

    List<Crop> findBySoilTypeIgnoreCase(String soilType);
}
