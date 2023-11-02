package com.fiap.techchallenge.diegopinho.parkingmeter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fiap.techchallenge.diegopinho.parkingmeter.entities.Park;

public interface ParkRepository extends JpaRepository<Park, Long> {

}
