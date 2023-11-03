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

CREATE TABLE parking_meters (
  id SERIAL PRIMARY KEY,
  serial VARCHAR(255) NOT NULL,
  price NUMERIC(10,2),
  address_id INTEGER,

  CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE parks (
  id SERIAL PRIMARY KEY,
  vehicle_id INTEGER NOT NULL,
  parking_meter_id INTEGER NOT NULL,
  start_date DATE DEFAULT current_date,
  end_date DATE,
  total NUMERIC(10,2),

  CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
  CONSTRAINT fk_parking_meter FOREIGN KEY (parking_meter_id) REFERENCES parking_meters(id)
);
