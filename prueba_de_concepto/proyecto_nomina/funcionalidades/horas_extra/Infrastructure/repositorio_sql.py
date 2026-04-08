from compartido.config.base_datos import ConexionDB
from ..Domain.interfaces import RepositorioNominaInterface
from ..Domain.modelos import HoraExtra

class RepositorioSQL(RepositorioNominaInterface):
    def guardar_exitoso(self, hora_extra: HoraExtra):
        sql = """
            INSERT INTO Novedades_HorasExtras 
            (DocumentoEmpleado, TipoHoraExtra, CantidadHoras, FechaReporte) 
            VALUES (%s, %s, %s, %s)
        """
        valores = (
            hora_extra.documento,
            hora_extra.tipo_hora,
            hora_extra.cantidad,
            hora_extra.fecha_reporte
        )
        with ConexionDB() as conexion:
            cursor = conexion.cursor()
            cursor.execute(sql, valores)
            conexion.commit()

    def guardar_error(self, hora_extra: HoraExtra, motivo: str):
        # Concatenamos el número de línea al motivo para que sea visible en phpMyAdmin
        motivo_con_fila = f"Fila {hora_extra.numero_linea}: {motivo}"
        
        sql = """
            INSERT INTO Log_Errores_Nomina 
            (FilaOriginal, MotivoFallo) 
            VALUES (%s, %s)
        """
        with ConexionDB() as conexion:
            cursor = conexion.cursor()
            cursor.execute(sql, (hora_extra.fila_original, motivo_con_fila))
            conexion.commit()