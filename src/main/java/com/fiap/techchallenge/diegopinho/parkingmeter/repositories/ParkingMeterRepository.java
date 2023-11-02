package com.fiap.techchallenge.diegopinho.parkingmeter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fiap.techchallenge.diegopinho.parkingmeter.entities.ParkingMeter;

public interface ParkingMeterRepository
                extends JpaRepository<ParkingMeter, Long>, JpaSpecificationExecutor<ParkingMeter> {

}
