import numpy as np


# Definición de las funciones de membresía de temperatura y clasificación
def funcion_membresia_fria(x, rango):
    # Función de membresía triangular para "fría"
    if x <= rango[0] or x >= rango[1]:
        return 0
    elif x < (rango[0] + rango[1]) / 2:
        return (x - rango[0]) / (rango[1] - rango[0])
    else:
        return (rango[1] - x) / (rango[1] - rango[0])


def funcion_membresia_templada(x, rango):
    # Función de membresía triangular para "templada"
    if x <= rango[0] or x >= rango[2]:
        return 0
    elif x < (rango[0] + rango[1]) / 2:
        return (x - rango[0]) / (rango[1] - rango[0])
    else:
        return (rango[2] - x) / (rango[2] - rango[1])


def funcion_membresia_caliente(x, rango):
    # Función de membresía triangular para "caliente"
    if x <= rango[0] or x >= rango[2]:
        return 0
    elif x < (rango[0] + rango[1]) / 2:
        return (x - rango[0]) / (rango[1] - rango[0])
    else:
        return (rango[2] - x) / (rango[2] - rango[1])


# Función de inferencia difusa
def inferencia_difusa(temp):
    # Definir los rangos de las funciones de membresía
    rango_fria = [0, 15]
    rango_templada = [10, 20, 30]
    rango_caliente = [25, 40]

    # Calcular el grado de membresía para cada etiqueta de temperatura
    grados_fria = funcion_membresia_fria(temp, rango_fria)
    grados_templada = funcion_membresia_templada(temp, rango_templada)
    grados_caliente = funcion_membresia_caliente(temp, rango_caliente)

    # Establecer las reglas para inferir la clasificación
    # Regla 1: Si la temperatura es fría, la clasificación es fría
    # Regla 2: Si la temperatura es templada, la clasificación es templada
    # Regla 3: Si la temperatura es caliente, la clasificación es caliente
    resultado = (grados_fria * 0) + (grados_templada * 50) + (grados_caliente * 100)

    # Normalizamos el resultado
    resultado_final = resultado / (grados_fria + grados_templada + grados_caliente)
    return resultado_final


# Entrada de temperatura
temp_input = 18

# Calcular el resultado de la clasificación
resultado = inferencia_difusa(temp_input)

# Mostrar el resultado
print(f"Para una temperatura de {temp_input}°C, la clasificación es {resultado:.2f}/100")
