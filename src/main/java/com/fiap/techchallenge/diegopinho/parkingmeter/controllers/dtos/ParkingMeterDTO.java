package com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.ParkingMeter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParkingMeterDTO {
  @JsonProperty
  @NotBlank(message = "Serial is required and cannot be blank")
  private String serial;

  @JsonProperty
  @NotNull(message = "Price is required and cannot be blank")
  @NotBlank(message = "Price is required and cannot be blank")
  private Integer price;

  @JsonProperty
  @NotBlank(message = "AddressId is required and cannot be blank")
  private Long addressId;

  public ParkingMeter toParkingMeter() {
    return new ParkingMeter(serial, price);
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

}
