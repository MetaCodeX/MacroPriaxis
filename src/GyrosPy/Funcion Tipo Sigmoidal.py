import matplotlib.pyplot as plt
import numpy as np

## Función tipo sigmoidal:
## f(x) = 0 si x <= alpha
## f(x) = 2 * ((x - alpha) / (Y - alpha))^2 si alpha < x <= beta
## f(x) = 1 - 2 * ((x - Y) / (Y - alpha))^2 si beta < x <= Y
## f(x) = 1 si x > Y
## By MetaCodeX
def sigmoidal(x, alpha, beta, Y):
    if x <= alpha:
        return 0, "Región nula"
    elif alpha < x <= beta:
        return 2 * ((x - alpha) / (Y - alpha)) ** 2, "Región creciente rápida"
    elif beta < x <= Y:
        return 1 - 2 * ((x - Y) / (Y - alpha)) ** 2, "Región creciente lenta"
    else:
        return 1, "Región constante"

def main():
    alpha = float(input("valor de alpha: "))
    beta = float(input("valor de beta: "))
    Y = float(input("valor de Y: "))
    x = float(input("valor de x: "))

    grado_pertenencia, region = sigmoidal(x, alpha, beta, Y)
    print(f"\nGrado de pertenencia: {grado_pertenencia}")
    print(f"Región: {region}")

    x_vals = np.linspace(alpha - 1, Y + 1, 500)##aplique el numpy(np) para representar el sigmoide y tenga esa apariencia de curva
    y_vals = [sigmoidal(val, alpha, beta, Y)[0] for val in x_vals]
    
    plt.plot(x_vals, y_vals, label="Función tipo sigmoidal", color="blue")
    plt.axvline(x=x, color="red", linestyle="--", label=f"Grado: {grado_pertenencia:.2f}")
    
    plt.title("Función de Pertenencia Tipo Sigmoidal")
    plt.xlabel("Valor de x")
    plt.ylabel("Grado de pertenencia")
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()