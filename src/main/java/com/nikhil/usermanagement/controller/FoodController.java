package com.nikhil.usermanagement.controller;

import com.nikhil.usermanagement.model.Food;
import com.nikhil.usermanagement.service.FoodService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin("*")
public class FoodController {

    private final FoodService service;

    public FoodController(FoodService service) {
        this.service = service;
    }

    @PostMapping("/with-image")
    public Food addFood(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam int quantity,
            @RequestParam String foodType,
            @RequestParam String location,
            @RequestParam String donorId,
            @RequestParam String donorName,
            @RequestParam String expiryTime,
            @RequestParam MultipartFile image
    ) throws Exception {

        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.write(filePath, image.getBytes());

        Food food = new Food();
        food.setTitle(title);
        food.setDescription(description);
        food.setQuantity(quantity);
        food.setFoodType(foodType);
        food.setLocation(location);
        food.setDonorId(donorId);
        food.setDonorName(donorName);
        food.setExpiryTime(LocalDateTime.parse(expiryTime));
        food.setImageUrl("/uploads/" + fileName);

        return service.addFood(food);
    }

    @GetMapping("/available")
    public List<Food> availableFood() {
        return service.getAvailableFood();
    }

    @PostMapping("/{id}/request")
    public Food requestFood(@PathVariable String id) {
        return service.requestFood(id);
    }

    @PostMapping("/{id}/pickup")
    public Food pickupFood(@PathVariable String id) {
        return service.pickupFood(id);
    }
}
