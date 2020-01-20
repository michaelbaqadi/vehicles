package com.vehicles.assignment.repository;

import com.vehicles.assignment.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 *
 * @author Michael Baqadi
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {}
