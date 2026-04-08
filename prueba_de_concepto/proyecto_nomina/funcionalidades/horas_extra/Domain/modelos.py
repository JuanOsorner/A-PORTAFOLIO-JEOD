from dataclasses import dataclass
from decimal import Decimal, InvalidOperation
from datetime import date

@dataclass
class HoraExtra:
    documento: str
    tipo_hora: str
    cantidad: Decimal
    fecha_reporte: date
    fila_original: str = ""
    numero_linea: int = 0

    def __repr__(self):
        return self.fila_original if self.fila_original else f"Doc: {self.documento}, Tipo: {self.tipo_hora}"

    def validar(self):
        """
        Aplica las reglas de negocio estrictas de la prueba técnica.
        Levanta un ValueError si los datos son 'sucios'.
        """
        # 1. Regla: Documento no puede estar vacío
        if not self.documento or not str(self.documento).strip():
            raise ValueError("El documento del empleado es obligatorio.")

        # 2. Regla: Tipos de recargo permitidos
        tipos_validos = ['HE_DIURNA', 'HE_NOCTURNA', 'HE_DOMINICAL', 'HE_FESTIVA']
        if self.tipo_hora not in tipos_validos:
            raise ValueError(f"Tipo de hora '{self.tipo_hora}' no es válido.")

        # 3. Regla: Cantidad debe ser un número mayor a 0
        if self.cantidad <= 0:
            raise ValueError(f"La cantidad de horas debe ser mayor a 0. Recibido: {self.cantidad}")