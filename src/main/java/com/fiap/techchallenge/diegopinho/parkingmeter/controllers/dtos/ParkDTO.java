package com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class ParkDTO {

  @JsonProperty
  @NotNull(message = "vehicleId is required and cannot be blank")
  private Long vehicleId;

  @JsonProperty
  @NotNull(message = "parkingMeterId is required and cannot be blank")
  private Long parkingMeterId;

  public Long getVehicleId() {
    return vehicleId;
  }

  public Long getParkingMeterId() {
    return parkingMeterId;
  }

}
