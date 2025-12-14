package com.nikhil.usermanagement.service;

import com.nikhil.usermanagement.model.Pickup;
import com.nikhil.usermanagement.repository.PickupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickupService {

    private final PickupRepository repo;

    public PickupService(PickupRepository repo) {
        this.repo = repo;
    }

    // NGO requests pickup
    public Pickup requestPickup(Pickup pickup) {
        pickup.setStatus("REQUESTED");
        return repo.save(pickup);
    }

    // Donor accepts pickup
    public Pickup acceptPickup(String pickupId) {
        Pickup pickup = repo.findById(pickupId)
                .orElseThrow(() -> new RuntimeException("Pickup not found"));

        pickup.setStatus("ACCEPTED");
        return repo.save(pickup);
    }

    // NGO marks picked
    public Pickup markPicked(String pickupId) {
        Pickup pickup = repo.findById(pickupId)
                .orElseThrow(() -> new RuntimeException("Pickup not found"));

        pickup.setStatus("PICKED");
        return repo.save(pickup);
    }

    // Cancel pickup
    public Pickup cancelPickup(String pickupId) {
        Pickup pickup = repo.findById(pickupId)
                .orElseThrow(() -> new RuntimeException("Pickup not found"));

        pickup.setStatus("CANCELLED");
        return repo.save(pickup);
    }

    public List<Pickup> getPickupsByDonor(String donorId) {
        return repo.findByDonorId(donorId);
    }

    public List<Pickup> getPickupsByNgo(String ngoId) {
        return repo.findByNgoId(ngoId);
    }

    public List<Pickup> getAllPickups() {
        return repo.findAll();
    }
}
