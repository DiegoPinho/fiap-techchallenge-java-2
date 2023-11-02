package com.fiap.techchallenge.diegopinho.parkingmeter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.ParkingMeterDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Address;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.ParkingMeter;
import com.fiap.techchallenge.diegopinho.parkingmeter.repositories.ParkingMeterRepository;

@Service
public class ParkingMeterService {

  @Autowired
  ParkingMeterRepository parkingMeterRepository;

  @Autowired
  AddressService addressService;

  public List<ParkingMeter> getAll() {
    return this.parkingMeterRepository.findAll();
  }

  public ParkingMeter getById(Long id) {
    return this.parkingMeterRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Parking Meter Not Found!"));
  }

  public ParkingMeter create(ParkingMeterDTO parkingMeterDTO) {
    ParkingMeter parkingMeter = parkingMeterDTO.toParkingMeter();

    Address address = this.addressService.getById(parkingMeterDTO.getAddressId());
    parkingMeter.setAddress(address);

    return this.parkingMeterRepository.save(parkingMeter);
  }

  public ParkingMeter update(Long id, ParkingMeterDTO parkingMeterDTO) {
    this.getById(id); // checks if exists

    ParkingMeter parkingMeter = parkingMeterDTO.toParkingMeter();
    Address address = this.addressService.getById(parkingMeterDTO.getAddressId());
    parkingMeter.setId(id);
    parkingMeter.setAddress(address);

    return this.parkingMeterRepository.save(parkingMeter);
  }

  public void delete(Long id) {
    this.getById(id); // checks if exists
    this.parkingMeterRepository.deleteById(id);
  }

}
