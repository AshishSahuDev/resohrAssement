# resohrAssement

# Wedding Planner Management System

##Overview

The Wedding Planner Management System is a Spring Boot application that helps manage clients, vendors, events, bookings, and payments for wedding planning. The application uses RESTful APIs, with detailed documentation available through Swagger UI for easy testing and integration.

## Getting Started

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Spring Boot 3.4.0
- H2 in-memory database (default)
- OpenAPI (Swagger) for API documentation and testing

## Installation

1. Clone the repository:

   ```sh
   git clone <repository-url#
   cd WeddingPlannerMgmt
   ```

2. Build the project using Maven:

   ```sh
   mvn clean install
   ```

3. Run the application:

   ```sh
   mvn spring-boot:run
   ```

## Accessing the Application

- The application runs on **[http://localhost:8080](http://localhost:8080)**.
- Swagger UI for API testing is available at **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**.
- The OpenAPI specification can be accessed via **[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)**.

## API Endpoints

### Vendor Controller

- **`PUT /vendors/{id}/updateAvailability`** - Update the availability status of a vendor.
- **`POST /vendors/`** - Add a new vendor.
- **`GET /vendors/lookup`** - Look up vendors by `serviceType` and `date`.
- **`GET /vendors/available`** - Get all available vendors filtered by `serviceType`.

### Payment Controller

- **`GET /payments`** - Retrieve payments filtered by `status` (PENDING, COMPLETED).
- **`POST /payments`** - Make a new payment.

### Event Controller

- **`GET /events`** - List all events filtered by `status` (UPCOMING, COMPLETED).
- **`POST /events`** - Create a new event.
- **`GET /events/{id}`** - Get details of an event by its ID.

### Client Controller

- **`GET /clients`** - Retrieve all clients.
- **`POST /clients`** - Add a new client.
- **`GET /clients/{id}`** - Get details of a client by their ID.

### Booking Controller

- **`POST /bookings`** - Create a new booking by specifying `eventId` and `vendorId`.
- **`DELETE /bookings/{id}/cancel`** - Cancel a booking by its ID.

### Report Controller

- **`GET /report/monthlySummary`** - Retrieve a summary report including total clients, events, revenue, and top vendors.

### Sample Payloads

### Add a Vendor
```json
{
  "name": "Flower Arrangements Co.",
  "serviceType": "Floral",
  "servicePrice": 1000,
  "contactNumber": 9876543210
}
```

### Create an Event
```json
{
  "eventName": "John and Jane Wedding",
  "eventDate": "2024-12-01",
  "clientId": 1
}
```

### Make a Payment
```json
{
  "clientID": 1,
  "amount": 5000
}
```

### Running Tests

You can use Swagger UI to run API tests:

1. Start the application.
2. Visit **[Swagger UI](http://localhost:8080/swagger-ui/index.html)**.
3. Explore and execute requests for available endpoints.

### Built With

- **Spring Boot** - Framework for building Java applications.
- **H2 Database** - In-memory database for development and testing.
- **Swagger/OpenAPI** - API documentation and testing.

### License

This project is licensed under the MIT License - see the LICENSE file for details.

### Acknowledgements

- Spring Boot for the rapid application development support.
- Swagger for the easy-to-use API testing interface.