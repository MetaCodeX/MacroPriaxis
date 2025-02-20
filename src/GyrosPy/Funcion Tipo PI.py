import matplotlib.pyplot as plt

## Función tipo PI: 
## f(x) = (x - alpha)/(beta - alpha) si alpha <= x < beta
## f(x) = 1 si beta <= x <= phi
## f(x) = (delta - x)/(delta - phi) si phi < x <= delta
## f(x) = 0 en otro caso
## By MetaCodeX
def pi(x, alpha, beta, phi, delta):
    if alpha <= x < beta:
        return (x - alpha) / (beta - alpha), "Región creciente"
    elif beta <= x <= phi:
        return 1, "Región constante"
    elif phi < x <= delta:
        return (delta - x) / (delta - phi), "Región decreciente"
    else:
        return 0, "Región nula"

def main():
    alpha = float(input("valor de alpha: "))
    beta = float(input("valor de beta: "))
    phi = float(input("valor de phi: "))
    delta = float(input("valor de delta: "))
    x = float(input("valor de x: "))

    grado_pertenencia, region = pi(x, alpha, beta, phi, delta)
    print(f"\nGrado de pertenencia: {grado_pertenencia}")
    print(f"Región: {region}")

    # Valores para graficar
    valores_x = [alpha - 1, alpha, beta, phi, delta, delta + 1]
    valores_y = [0, 0, 1, 1, 0, 0]
    
    plt.plot(valores_x, valores_y, label="Función tipo PI", color="blue")
    plt.axvline(x=x, color="red", linestyle="--", label=f"Grado: {grado_pertenencia:.2f}")
    
    plt.title("Función de Pertenencia Tipo PI")
    plt.xlabel("Valor de x")
    plt.ylabel("Grado de pertenencia")
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()