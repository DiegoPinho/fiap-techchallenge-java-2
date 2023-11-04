# FIAP - Tech Challenge 2 (Java)

## How to run

- You'll need a Postgres database.
- The script with all the tables needed is inside de `resources/` directory. Please, run it before start the application.
- Check the database configuration inside de `application.properties` file. You probably gonna have to change it.

## How it works
- Workflow:
  - Create a parking spot.
  - Create a car.
  - Park a car into a parking spot. Billing will start.
  - Close the parking and the billing will be calculated.
  - Pay the parking bill.

## API Documentation

- This project includes a thunder-collection file that can be used with ThunderClient to test this API.

## Health

`GET /health`
Checks if the API is ok.


## Vehicles
`GET /vehicles`
Return all vehicles. You can use filters by query params.

`GET /vehicles/:id`
Return a vehicle based on the id.

`POST /vehicles`
Creates a new vehicle.

body (JSON):
```
{
  "label": String
  "type": "BUS, CAR, TRUCK, MOTOCYCLE",
  "licencePlate": String,
  "model": String,
  "manufacturer": String
}
```

`PUT /vehicles/:id`
Updates a vehicle based on the id.

body (JSON):
```
{
  "label": String
  "type": "BUS, CAR, TRUCK, MOTOCYCLE",
  "licencePlate": String,
  "model": String,
  "manufacturer": String
}
```

`DELETE /vehicles/:id`
Deletes a vehicle based on the id. You can't delete a vehicle in use.

## Addresses
`GET /addresses`
Return all addressess. You can use filters by query params.

`GET /addresses/:id`
Return an address based on the id.

`POST /addresses`
Creates a new address.

body (JSON):
```
{
  "street": String
  "number": Integer
  "string": String
  "city": String
  "state": String
}
```

`PUT /addresses/:id`
Updates a address based on the id.

body (JSON):
```
{
  "street": String
  "number": Integer
  "string": String
  "city": String
  "state": String
}
```

`DELETE /addresses/:id`
Deletes a address based on the id. You can't delete an address in use.


## Parking Meters
`GET /parkingmeter`
Return all parking meters. You can use filters by query params.

`GET /parkingmeter/:id`
Return a parking meter based on the id.

`POST /parkingmeter`
Creates a new parking meter.

body (JSON):
```
{
  "serial": String,
  "price": Decimal,
  "addressId": Integer
}
```

`PUT /parkingmeter/:id`
Updates a parking meter based on the id.

body (JSON):
```
{
  "serial": String,
  "price": Decimal,
  "addressId": Integer
}
```

`DELETE /parkingmeter/:id`
Deletes a parking meter based on the id. You can't delete an parking meter in use.

## Parks
`POST /parks`
Allocates a parking spot with a vehicle using a parking meter.

body (JSON):
```
{
  "vehicleId": Integer,
  "parkingMeterId": Integer
}
```

`PUT /parks/:id/finish`
Finish a parking.