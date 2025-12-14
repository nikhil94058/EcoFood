package com.nikhil.usermanagement.service;

import com.nikhil.usermanagement.model.Food;
import com.nikhil.usermanagement.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository repo;

    public FoodService(FoodRepository repo) {
        this.repo = repo;
    }

    public Food addFood(Food food) {
        food.setStatus("AVAILABLE");
        return repo.save(food);
    }

    public List<Food> getAvailableFood() {
        return repo.findByStatus("AVAILABLE");
    }

    public List<Food> getFoodByDonor(String donorId) {
        return repo.findByDonorId(donorId);
    }

    public Food requestFood(String foodId) {
        Food food = repo.findById(foodId).orElseThrow();
        food.setStatus("REQUESTED");
        return repo.save(food);
    }

    public Food pickupFood(String foodId) {
        Food food = repo.findById(foodId).orElseThrow();
        food.setStatus("PICKED_UP");
        return repo.save(food);
    }
}
