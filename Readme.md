# BCI API

Esta aplicacion desarrolla una API RESTful de creación de usuarios.

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes programas:

- **Java 17 o superior** (se recomienda usar Java 17, ya que es la versión compatible con Spring Boot 3.x).
- **Maven** o **Gradle** (usaremos Maven en este caso).
- **Docker** (opcional, para ejecutar el proyecto en contenedores).

## Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/ehidalgo49/BCI.git
   cd BCIApi
    
## Compilación y ejecución
Sin ejecutar pruebas

Para compilar el proyecto sin ejecutar las pruebas, usa el siguiente comando:

    mvn clean install -DskipTests
    
Ejecutar la aplicación
Una vez compilado el proyecto, puedes ejecutar la aplicación usando:

    mvn spring-boot:run

Esto levantará el servidor en http://localhost:8080.
Ejecutar con Docker (opcional)
Si deseas ejecutar el proyecto en Docker, puedes usar el siguiente comando:

    docker-compose up --build

Esto construirá y levantará los contenedores de la aplicación y la base de datos en Docker.

Endpoints
1. Registrar un usuario
   ```bash
   Endpoint: /api/users/register
   Método: POST
   Descripción: Registra un nuevo usuario en la plataforma.
   Request:
   json
   Copiar código
   {
   "name": "Juan Pérez",
   "email": "juan.perez@colombia.com",
   "password": "Password123!"
   }
   Response:
   Código 201 (Created):
   json
   Copiar código
   {
   "id": "1a2b3c4d-5678-9101-1121-314151617181",
   "name": "Juan Pérez",
   "email": "juan.perez@colombia.com",
   "is_active": true
   }
   Código 400 (Bad Request):
   json
   Copiar código
   {
   "error": "Email already exists."
   }
   
2. Iniciar sesión de usuario
   ```bash
   Endpoint: /api/users/login
   Método: POST
   Descripción: Inicia sesión con un usuario registrado y obtiene un token JWT.
   Request:
   json
   Copiar código
   {
   "email": "juan.perez@colombia.com",
   "password": "Password123!"
   }
   Response:
   Código 200 (OK):
   json
   Copiar código
   {
   "token": "jwt_token_example"
   }
   Código 401 (Unauthorized):
   json
   Copiar código
   {
   "error": "Invalid credentials."
   }

Pruebas
   
El proyecto incluye pruebas unitarias e integradas. Para ejecutar las pruebas, usa el siguiente comando:

    mvn test
Si deseas omitir las pruebas, usa el siguiente comando:

    mvn clean install -DskipTests

## Estructura del proyecto
La estructura básica del proyecto es la siguiente:

    BCIApi/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   ├── com/
    │   │   │   │   ├── bci/
    │   │   │   │   │   ├── bciapi/
    │   │   │   │   │   │   ├── adapter/
    │   │   │   │   │   │   ├── config/
    │   │   │   │   │   │   ├── controller/
    │   │   │   │   │   │   ├── core/
    │   │   │   │   │   │   └── model/
    │   │   │   ├── resources/
    │   │   │   │   ├── application.properties
    │   │   │   │   ├── data.sql
    ├── Dockerfile
    ├── docker-compose.yml
    ├── pom.xml

## Contribuciones
Si deseas contribuir al proyecto, por favor sigue estos pasos:

Haz un fork del repositorio.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realiza tus cambios y haz commit (git commit -am 'Agrega nueva funcionalidad').
Haz push a tu rama (git push origin feature/nueva-funcionalidad).
Crea un pull request en GitHub.
Licencia
Este proyecto está licenciado bajo la Licencia MIT.
