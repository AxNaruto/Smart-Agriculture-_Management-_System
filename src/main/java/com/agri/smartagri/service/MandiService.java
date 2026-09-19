package com.agri.smartagri.service;

import com.agri.smartagri.model.MandiListing;
import com.agri.smartagri.repository.MandiListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MandiService {

    private final MandiListingRepository mandiListingRepository;

    public MandiService(MandiListingRepository mandiListingRepository) {
        this.mandiListingRepository = mandiListingRepository;
    }

    public List<MandiListing> getAllListings() {
        return mandiListingRepository.findAllByOrderByListedDateDesc();
    }

    public Optional<MandiListing> getListingById(Long id) {
        return mandiListingRepository.findById(id);
    }

    public MandiListing createListing(MandiListing listing) {
        return mandiListingRepository.save(listing);
    }

    public boolean deleteListing(Long id) {
        if (mandiListingRepository.existsById(id)) {
            mandiListingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
