package com.fiap.techchallenge.diegopinho.parkingmeter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.criterias.VehicleCriteria;
import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.VehicleDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Vehicle;
import com.fiap.techchallenge.diegopinho.parkingmeter.repositories.VehicleRepository;

@Service
public class VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;

  public List<Vehicle> getAll(VehicleCriteria criteira) {
    Specification<Vehicle> specification = criteira.toSpecification();
    return this.vehicleRepository.findAll(specification);
  }

  public Vehicle getById(Long id) {
    return this.vehicleRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Vehicle Not Found!"));
  }

  public Vehicle create(VehicleDTO vehicleDTO) {
    Vehicle vehicle = vehicleDTO.toVehicle();
    return this.vehicleRepository.save(vehicle);
  }

  public void update(Long id, VehicleDTO vehicleDTO) {
    this.getById(id); // checks if exists

    Vehicle vehicle = vehicleDTO.toVehicle();
    vehicle.setId(id);
    this.vehicleRepository.save(vehicle);
  }

  public void delete(Long id) {
    this.getById(id); // checks if exists
    this.vehicleRepository.deleteById(id);
  }

}
