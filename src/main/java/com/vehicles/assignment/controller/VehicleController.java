package com.vehicles.assignment.controller;

import com.vehicles.assignment.exception.ResourceNotFoundException;
import com.vehicles.assignment.model.Vehicle;
import com.vehicles.assignment.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Vehicle controller.
 *
 * @author Michael Baqadi
 */
@RestController
@RequestMapping("/codingchallenge/v1")
public class VehicleController {

  @Autowired
  private VehicleRepository vehicleRepository;

  /**
   * Get all vehicles list.
   *
   * @return the list
   */
  @GetMapping("/vehicles")
  public List<Vehicle> getAllVehicles() {
    return vehicleRepository.findAll();
  }

  /**
   * Gets vehicles by id.
   *
   * @param vehicleId the vehicle id
   * @return the vehicles by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/vehicles/{id}")
  public ResponseEntity<Vehicle> getVehiclesById(@PathVariable(value = "id") Long vehicleId)
      throws ResourceNotFoundException {
    Vehicle vehicle =
        vehicleRepository
            .findById(vehicleId)
            .orElseThrow(() -> new ResourceNotFoundException("vehicle not found on :: " + vehicleId));
    return ResponseEntity.ok().body(vehicle);
  }

  /**
   * Create vehicle vehicle.
   *
   * @param vehicle the vehicle
   * @return the vehicle
   */
  @PostMapping("/vehicles")
  public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }

  /**
   * Update vehicle response entity.
   *
   * @param vehicleId the vehicle id
   * @param vehicleDetails the vehicle details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/vehicles/{id}")
  public ResponseEntity<Vehicle> updateVehicle(
      @PathVariable(value = "id") Long vehicleId, @Valid @RequestBody Vehicle vehicleDetails)
      throws ResourceNotFoundException {

    Vehicle vehicle =
        vehicleRepository
            .findById(vehicleId)
            .orElseThrow(() -> new ResourceNotFoundException("vehicle not found on :: " + vehicleId));

    vehicle.setYear(vehicleDetails.getYear());
    vehicle.setModel(vehicleDetails.getModel());
    vehicle.setMake(vehicleDetails.getMake());
    vehicle.setUpdatedAt(new Date());
    final Vehicle updateVehicle = vehicleRepository.save(vehicle);
    return ResponseEntity.ok(updateVehicle);
  }

  /**
   * Delete vehicle map.
   *
   * @param vehicleId the vehicle id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/vehicles/{id}")
  public Map<String, Boolean> deleteVehicle(@PathVariable(value = "id") Long vehicleId) throws Exception {
    Vehicle vehicle =
        vehicleRepository
            .findById(vehicleId)
            .orElseThrow(() -> new ResourceNotFoundException("vehicle not found on :: " + vehicleId));

    vehicleRepository.delete(vehicle);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
