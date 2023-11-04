package com.fiap.techchallenge.diegopinho.parkingmeter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.criterias.AddressCriteria;
import com.fiap.techchallenge.diegopinho.parkingmeter.controllers.dtos.AddressDTO;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Address;
import com.fiap.techchallenge.diegopinho.parkingmeter.exceptions.NotFoundException;
import com.fiap.techchallenge.diegopinho.parkingmeter.repositories.AddressRepository;

@Service
public class AddressService {

  @Autowired
  AddressRepository addressRepository;

  public List<Address> getAll(AddressCriteria criteria) {
    Specification<Address> specification = criteria.toSpecification();
    return this.addressRepository.findAll(specification);
  }

  public Address getById(Long id) {
    return this.addressRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Address Not Found!"));
  }

  public Address create(AddressDTO addressDTO) {
    Address address = addressDTO.toAddress();
    return this.addressRepository.save(address);
  }

  public void update(Long id, AddressDTO addressDTO) {
    this.getById(id); // checks if exists

    Address address = addressDTO.toAddress();
    address.setId(id);
    this.addressRepository.save(address);
  }

  public void delete(Long id) {
    this.getById(id); // checks if exists
    this.addressRepository.deleteById(id);
  }

}
