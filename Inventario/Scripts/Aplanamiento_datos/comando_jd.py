"""
Descripcion: Este es un Script que elimina de un json todo lo que esta en su valor lo remplaza por None

Responsabilidad: Se le pasa un json a la funcion eliminar_valores y esta retorna un json con todos los valores remplazados por None

Nota: Hago uso de esto como filtro para pasar la estructura de un json a la IA cuando sea necesario, asi no comparto la informacion
sensible del mismo json

Observacion: Nunca compartir el json dentro de este codigo. Debe colocarse en un .json en algun lugar
"""
import os
import json # para parsear el json a diccionario
def eliminar_valores(json: dict) -> dict:
    """
    Recorremos el json haciendo backtracking para eliminar 
    los valores que esten en su valor lo remplaza por None
    
    args: json (dict)

    return: json (dict)
    """
    # Recorremos por key y value los items del json 
    for llave, valor in json.items():
        # Si el valor es un diccionario volvemos a llamar la funcion
        if isinstance(valor, dict):
            eliminar_valores(valor)
        # Si el valor es una lista recorremos la lista
        elif isinstance(valor, list):
            # Por cada elemento de la lista validamos si es un diccionario, 
            # si no es un diccionario remplazamos el valor por None
            for item in valor:
                if isinstance(item, dict):
                    eliminar_valores(item)
                else:
                    item = None # remplazamos el valor por None
        else:
            json[llave] = None # remplazamos el valor por None
    return json
# Manejamos el error con el bloque try except
try:
    ruta = os.path.join("datos.json") # Encontramos la ruta del archivo
    with open(ruta, "r") as f:
        # Parseamos el json a diccionario
        json_data = json.load(f)
        # Llamamos a la funcion eliminar_valores
        json_resultado = eliminar_valores(json_data)
        # Imprimimos el json resultado
        print(json_resultado)
except Exception as e:
    print(f"\nMENSAJE DE ERRRO: {e}")

# EJERCICIO PARA MI: CALCULAR LA COMPLEJIDAD COMPUTACIONAL DE ESTE ALGORITMO