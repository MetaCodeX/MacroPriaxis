import matplotlib.pyplot as plt

## Función tipo triangular:
## f(x) = (x - alpha)/(beta - alpha) si alpha <= x < beta
## f(x) = (delta - x)/(delta - beta) si beta <= x <= delta
## f(x) = 0 en otro caso
## By MetaCodeX
def triangular(x, alpha, beta, delta):
    if alpha <= x < beta:
        return (x - alpha) / (beta - alpha), "Región creciente"
    elif beta <= x <= delta:
        return (delta - x) / (delta - beta), "Región decreciente"
    else:
        return 0, "Región nula"

def main():
    alpha = float(input("valor de alpha: "))
    beta = float(input("valor de beta: "))
    delta = float(input("valor de delta: "))
    x = float(input("valor de x: "))

    grado_pertenencia, region = triangular(x, alpha, beta, delta)
    print(f"\nGrado de pertenencia: {grado_pertenencia}")
    print(f"Región: {region}")

    # Valores para graficar
    valores_x = [alpha - 1, alpha, beta, delta, delta + 1]
    valores_y = [0, 0, 1, 0, 0]
    
    plt.plot(valores_x, valores_y, label="Función tipo triangular", color="blue")
    plt.axvline(x=x, color="red", linestyle="--", label=f"Grado: {grado_pertenencia:.2f}")
    
    plt.title("Función de Pertenencia Tipo Triangular")
    plt.xlabel("Valor de x")
    plt.ylabel("Grado de pertenencia")
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()