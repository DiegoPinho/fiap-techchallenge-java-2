package com.fiap.techchallenge.diegopinho.parkingmeter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.ParkDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Park;
import com.fiap.techchallenge.diegopinho.parkingmeter.exceptions.NotFoundException;
import com.fiap.techchallenge.diegopinho.parkingmeter.services.ParkService;

@RestController
@RequestMapping("/parks")
public class ParkController {

  @Autowired
  private ParkService parkService;

  @PostMapping
  public ResponseEntity<?> park(@RequestBody ParkDTO parkDTO) {
    try {
      Park park = this.parkService.park(parkDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(park);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PutMapping("/{id}/finish")
  public ResponseEntity<?> update(@PathVariable("id") Long id) {
    try {
      Park park = this.parkService.finish(id);
      return ResponseEntity.ok().body(park);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

  }
}
