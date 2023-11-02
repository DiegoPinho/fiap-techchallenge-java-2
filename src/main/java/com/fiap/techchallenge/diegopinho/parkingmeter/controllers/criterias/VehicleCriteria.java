package com.fiap.techchallenge.diegopinho.parkingmeter.controllers.criterias;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Vehicle;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.VehicleType;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class VehicleCriteria {

  private String model;
  private String manufacturer;
  private VehicleType type;

  public Specification<Vehicle> toSpecification() {
    return (Root<Vehicle> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.conjunction();

      if (Objects.nonNull(model)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("model"), model));
      }

      if (Objects.nonNull(manufacturer)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("manufacturer"), manufacturer));
      }

      if (Objects.nonNull(type)) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("type"), type));
      }

      return predicate;
    };
  }

}
