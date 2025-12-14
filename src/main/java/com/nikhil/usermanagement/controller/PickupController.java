package com.nikhil.usermanagement.controller;

import com.nikhil.usermanagement.model.Pickup;
import com.nikhil.usermanagement.service.PickupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pickups")
@CrossOrigin("*")
public class PickupController {

    private final PickupService service;

    public PickupController(PickupService service) {
        this.service = service;
    }

    // NGO requests pickup
    @PostMapping("/request")
    public Pickup requestPickup(@RequestBody Pickup pickup) {
        return service.requestPickup(pickup);
    }

    // Donor accepts
    @PutMapping("/{id}/accept")
    public Pickup acceptPickup(@PathVariable String id) {
        return service.acceptPickup(id);
    }

    // NGO marks picked
    @PutMapping("/{id}/picked")
    public Pickup markPicked(@PathVariable String id) {
        return service.markPicked(id);
    }

    // Cancel
    @PutMapping("/{id}/cancel")
    public Pickup cancelPickup(@PathVariable String id) {
        return service.cancelPickup(id);
    }

    // Donor view
    @GetMapping("/donor/{donorId}")
    public List<Pickup> donorPickups(@PathVariable String donorId) {
        return service.getPickupsByDonor(donorId);
    }

    // NGO view
    @GetMapping("/ngo/{ngoId}")
    public List<Pickup> ngoPickups(@PathVariable String ngoId) {
        return service.getPickupsByNgo(ngoId);
    }

    // Admin
    @GetMapping
    public List<Pickup> allPickups() {
        return service.getAllPickups();
    }
}
