package com.nikhil.usermanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "pickups")
public class Pickup {

    @Id
    private String id;

    private String foodId;
    private String donorId;
    private String ngoId;

    private String pickupAddress;
    private LocalDateTime pickupTime;

    private String status; 
    // REQUESTED, ACCEPTED, PICKED, CANCELLED

    private LocalDateTime createdAt;

    public Pickup() {
        this.createdAt = LocalDateTime.now();
        this.status = "REQUESTED";
    }

    // -------- GETTERS & SETTERS --------

    public String getId() {
        return id;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getNgoId() {
        return ngoId;
    }

    public void setNgoId(String ngoId) {
        this.ngoId = ngoId;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
