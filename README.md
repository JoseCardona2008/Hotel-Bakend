# Hotel Backend - Check-In / Check-Out

Proyecto Spring Boot 3 con Java 25 para gestionar clientes, habitaciones, check-in, check-out y facturación en un hotel académico.

## Requisitos

- Java 25+
- Maven
- MySQL 8+

## Base de datos

Crea la base de datos localmente:

```sql
CREATE DATABASE hotel_db;
```

Asegúrate de que las credenciales del archivo `src/main/resources/application.properties` correspondan a tu instancia local de MySQL.

## Ejecutar la aplicación

```bash
mvn spring-boot:run
```

## Swagger/OpenAPI

Una vez levantado el proyecto:

- http://localhost:8080/swagger-ui.html
- http://localhost:8080/v3/api-docs

## Endpoints principales

- `POST /api/clientes`
- `GET /api/clientes`
- `POST /api/hospedajes/check-in`
- `POST /api/hospedajes/check-out`
- `GET /api/hospedajes/activos`
- `GET /api/habitaciones`
- `GET /api/habitaciones/disponibles/{tipoHabitacion}`

## Horarios oficiales

- Check-In: 16:00
- Check-Out: 13:00
