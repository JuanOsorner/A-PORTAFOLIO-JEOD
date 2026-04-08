from decimal import Decimal
from datetime import datetime, date
from ..Domain.modelos import HoraExtra

class HoraExtraMapeador:
    @staticmethod
    def desde_diccionario(datos: dict) -> HoraExtra:
        # Aquí se maneja la 'suciedad' de tipos (ej: 'cinco' en vez de 5)
        try:
            cantidad_str = str(datos.get('cantidad_horas', '0')).strip()
            # Si falla la conversión a Decimal, lanzará InvalidOperation
            cantidad = Decimal(cantidad_str) 
            
            fecha_str = datos.get('fecha_reporte', '')
            fecha_dt = datetime.strptime(fecha_str, '%Y-%m-%d').date()
            
            return HoraExtra(
                documento=str(datos.get('documento', '')).strip(),
                tipo_hora=str(datos.get('tipo_he', '')).strip(),
                cantidad=cantidad,
                fecha_reporte=fecha_dt,
                fila_original=str(datos) # Guardamos la 'foto' cruda de la fila
            )
        except Exception as e:
            # Si el mapeo falla, devolvemos un objeto mínimo para que el dominio lo rechace
            return HoraExtra(
                documento=str(datos.get('documento', '')),
                tipo_hora=str(datos.get('tipo_he', '')),
                cantidad=Decimal('-1'), # Provocamos fallo de validación
                fecha_reporte=date.today(),
                fila_original=str(datos)
            )