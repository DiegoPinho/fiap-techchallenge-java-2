package com.fiap.techchallenge.diegopinho.parkingmeter.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.criterias.VehicleCriteria;
import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.VehicleDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Vehicle;
import com.fiap.techchallenge.diegopinho.parkingmeter.services.VehicleService;
import com.fiap.techchallenge.diegopinho.parkingmeter.utils.DTOValidator;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @Autowired
  private DTOValidator validator;

  @GetMapping
  public ResponseEntity<?> getAll(VehicleCriteria criteira) {
    List<Vehicle> person = this.vehicleService.getAll(criteira);
    return ResponseEntity.ok().body(person);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    try {
      Vehicle vehicle = this.vehicleService.getById(id);
      return ResponseEntity.ok().body(vehicle);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody VehicleDTO vehicleDTO) {
    Map<Object, Object> violations = validator.check(vehicleDTO);
    if (!violations.isEmpty()) {
      return ResponseEntity.badRequest().body(violations);
    }

    Vehicle vehicle = this.vehicleService.create(vehicleDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody VehicleDTO vehicleDTO, @PathVariable("id") Long id) {
    try {
      this.vehicleService.update(id, vehicleDTO);
      return ResponseEntity.ok().body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      this.vehicleService.delete(id);
      return ResponseEntity.ok().body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Vehicle not found or in use");
    }
  }

}
