def extraer_llaves_relevantes(datos: dict | list, llaves_buscadas: set, resultado: dict = None) -> dict:
    """Busca en profundidad las llaves solicitadas y las aplana en un solo diccionario"""
    if resultado is None:
        resultado = {}
        
    if isinstance(datos, dict):
        for llave, valor in datos.items():
            # Si encontramos la llave, la guardamos
            if llave in llaves_buscadas:
                resultado[llave] = valor
            # Si el valor es anidado, seguimos buscando
            if isinstance(valor, (dict, list)):
                extraer_llaves_relevantes(valor, llaves_buscadas, resultado)
                
    elif isinstance(datos, list):
        for item in datos:
            extraer_llaves_relevantes(item, llaves_buscadas, resultado)
            
    return resultado