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
                for fila in lector:
                    hora_extra = HoraExtraMapeador.desde_diccionario(fila)
                    datos.append(hora_extra)
        except FileNotFoundError:
            print(f"Error: No se encontró el archivo en {self.ruta_archivo}")
        return datos