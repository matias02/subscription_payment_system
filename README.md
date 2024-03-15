# Sistema de Gestión de Pagos de Suscripciones

## Descripción General

Este sistema ofrece una solución completa para proveedores de servicios que desean implementar un modelo de negocio basado en suscripciones. Facilita la gestión de suscripciones y pagos, proporcionando una visión clara de ingresos y cartera de clientes.

## Arquitectura del Sistema

El sistema se basa en una arquitectura de microservicios, con cada uno enfocado en una responsabilidad única:

- **API Gateway**: Punto de entrada para las llamadas de API, encaminando solicitudes a los servicios correspondientes y gestionando la autenticación.
- **Servicio de Autenticación**: Gestiona el registro y autenticación de usuarios, emitiendo tokens JWT.
- **Microservicio de Usuarios**: Responsable de la creación, actualización y eliminación de cuentas de usuario. (Para un ADMIN_ROLE)
- **Microservicio de Pagos**: Procesa y gestiona los pagos y suscripciones. (Aún por implementar)
- **Servicio de Descubrimiento (Eureka Server)**: Mantiene registro y facilita la comunicación entre servicios.

## Configuración y Puesta en Marcha

### Requisitos Previos

Para ejecutar el sistema, se requieren las siguientes herramientas y dependencias:

- Java JDK 17
- Maven
- PostgreSQL
- Las dependencias de Spring Boot como Data JPA, Security, Web, etc.

### Instalación y Ejecución

Para poner en marcha los microservicios, siga los siguientes pasos:

1. Clone el repositorio de código fuente.
2. Instale PostgreSQL y cree las bases de datos con los esquemas proporcionados.
3. Configure las cadenas de conexión y claves JWT en `application.properties`.
4. Navegue al directorio de cada microservicio y ejecute `mvn spring-boot:run`.

### Puertos de Microservicios

Los microservicios se ejecutan en los siguientes puertos:

- API Gateway: `8080`
- Servicio de Autenticación: `8081`
- Microservicio de Usuarios: `8083`
- Microservicio de Pagos: `8084`
- Eureka Server: `8761`

## Endpoints

Los endpoints clave para cada microservicio se describen a continuación:

### API Gateway

- `/autenticacion/**` - Rutas de autenticación
- `/usuarios/**` - Rutas de usuario
- `/pagos/**` - Rutas de pagos (aún no implementado)

### Servicio de Autenticación

- `POST /api/auth/login` - Autenticación de usuario
- `POST /api/auth/register` - Registro de usuario

### Microservicio de Usuarios

- `GET /api/users/` - Lista de usuarios
- `POST /api/users/` - Crear usuario

### Microservicio de Pagos

(Aún por implementar)
- `GET /api/payments/` - Obtener pagos
- `POST /api/payments/` - Crear pago

## Ejemplos de Uso

### Registrar un Usuario mediante Postman

Para registrar un nuevo usuario a través de Postman, sigue estos pasos:

1. Abre Postman y crea una nueva solicitud POST.
2. Ingresa la URL del API Gateway seguida por el endpoint de registro: `http://localhost:8080/api/auth/register`.
3. En la pestaña "Headers", configura el siguiente header:
   - Key: `Content-Type`
   - Value: `application/json`.
4. Selecciona la pestaña "Body", elige "raw" y asegúrate de que el formato es "JSON (application/json)".
5. Ingresa los detalles del usuario en el cuerpo de la solicitud en formato JSON:

```json
{
   "username": "newuser",
   "password": "newpassword",
   "roles": "user"
}
```


### Obtener un Token JWT

Para obtener un token JWT para un usuario registrado, sigue estos pasos:

1. Abre Postman y crea una nueva solicitud POST dirigida al endpoint de login: `http://localhost:8080/api/auth/login`.
2. En la pestaña "Headers", asegúrate de que el header esté establecido a `Content-Type: application/json`.
3. Selecciona la pestaña "Body", elige "raw" y selecciona "JSON (application/json)".
4. Escribe las credenciales del usuario en el cuerpo de la solicitud:

```json
{
   "username": "newuser",
   "password": "newpassword"
}
```


