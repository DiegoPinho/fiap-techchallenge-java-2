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
  price NUMERIC(10,2),
  address_id INTEGER,

  FOREIGN_KEY(address_id) REFERENCES addresses(id)
}

CREATE TABLE parks {
  id SERIAL PRIMARY KEY,
  vehicle_id INTEGER NOT NULL,
  parking_meter_id INTEGER NOT NULL,
  start DATE DEFAULT current_date,
  end DATE,
  total NUMERIC(10,2)

  FOREIGN_KEY(vehicle_id) REFERENCES vehicles(id),
  FOREIGN_KEY(parking_meter_id) REFERENCES parking_meters(id)
}