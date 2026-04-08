from sqlalchemy import Column, Integer, String, DECIMAL, Date, DateTime, func
from compartido.config.base_datos import Base

class NovedadHoraExtraDB(Base):
    """Modelo privado del Slice AHE, mapea exacto con el script SQL"""
    __tablename__ = "Novedades_HorasExtras"

    id_novedad = Column("IdNovedad", Integer, primary_key=True, autoincrement=True)
    documento = Column("DocumentoEmpleado", String(20), nullable=False)
    tipo_hora = Column("TipoHoraExtra", String(50), nullable=False)
    cantidad = Column("CantidadHoras", DECIMAL(10, 2), nullable=False)
    fecha_reporte = Column("FechaReporte", Date, nullable=False)
    fecha_procesamiento = Column("FechaProcesamiento", DateTime, server_default=func.now())
    estado_validacion = Column("EstadoValidacion", String(20), default='EXITOSO')