from funcionalidades.horas_extra.Infrastructure.lector_csv import LectorCSV
from funcionalidades.horas_extra.Infrastructure.repositorio_sql import RepositorioSQL
from funcionalidades.horas_extra.Application.servicio_limpieza import ServicioNomina



if __name__ == "__main__":
    # Inyección de dependencias: aquí se decide qué herramientas usar
    lector = LectorCSV('datos/novedades_extra.csv')
    repo = RepositorioSQL()
    
    app = ServicioNomina(lector, repo)
    app.ejecutar()