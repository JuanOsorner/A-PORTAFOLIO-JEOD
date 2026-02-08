# Chat Gemini

Este proyecto es una aplicación web de chatbot en tiempo real construida con **Django** que integra la inteligencia artificial de Google **Gemini** (modelo `gemini-pro-latest`) para responder a las consultas de los usuarios.

## 🚀 Características Principales

-   **Integración con Gemini API**: Utiliza el SDK generativo de Google para respuestas inteligentes.
-   **Manejo Robusto de Errores**: Implementa lógica de reintentos (exponential backoff) para manejar automáticamente errores de cuota (HTTP 429).
-   **Arquitectura Singleton**: El servicio de Gemini se inicializa una única vez para optimizar recursos.
-   **Interfaz Simple**: Chat limpio utilizando HTML, CSS y JavaScript (Fetch API).

## 🛠️ Arquitectura

El proyecto está estructurado en una aplicación principal: `gemini_bot`.

### Backend (Django)
-   **Core Service** (`gemini_bot/core/gemini_service.py`): Clase `GeminiService` que encapsula la lógica de negocio. Maneja la autenticación con la API key y los reintentos ante fallos.
-   **Vistas** (`gemini_bot/views.py`):
    -   `chat_view`: Renderiza la interfaz.
    -   `chat_api`: Endpoint JSON que recibe el prompt, consulta al servicio y devuelve la respuesta.

### Frontend
-   **JavaScript**: Comunicación asíncrona con el backend para enviar mensajes sin recargar la página.

## ⚙️ Configuración e Instalación

### Prerrequisitos
-   Python 3.10+
-   Una API Key de Google Gemini ([Conseguir aquí](https://aistudio.google.com/))

### Pasos

1.  **Clonar el repositorio**:
    ```bash
    git clone <URL_DEL_REPO>
    cd chat_gemini
    ```

2.  **Instalar dependencias**:
    ```bash
    pip install -r requirements.txt
    ```
    *Nota: Las dependencias principales son `django`, `google-generativeai`, y `python-dotenv`.*

3.  **Configurar Variables de Entorno**:
    Crea un archivo `.env` en la raíz del proyecto (al nivel de `manage.py` o un nivel superior según configuración) con las siguientes variables:

    ```env
    # Configuración de Django
    SECRET_KEY1=tu_llave_secreta_django
    ALLOWED_HOSTS1=127.0.0.1,localhost
    DEBUG=True

    # Configuración de Gemini
    LLAVEGEMINI=tu_api_key_de_google_gemini

    # Configuración de Base de Datos (Opcional, por defecto SQLite)
    DB_ENGINE1=django.db.backends.sqlite3
    DB_NAME1=db.sqlite3
    ```

4.  **Ejecutar migraciones**:
    ```bash
    python manage.py migrate
    ```

5.  **Iniciar el servidor**:
    ```bash
    python manage.py runserver
    ```

6.  **Usar el Chat**:
    Navega a `http://127.0.0.1:8000/` y comienza a interactuar con el bot.

## 📄 Licencia

Este proyecto es para fines educativos y de práctica.
