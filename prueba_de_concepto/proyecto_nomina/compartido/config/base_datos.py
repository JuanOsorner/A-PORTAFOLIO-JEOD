import os
import mysql.connector
from mysql.connector import Error

# 1. Configuración Centralizada
DB_CONFIG = {
    'host': 'localhost',
    'user': 'root',
    'password': '',
    'database': 'pruebanomina'
}

class ConexionDB:
    def __init__(self):
        self.config = DB_CONFIG
        self.conexion = None

    def __enter__(self):
        try:
            # Intentamos conectar directamente
            self.conexion = mysql.connector.connect(**self.config)
            return self.conexion
        except Error as e:
            # Si el error es 1049 (DB no existe), la creamos
            if e.errno == 1049:
                print(f"⚠️  La base de datos '{self.config['database']}' no existe. Creándola ahora...")
                self._crear_base_y_tablas()
                # Reintentamos la conexión ya con la DB creada
                self.conexion = mysql.connector.connect(**self.config)
                return self.conexion
            else:
                # Si es otro error (ej: MySQL apagado), usamos el Dummy para el video
                print(f"❌ Error de conexión: {e}. Usando modo simulación.")
                self.conexion = DummyConnection()
                return self.conexion

    def _crear_base_y_tablas(self):
        """Conecta al servidor MySQL para inicializar todo."""
        try:
            # Conectamos solo al host/user/pass (sin database)
            conn = mysql.connector.connect(
                host=self.config['host'],
                user=self.config['user'],
                password=self.config['password']
            )
            cursor = conn.cursor()
            
            # Crear DB
            cursor.execute(f"CREATE DATABASE IF NOT EXISTS {self.config['database']}")
            cursor.execute(f"USE {self.config['database']}")
            
            # Crear Tabla de Éxitos
            cursor.execute("""
                CREATE TABLE IF NOT EXISTS Novedades_HorasExtras (
                    IdNovedad INT AUTO_INCREMENT PRIMARY KEY,
                    DocumentoEmpleado VARCHAR(20),
                    TipoHoraExtra VARCHAR(50),
                    CantidadHoras DECIMAL(10,2),
                    FechaReporte DATE,
                    FechaProcesamiento DATETIME DEFAULT CURRENT_TIMESTAMP
                )
            """)
            
            # Crear Tabla de Errores
            cursor.execute("""
                CREATE TABLE IF NOT EXISTS Log_Errores_Nomina (
                    IdError INT AUTO_INCREMENT PRIMARY KEY,
                    FilaOriginal TEXT,
                    MotivoFallo VARCHAR(255),
                    FechaFallo DATETIME DEFAULT CURRENT_TIMESTAMP
                )
            """)
            
            conn.commit()
            cursor.close()
            conn.close()
            print("✅ Estructura de base de datos creada exitosamente.")
        except Error as e:
            print(f"🔥 Error fatal al intentar crear la DB: {e}")

    def __exit__(self, exc_type, exc_val, exc_tb):
        if self.conexion and hasattr(self.conexion, 'is_connected') and self.conexion.is_connected():
            self.conexion.close()

# Clases de soporte para que el programa no se caiga si falla el motor de DB
class DummyConnection:
    def cursor(self): return DummyCursor()
    def commit(self): pass
    def close(self): pass
    def is_connected(self): return False

class DummyCursor:
    def execute(self, sql, params=None): pass
    def close(self): pass