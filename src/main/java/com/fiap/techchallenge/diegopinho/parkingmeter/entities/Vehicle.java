package com.fiap.techchallenge.diegopinho.parkingmeter.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table(name = "vehicles")
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" }, callSuper = true)
@Entity(name = "vehicle")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String label;

  @Column(name = "vehicle_type")
  @Enumerated(EnumType.STRING)
  private VehicleType type;

  @Column(name = "license_plate")
  private String licensePlate;

  private String model;
  private String manufacturer;

  @OneToMany(mappedBy = "vehicle")
  private List<Park> parks;

  public Vehicle() {

  }

  public Vehicle(String label, VehicleType type, String licensePlate, String model, String manufacturer) {
    this.label = label;
    this.type = type;
    this.licensePlate = licensePlate;
    this.model = model;
    this.manufacturer = manufacturer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public VehicleType getType() {
    return type;
  }

  public void setType(VehicleType type) {
    this.type = type;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

}
