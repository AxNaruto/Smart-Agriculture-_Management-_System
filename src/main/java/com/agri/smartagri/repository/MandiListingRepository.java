package com.agri.smartagri.repository;

import com.agri.smartagri.model.MandiListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MandiListingRepository extends JpaRepository<MandiListing, Long> {

    List<MandiListing> findAllByOrderByListedDateDesc();

    List<MandiListing> findByCropNameIgnoreCase(String cropName);

    List<MandiListing> findByLocationContainingIgnoreCase(String location);
}
