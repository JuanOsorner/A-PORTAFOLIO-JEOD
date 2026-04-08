from ..Domain.interfaces import LectorDatosInterface, RepositorioNominaInterface

class ServicioNomina:
    def __init__(self, lector: LectorDatosInterface, repositorio: RepositorioNominaInterface):
        self.lector = lector
        self.repositorio = repositorio

    def ejecutar(self):
        # Recibe objetos de dominio listos para ser validados
        novedades = self.lector.obtener_datos()
        
        for item in novedades:
            try:
                item.validar() # El dominio decide si es correcto
                self.repositorio.guardar_exitoso(item)
                print(f"OK: {item.documento}")
            except Exception as e:
                # Si falla el mapeo previo o la validación, va al log
                self.repositorio.guardar_error(str(item), str(e))
                print(f"Error: {e}")