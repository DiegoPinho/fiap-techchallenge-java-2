--CREATE DATABASE fiap_techchallenge_2;

CREATE TABLE vehicles (
  id SERIAL PRIMARY KEY,
  label VARCHAR(255) NOT NULL,
  vehicle_type VARCHAR(10) NOT NULL,
  license_plate VARCHAR(7) NOT NULL,
  model VARCHAR(255) NOT NULL,
  manufacturer VARCHAR(255) NOT NULL
);

