import csv
import os
from typing import List
from ..Domain.modelos import HoraExtra
from ..Domain.interfaces import LectorDatosInterface
from .mapeadores import HoraExtraMapeador

class LectorCSV(LectorDatosInterface):
    def __init__(self, ruta_archivo: str):
        self.ruta_archivo = ruta_archivo

    def obtener_datos(self) -> List[HoraExtra]:
        datos = []
        try:
            with open(self.ruta_archivo, mode='r', encoding='utf-8-sig') as archivo:
                lector = csv.DictReader(archivo)
                # Empezamos en 2 porque la fila 1 suele ser el encabezado
                for i, fila in enumerate(lector, start=2):
                    hora_extra = HoraExtraMapeador.desde_diccionario(fila)
                    hora_extra.numero_linea = i # Asignamos el número de fila
                    datos.append(hora_extra)
        except FileNotFoundError:
            print(f"Error: No se encontró el archivo")
        return datos