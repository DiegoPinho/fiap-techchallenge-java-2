package com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.ParkingMeter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParkingMeterDTO {
  @JsonProperty
  @NotBlank(message = "Serial is required and cannot be blank")
  private String serial;

  @JsonProperty
  @NotNull(message = "Price is required and cannot be null")
  private BigDecimal price;

  @JsonProperty
  @NotNull(message = "AddressId is required and cannot be null")
  private Long addressId;

  public ParkingMeter toParkingMeter() {
    return new ParkingMeter(serial, price);
  }

  public String getSerial() {
    return serial;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

}
