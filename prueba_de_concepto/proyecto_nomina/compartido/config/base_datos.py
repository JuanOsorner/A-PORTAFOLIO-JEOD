import os
import mysql.connector
from mysql.connector import Error

# Configuración centralizada (puede sobrescribirse con variables de entorno)
DB_CONFIG = {
    'host': os.getenv('DB_HOST', 'localhost'),
    'user': os.getenv('DB_USER', 'root'),
    'password': os.getenv('DB_PASSWORD', ''),
    'database': os.getenv('DB_NAME', 'PruebaNomina')
}

class DummyConnection:
    """Fallback connection that discards all operations when MySQL is unavailable."""
    def cursor(self):
        class DummyCursor:
            def execute(self, *args, **kwargs):
                pass
        return DummyCursor()
    def commit(self):
        pass
    def close(self):
        pass
    def is_connected(self):
        return False

class ConexionDB:
    def __init__(self):
        self.config = DB_CONFIG
        self.conexion = None

    def __enter__(self):
        """Intenta conectar a MySQL; si falla, devuelve DummyConnection."""
        try:
            self.conexion = mysql.connector.connect(**self.config)
            if self.conexion.is_connected():
                return self.conexion
        except Error as e:
            print(f"Error al conectar a MySQL: {e}. Usando conexión dummy.")
            self.conexion = DummyConnection()
            return self.conexion
        # Si la conexión no está activa, usar dummy
        self.conexion = DummyConnection()
        return self.conexion

    def __exit__(self, exc_type, exc_val, exc_tb):
        """Cierra la conexión si es real; si es dummy, no hace nada."""
        if self.conexion and hasattr(self.conexion, 'is_connected') and self.conexion.is_connected():
            self.conexion.close()
