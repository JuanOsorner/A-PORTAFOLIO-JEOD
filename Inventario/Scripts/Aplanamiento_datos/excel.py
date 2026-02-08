import pandas as pd
import os
import re

def limpiar_codigo(codigo):
    """
    Elimina el sufijo 'R' solo si está precedido por un número.
    Ej: '30007000048R5' -> '30007000048'
    """
    codigo_str = str(codigo).strip()
    if 'R' in codigo_str:
        posicion_r = codigo_str.rfind('R')
        if posicion_r > 0 and codigo_str[posicion_r - 1].isdigit():
            return codigo_str[:posicion_r]
    return codigo_str

def obtener_todos_los_archivos(ruta_raiz):
    """
    Recorre la carpeta raíz y TODAS sus subcarpetas.
    Retorna una lista con los nombres de todos los archivos encontrados.
    """
    lista_archivos = []
    print(f"Indexando archivos en: {ruta_raiz} (esto puede tardar unos segundos)...")
    
    # os.walk genera los nombres de archivo en un árbol de directorios
    for raiz, carpetas, archivos in os.walk(ruta_raiz):
        for archivo in archivos:
            # Guardamos el nombre del archivo. 
            # Si necesitas la ruta completa, usa: os.path.join(raiz, archivo)
            lista_archivos.append(archivo)
            
    print(f"Total de archivos indexados en subcarpetas: {len(lista_archivos)}")
    return lista_archivos

def procesar_inventario(ruta_excel, ruta_carpeta_busqueda, nombre_responsable):
    print(f"--- Iniciando proceso para: {nombre_responsable} ---")
    
    # 1. Cargar Excel
    try:
        if ruta_excel.endswith('.csv'):
            df = pd.read_csv(ruta_excel)
        else:
            df = pd.read_excel(ruta_excel)
    except Exception as e:
        return f"Error al leer el archivo: {e}"

    # 2. Filtrar Responsable
    if 'RESPONSABLE' in df.columns:
        # Convertimos a string para evitar errores si hay celdas vacías o numéricas
        df_filtrado = df[df['RESPONSABLE'].astype(str).str.upper() == nombre_responsable.upper()].copy()
    else:
        return "Error: Columna 'RESPONSABLE' no encontrada."

    if len(df_filtrado) == 0:
        return "No se encontraron registros para ese responsable."

    # 3. Obtener TODOS los archivos (incluyendo subcarpetas)
    todos_los_archivos = obtener_todos_los_archivos(ruta_carpeta_busqueda)
    
    # 4. Lógica de comparación
    resultados_observacion = []
    
    print("Comparando códigos con archivos...")
    
    for index, row in df_filtrado.iterrows():
        item_code = row['ITEMCODE']
        codigo_limpio = limpiar_codigo(item_code)
        
        encontrado = False
        
        # Búsqueda: ¿Algún archivo empieza con este código?
        # Usamos startswith para que '40008000102' haga match con '40008000102 COBERTURA....lbl'
        for archivo in todos_los_archivos:
            if archivo.startswith(codigo_limpio):
                encontrado = True
                break # Ya lo encontramos, no hace falta seguir buscando para este ítem
        
        estado = "pendiente" if encontrado else "No existe"
        resultados_observacion.append(estado)

    # 5. Guardar
    df_filtrado['OBSERVACIONES'] = resultados_observacion
    nombre_salida = f"Resultado_{nombre_responsable}.xlsx"
    
    # Requiere openpyxl instalado
    df_filtrado.to_excel(nombre_salida, index=False)
    
    return f"¡Listo! Archivo guardado: {nombre_salida}"

# --- CONFIGURACIÓN DE USUARIO ---
MI_ARCHIVO = 'CODIGOS DE BARRAS DORAL(Hoja1).csv' 

# Coloca aquí la ruta de la carpeta raíz donde quieres empezar a buscar
MI_CARPETA = r'C:\Users\juan.osorno\JOLI FOODS S.A.S\JOHN ALEXANDER CARDONA HIGUITA - ETIQUETAS ZEBRA PRODUCCIÓN UP2' 

MI_RESPONSABLE = 'JUAN ESTEBAN' # O el nombre que corresponda

# Ejecutar
if __name__ == "__main__":
    print(procesar_inventario(MI_ARCHIVO, MI_CARPETA, MI_RESPONSABLE))