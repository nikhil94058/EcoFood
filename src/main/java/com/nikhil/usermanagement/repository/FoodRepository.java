package com.nikhil.usermanagement.repository;

import com.nikhil.usermanagement.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String> {

    List<Food> findByStatus(String status);
    List<Food> findByDonorId(String donorId);
}
