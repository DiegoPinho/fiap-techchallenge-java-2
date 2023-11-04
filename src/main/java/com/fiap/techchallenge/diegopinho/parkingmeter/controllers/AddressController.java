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

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.criterias.AddressCriteria;
import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.AddressDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Address;
import com.fiap.techchallenge.diegopinho.parkingmeter.exceptions.NotFoundException;
import com.fiap.techchallenge.diegopinho.parkingmeter.services.AddressService;
import com.fiap.techchallenge.diegopinho.parkingmeter.utils.DTOValidator;

@RestController
@RequestMapping("/addresses")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @Autowired
  private DTOValidator validator;

  @GetMapping
  public ResponseEntity<?> getAll(AddressCriteria criteria) {
    List<Address> addresses = this.addressService.getAll(criteria);
    return ResponseEntity.ok().body(addresses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    try {
      Address address = this.addressService.getById(id);
      return ResponseEntity.ok().body(address);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody AddressDTO addressDTO) {
    Map<Object, Object> violations = validator.check(addressDTO);
    if (!violations.isEmpty()) {
      return ResponseEntity.badRequest().body(violations);
    }

    Address address = this.addressService.create(addressDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(address);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody AddressDTO addressDTO, @PathVariable("id") Long id) {
    try {
      this.addressService.update(id, addressDTO);
      return ResponseEntity.ok().build();
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      this.addressService.delete(id);
      return ResponseEntity.ok().build();
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot remove resource in use.");
    }
  }

}
