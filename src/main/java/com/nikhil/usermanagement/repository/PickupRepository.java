package com.nikhil.usermanagement.repository;

import com.nikhil.usermanagement.model.Pickup;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PickupRepository extends MongoRepository<Pickup, String> {

    List<Pickup> findByDonorId(String donorId);

    List<Pickup> findByNgoId(String ngoId);

    List<Pickup> findByStatus(String status);
}
