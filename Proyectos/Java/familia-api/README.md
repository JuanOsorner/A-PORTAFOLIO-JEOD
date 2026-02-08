# Familia API

API REST construida con **Spring Boot** para la gestión integral de un módulo familiar, diseñada para administrar la información de estudiantes, familiares, vínculos y notificaciones en un entorno educativo u organizacional.

## 🚀 Tecnologías

Este proyecto utiliza las siguientes tecnologías y herramientas:

-   **Java 17**: Lenguaje de programación.
-   **Spring Boot 3.5.6**: Framework principal.
    -   *Spring Data JPA*: Para la persistencia de datos.
    -   *Spring Web*: Para la creación de controladores REST.
    -   *Spring Validation*: Para validación de entradas.
    -   *Spring Security Crypto*: Para encriptación de contraseñas.
-   **Base de Datos**: Soporte para MySQL y H2 (memoria).
-   **MapStruct**: Para el mapeo eficiente entre Entidades y DTOs.
-   **Maven**: Gestión de dependencias y construcción.

## 🏛️ Arquitectura

El proyecto sigue una arquitectura en capas estándar de Spring Boot:

### Estructura de Paquetes (`com.example.familia_api`)

-   **`controladores`**: Exponen los endpoints REST.
    -   `UsuarioControlador`: Gestión de usuarios y autenticación.
    -   `EstudianteControlador`: CRUD de estudiantes.
    -   `FamiliarControlador`: CRUD de familiares.
    -   `VinculoControlador`: Gestión de relaciones parentales/familiares.
    -   `NotificacionControlador`: Sistema de avisos.
    -   `ConsultaControlador`: endpoints para consultas específicas.
-   **`modelos`**: Entidades JPA que representan el dominio.
    -   `Usuario`: Entidad base con autenticación y Roles (`Rol` enum).
    -   `Estudiante`: Información académica y personal.
    -   `Familiar`: Datos de acudientes o parientes.
    -   `Vinculo`: Relación entre estudiantes y familiares.
    -   `Notificacion`: Mensajes del sistema.
-   **`servicios`**: Lógica de negocio.
-   **`repositorios`**: Interfaces que extienden `JpaRepository`.

## ⚙️ Instalación y Ejecución

### Prerrequisitos
-   Java JDK 17 o superior.
-   Maven (o usar el wrapper `mvnw` incluido).

### Pasos

1.  **Clonar el repositorio**:
    ```bash
    git clone <URL_DEL_REPO>
    cd familia-api
    ```

2.  **Configurar Base de Datos**:
    El archivo `src/main/resources/application.properties` contiene la configuración. Por defecto puede estar configurado para H2 o MySQL. Asegúrate de ajustar las credenciales si usas MySQL.

3.  **Ejecutar la aplicación**:
    -   **Linux/Mac**:
        ```bash
        ./mvnw spring-boot:run
        ```
    -   **Windows**:
        ```bash
        mvnw.cmd spring-boot:run
        ```

4.  **Acceder a la API**:
    La aplicación se iniciará generalmente en el puerto 8080.
    URL Base: `http://localhost:8080/`

## 📄 Licencia

Este proyecto es para fines educativos y de práctica.
