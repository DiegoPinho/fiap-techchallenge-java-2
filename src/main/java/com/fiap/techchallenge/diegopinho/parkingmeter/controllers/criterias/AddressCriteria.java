package com.fiap.techchallenge.diegopinho.parkingmeter.controllers.criterias;

import org.springframework.data.jpa.domain.Specification;

import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Address;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.Objects;

public class AddressCriteria {

  private String street;
  private String district;
  private String city;
  private String state;

  public Specification<Address> toSpecification() {
    return (Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.conjunction();

      if (Objects.nonNull(street)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("street"), street));
      }

      if (Objects.nonNull(district)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("district"), district));
      }

      if (Objects.nonNull(city)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("city"), city));
      }

      if (Objects.nonNull(state)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("state"), state));
      }

      return predicate;
    };
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

}
