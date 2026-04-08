from ..Domain.interfaces import LectorDatosInterface, RepositorioNominaInterface

class ServicioNomina:
    def __init__(self, lector: LectorDatosInterface, repositorio: RepositorioNominaInterface):
        self.lector = lector
        self.repositorio = repositorio

    def ejecutar(self):
        novedades = self.lector.obtener_datos()
        for item in novedades:
            try:
                item.validar()
                self.repositorio.guardar_exitoso(item)
                print(f"✓ OK: Fila {item.numero_linea}")
            except Exception as e:
                # Ahora pasamos el objeto 'item' completo
                self.repositorio.guardar_error(item, str(e))
                print(f"✗ Error en fila {item.numero_linea}: {e}")