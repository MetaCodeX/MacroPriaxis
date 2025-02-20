import numpy as np
##creado con IA para clasificar la temperatura en fria, templada y caliente Sin impórtaciones 
def funcion_membresia_fria(x, rango):
   
    if x <= rango[0] or x >= rango[1]:
        return 0
    elif x < (rango[0] + rango[1]) / 2:
        return (x - rango[0]) / (rango[1] - rango[0])
    else:
        return (rango[1] - x) / (rango[1] - rango[0])


def funcion_membresia_templada(x, rango):
    
    if x <= rango[0] or x >= rango[2]:
        return 0
    elif x < (rango[0] + rango[1]) / 2:
        return (x - rango[0]) / (rango[1] - rango[0])
    else:
        return (rango[2] - x) / (rango[2] - rango[1])


def funcion_membresia_caliente(x, rango):
    
    if x <= rango[0] or x >= rango[2]:
        return 0
    elif x < (rango[0] + rango[1]) / 2:
        return (x - rango[0]) / (rango[1] - rango[0])
    else:
        return (rango[2] - x) / (rango[2] - rango[1])



def inferencia_difusa(temp):
    
    rango_fria = [0, 15]
    rango_templada = [10, 20, 30]
    rango_caliente = [25, 40]
 
    grados_fria = funcion_membresia_fria(temp, rango_fria)
    grados_templada = funcion_membresia_templada(temp, rango_templada)
    grados_caliente = funcion_membresia_caliente(temp, rango_caliente)

    resultado = (grados_fria * 0) + (grados_templada * 50) + (grados_caliente * 100)

    resultado_final = resultado / (grados_fria + grados_templada + grados_caliente)
    return resultado_final

temp_input = 18

resultado = inferencia_difusa(temp_input)


print(f"Para una temperatura de {temp_input}°C, la clasificación es {resultado:.2f}/100")
