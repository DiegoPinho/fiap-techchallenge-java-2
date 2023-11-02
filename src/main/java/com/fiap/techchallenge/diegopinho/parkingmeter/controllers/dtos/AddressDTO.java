package com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Address;

import jakarta.validation.constraints.NotBlank;

public class AddressDTO {

  @JsonProperty
  @NotBlank(message = "Street is required and cannot be blank")
  private String street;

  @JsonProperty
  @NotBlank(message = "District is required and cannot be blank")
  private String district;

  @JsonProperty
  @NotBlank(message = "City is required and cannot be blank")
  private String city;

  @JsonProperty
  @NotBlank(message = "State is required and cannot be blank")
  private String state;

  public Address toAddress() {
    return new Address(this.street, this.district, this.city, this.state);
  }
}
