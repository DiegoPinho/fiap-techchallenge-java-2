package com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Vehicle;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.VehicleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehicleDTO {
  @JsonProperty
  @NotBlank(message = "Label is required and cannot be blank")
  private String label;

  @JsonProperty
  @NotNull(message = "Vehicle Type is required and cannot be blank")
  private VehicleType type;

  @JsonProperty
  @NotBlank(message = "License Plate is required and cannot be blank")
  private String licensePlate;

  @JsonProperty
  @NotBlank(message = "Model is required and cannot be blank")
  private String model;

  @JsonProperty
  @NotBlank(message = "Manufacturer is required and cannot be blank")
  private String manufacturer;

  public Vehicle toVehicle() {
    return new Vehicle(this.label, this.type, this.licensePlate, this.model, this.manufacturer);
  }

  public String getLicensePlate() {
    return licensePlate;
  }
}
