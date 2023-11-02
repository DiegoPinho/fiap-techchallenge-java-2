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

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.ParkingMeterDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.ParkingMeter;
import com.fiap.techchallenge.diegopinho.parkingmeter.services.ParkingMeterService;
import com.fiap.techchallenge.diegopinho.parkingmeter.utils.DTOValidator;

@RestController
@RequestMapping("/parkingmeters")
public class ParkingMeterController {

  @Autowired
  private ParkingMeterService parkingMeterService;

  @Autowired
  private DTOValidator validator;

  @GetMapping
  public ResponseEntity<?> getAll() {
    List<ParkingMeter> parkingMeters = this.parkingMeterService.getAll();
    return ResponseEntity.ok().body(parkingMeters);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    try {
      ParkingMeter parkingMeter = this.parkingMeterService.getById(id);
      return ResponseEntity.ok().body(parkingMeter);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody ParkingMeterDTO parkingMeterDTO) {
    Map<Object, Object> violations = validator.check(parkingMeterDTO);
    if (!violations.isEmpty()) {
      return ResponseEntity.badRequest().body(violations);
    }

    ParkingMeter parkingMeter = this.parkingMeterService.create(parkingMeterDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(parkingMeter);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody ParkingMeterDTO parkingMeterDTO, @PathVariable("id") Long id) {
    try {
      this.parkingMeterService.update(id, parkingMeterDTO);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      this.parkingMeterService.delete(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parking Meter not found or in use.");
    }
  }

}
