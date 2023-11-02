--CREATE DATABASE fiap_techchallenge_2;

CREATE TABLE vehicles (
  id SERIAL PRIMARY KEY,
  label VARCHAR(255) NOT NULL,
  vehicle_type VARCHAR(10) NOT NULL,
  license_plate VARCHAR(7) NOT NULL,
  model VARCHAR(255) NOT NULL,
  manufacturer VARCHAR(255) NOT NULL
);

CREATE TABLE addresses (
  id SERIAL PRIMARY KEY,
  street VARCHAR(255) NOT NULL,
  district VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  state VARCHAR(255) NOT NULL
);

CREATE TABLE parking_meters {
  id SERIAL PRIMARY KEY,
  serial VARCHAR(255) NOT NULL,
  price INTEGER,
  address_id INTEGER,

  FOREIGN_KEY(address_id) REFERENCES addresses(id)
}