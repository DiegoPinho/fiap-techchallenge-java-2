package com.fiap.techchallenge.diegopinho.parkingmeter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {

  Optional<Vehicle> findByLicensePlate(String licensePlate);
}
