import numpy as np
import skfuzzy as fuzz
import skfuzzy.control as ctrl
import matplotlib.pyplot as plt

# Definir la variable de entrada (temperatura) y la de salida (clasificación)
temperatura = ctrl.Antecedent(np.arange(0, 41, 1), 'temperatura')
clasificacion = ctrl.Consequent(np.arange(0, 101, 1), 'clasificacion')

# Definir funciones de membresía para temperatura
temperatura['fria'] = fuzz.trimf(temperatura.universe, [0, 0, 15])
temperatura['templada'] = fuzz.trimf(temperatura.universe, [10, 20, 30])
temperatura['caliente'] = fuzz.trimf(temperatura.universe, [25, 40, 40])

# Definir funciones de membresía para la clasificación (0=Fría, 50=Templada, 100=Caliente)
clasificacion['fria'] = fuzz.trimf(clasificacion.universe, [0, 0, 50])
clasificacion['templada'] = fuzz.trimf(clasificacion.universe, [25, 50, 75])
clasificacion['caliente'] = fuzz.trimf(clasificacion.universe, [50, 100, 100])

# Definir reglas difusas
regla1 = ctrl.Rule(temperatura['fria'], clasificacion['fria'])
regla2 = ctrl.Rule(temperatura['templada'], clasificacion['templada'])
regla3 = ctrl.Rule(temperatura['caliente'], clasificacion['caliente'])

# Crear el sistema de control difuso
sistema_ctrl = ctrl.ControlSystem([regla1, regla2, regla3])
sistema = ctrl.ControlSystemSimulation(sistema_ctrl)

# Entrada de temperatura
temp_input = 18
sistema.input['temperatura'] = temp_input

# Calcular el resultado
sistema.compute()
resultado = sistema.output['clasificacion']

# Mostrar resultado en consola
print(f"Para una temperatura de {temp_input}°C, la clasificación es {resultado:.2f}/100")

# --------- Gráficas ---------
# Gráfica 1: Funciones de membresía de temperatura
plt.figure(figsize=(8, 4))
plt.title("Funciones de membresía - Temperatura")
for etiqueta in temperatura.terms:
    plt.plot(temperatura.universe, temperatura[etiqueta].mf, label=etiqueta)
plt.axvline(temp_input, color='r', linestyle='--', label=f"Entrada: {temp_input}°C")
plt.xlabel("Temperatura (°C)")
plt.ylabel("Grado de membresía")
plt.legend()
plt.grid()

# Gráfica 2: Funciones de membresía de clasificación
plt.figure(figsize=(8, 4))
plt.title("Funciones de membresía - Clasificación")
for etiqueta in clasificacion.terms:
    plt.plot(clasificacion.universe, clasificacion[etiqueta].mf, label=etiqueta)
plt.axvline(resultado, color='r', linestyle='--', label=f"Salida: {resultado:.2f}/100")
plt.xlabel("Clasificación")
plt.ylabel("Grado de membresía")
plt.legend()
plt.grid()

# Mostrar gráficas
plt.show()
