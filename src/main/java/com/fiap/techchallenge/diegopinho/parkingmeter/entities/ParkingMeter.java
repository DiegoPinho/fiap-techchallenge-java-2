package com.fiap.techchallenge.diegopinho.parkingmeter.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table(name = "parking_meters")
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" }, callSuper = true)
@Entity(name = "parkingMeter")
public class ParkingMeter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String serial;
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address address;

  public ParkingMeter() {

  }

  public ParkingMeter(String serial, BigDecimal price) {
    this.serial = serial;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
