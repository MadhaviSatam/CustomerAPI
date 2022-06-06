# Customer API

The **Customer API** is REST API which allows to maintain contract for customer. API uses H2 database to store customer data.

### Run API locally

#### Build application
```mvn clean install```

#### Run application
```mvn spring-boot:run```

Note: the application starts on port 8083.

#### Test API

##### GET (Read Customer details)
```GET http://localhost:8083/api/customer/<id>```

##### POST (Create new customer)
```POST http://localhost:8083/api/customer```
Request body example:
```
{
    "name": "test",
    "street": "street",
    "houseNumber": 2,
    "zipcode": "dsfdf",
    "place": "dfdff",
    "email": "hdhfhfh",
    "phoneNumber": 34353455
}
```

##### PUT (Update customer details)
```PUT http://localhost:8083/api/customer/<id>```
Request body example:
```
{
"name": "test1",
"street": "street1",
"houseNumber": 21,
"zipcode": "dsfdf1",
"place": "dfdff1",
"email": "hdhfhfh1",
"phoneNumber": 1111111
}
```

##### DELETE (Delete customer)
```DELETE http://localhost:8083/api/customer/<id>```
