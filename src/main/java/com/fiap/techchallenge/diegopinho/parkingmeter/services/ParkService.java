package com.fiap.techchallenge.diegopinho.parkingmeter.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.ParkDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Park;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.ParkingMeter;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Vehicle;
import com.fiap.techchallenge.diegopinho.parkingmeter.exceptions.NotFoundException;
import com.fiap.techchallenge.diegopinho.parkingmeter.repositories.ParkRepository;

@Service
public class ParkService {

  @Autowired
  private ParkRepository parkRepository;

  @Autowired
  private VehicleService vehicleService;

  @Autowired
  private ParkingMeterService parkingMeterService;

  public List<Park> getAll() {
    return this.parkRepository.findAll();
  }

  public Park getById(Long id) {
    return this.parkRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Park Not Found!"));
  }

  public Park park(ParkDTO parkDTO) {
    Vehicle vehicle = this.vehicleService.getById(parkDTO.getVehicleId());
    ParkingMeter parkingMeter = this.parkingMeterService.getById(parkDTO.getParkingMeterId());

    Park park = new Park();
    park.setVehicle(vehicle);
    park.setParkingMeter(parkingMeter);

    return this.parkRepository.save(park);
  }

  public Park finish(Long id) {
    Park park = this.getById(id);

    if (!(park.getEnd() == null)) {
      throw new RuntimeException("Parking already closed!");
    }

    park.setEnd(LocalDateTime.now());
    park.setTotal(this.calculatePrice(park));

    return this.parkRepository.save(park);
  }

  private BigDecimal calculatePrice(Park park) {
    ParkingMeter parkingMeter = park.getParkingMeter();
    BigDecimal parkingMeterPrice = parkingMeter.getPrice();

    Duration duration = Duration.between(park.getStart(), park.getEnd());
    BigDecimal minutes = BigDecimal.valueOf(duration.toMinutes());

    return minutes.multiply(parkingMeterPrice).divide(BigDecimal.valueOf(60), RoundingMode.HALF_UP);
  }

}
