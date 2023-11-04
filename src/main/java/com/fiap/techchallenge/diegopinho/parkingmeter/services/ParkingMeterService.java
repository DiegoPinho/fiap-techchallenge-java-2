package com.fiap.techchallenge.diegopinho.parkingmeter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.ParkingMeterDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Address;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.ParkingMeter;
import com.fiap.techchallenge.diegopinho.parkingmeter.exceptions.ConflictException;
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
        .orElseThrow(() -> new IllegalArgumentException("Parking Meter Not Found."));
  }

  public ParkingMeter create(ParkingMeterDTO parkingMeterDTO) {
    this.checkUniqueSerial(parkingMeterDTO.getSerial());

    ParkingMeter parkingMeter = parkingMeterDTO.toParkingMeter();
    Address address = this.addressService.getById(parkingMeterDTO.getAddressId());
    parkingMeter.setAddress(address);

    return this.parkingMeterRepository.save(parkingMeter);
  }

  public ParkingMeter update(Long id, ParkingMeterDTO parkingMeterDTO) {
    ParkingMeter parkingMeter = this.getById(id);
    if (!parkingMeter.getSerial().equals(parkingMeterDTO.getSerial())) {
      this.checkUniqueSerial(parkingMeterDTO.getSerial());
    }

    ParkingMeter updatedParkingMeter = parkingMeterDTO.toParkingMeter();
    Address address = this.addressService.getById(parkingMeterDTO.getAddressId());
    updatedParkingMeter.setId(id);
    updatedParkingMeter.setAddress(address);

    return this.parkingMeterRepository.save(updatedParkingMeter);
  }

  public void delete(Long id) {
    this.getById(id); // checks if exists
    this.parkingMeterRepository.deleteById(id);
  }

  private void checkUniqueSerial(String serial) {
    Optional<ParkingMeter> parkingMeterWithSerial = this.parkingMeterRepository
        .findBySerial(serial);
    if (parkingMeterWithSerial.isPresent()) {
      throw new ConflictException("Serial for parking meter already registered.");
    }
  }

}
