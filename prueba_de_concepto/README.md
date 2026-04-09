# Prueba de Concepto (POC) - Sistema de Procesamiento de Novedades y Horas Extras

## Descripción del Proyecto

Esta Prueba de Concepto (POC) automatiza la recepción, limpieza, validación y persistencia de reportes de horas extras. El sistema está diseñado para reemplazar flujos manuales y procesos legacy propensos a errores de digitación, asegurando que solo los datos que cumplen estrictamente con las reglas de negocio sean insertados en la base de datos de nómina.

## Arquitectura del Sistema

El proyecto fue construido bajo principios de Domain-Driven Design (DDD) e implementa una Arquitectura Hexagonal orientada a Vertical Slices (Contextos Delimitados).

- Backend: Python 3.x, FastAPI, SQLAlchemy.
- Frontend: Vanilla JavaScript, HTML5, Tailwind CSS.
- Base de Datos: MySQL / SQL Server.
- Patrones de Diseño: Dependency Injection, Unit of Work, Repository Pattern, Mediator (Event Bus).

## Requisitos Previos

- Python 3.10 o superior.
- Gestor de base de datos relacional (MySQL o SQL Server).
- Navegador web moderno.

## Instalación y Configuración

### 1. Configuración de la Base de Datos

Antes de levantar el servidor, debe existir el esquema de base de datos.
Ejecute el siguiente comando en su gestor de base de datos:

    CREATE DATABASE pruebanomina;

Si va a usar SQL Server es necesario añadir en el requirements.txt la siguiente libreria: pyodbc

### 2. Configuración del Backend

Navegue al directorio del backend y configure el entorno virtual:
cd backend

    python -m venv venv

Active el entorno virtual:

- En Windows: venv\Scripts\activate
- En macOS/Linux: source venv/bin/activate

Instale las dependencias:

    pip install -r requirements.txt

Asegúrese de que el archivo `.env` en el directorio `backend` tenga la cadena de conexión correcta:
DATABASE_URL=mysql+mysqlconnector://root:@localhost/pruebanomina

### 3. Ejecución del Servidor

Con el entorno virtual activado y desde el directorio `backend`, inicie la API:
uvicorn main:app --reload

El servidor confirmará el inicio y SQLAlchemy creará automáticamente las tablas necesarias (Novedades_HorasExtras y Log_Errores_Nomina) si no existen.

## Uso del Sistema (Prueba End-to-End)

1. Con el servidor backend en ejecución, navegue al directorio `frontend`.
2. Abra el archivo `index.html` directamente en su navegador web.
3. Arrastre un archivo CSV con el formato requerido hacia la zona de carga (Dropzone).
4. El sistema procesará el archivo en memoria, aplicará las reglas de negocio y mostrará un modal con el resumen de la operación.
5. Los registros válidos se persistirán en la base de datos, mientras que las anomalías serán registradas en la tabla de auditoría (Log de Errores) con el detalle de la línea y el motivo del fallo.

## Decisiones Arquitectónicas y Escalabilidad

- Aislamiento del Dominio: Las reglas de negocio desconocen el origen de los datos (CSV). Si el requerimiento cambia a una integración vía API, solo se debe construir un nuevo adaptador en la capa de Infraestructura, respetando el principio Abierto/Cerrado (OCP).
- Comunicación Desacoplada: Se implementó un Bus de Eventos. Cuando el procesamiento finaliza, el orquestador publica un evento. Futuros módulos (Auditoría, Notificaciones) pueden suscribirse a este evento sin alterar el código de la funcionalidad principal.
- Manejo de Datos Sensibles: Se incluye una utilidad de enmascaramiento recursivo (`json_masker.py`) diseñada para ofuscar información personal antes de enviar cargas útiles (payloads) a servicios externos o modelos de Inteligencia Artificial.

## Deuda Técnica y Mejoras a Futuro

- Estrategia de Persistencia (Upsert): Actualmente el sistema realiza inserciones planas (Append-Only). Para entornos de producción, se debe implementar una estrategia de actualización o inserción (Upsert) basada en llaves compuestas (DocumentoEmpleado + FechaReporte) para evitar la duplicidad de datos en caso de re-procesamiento de archivos.
- Despliegue en la Nube: La API está estructurada para ser contenerizada mediante Docker y desplegada en servicios administrados como Azure App Service o Azure Container Apps.
