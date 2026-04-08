from abc import ABC, abstractmethod
from typing import List
from .modelos import HoraExtra

class LectorDatosInterface(ABC):
    @abstractmethod
    def obtener_datos(self) -> List[HoraExtra]:
        """Debe devolver una lista de objetos HoraExtra con la data procesada"""
        pass

class RepositorioNominaInterface(ABC):
    @abstractmethod
    def guardar_exitoso(self, hora_extra: HoraExtra):
        """Guarda en la tabla Novedades_HorasExtras"""
        pass

    @abstractmethod
    def guardar_error(self, fila_cruda: str, motivo: str):
        """Guarda en la tabla Log_Errores_Nomina"""
        pass