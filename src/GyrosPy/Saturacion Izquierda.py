import matplotlib.pyplot as plt
## Saturación izquierda: f(x) = 1 si x <= alpha, f(x) = (beta - x)/(beta - alpha) si alpha < x < beta, f(x) = 0 si x >= beta
##By MetaCodeX
def hombro_izquierda(x, alpha, beta):
    if x <= alpha:
        return 1, "Plena pertenencia"
    elif alpha < x < beta:
        return (beta - x) / (beta - alpha), "Transición"
    else:
        return 0, "No pertenece"

def main():
    alpha = float(input("valor de alpha: "))
    beta = float(input("valor de beta: "))
    x = float(input("valor de x: "))

    grado_pertenencia, rango = hombro_izquierda(x, alpha, beta)
    print(f"\nGrado de pertenencia: {grado_pertenencia}")
    print(f"Rango: {rango}")

    valores_x = [alpha - 1, alpha, beta, beta + 1]
    valores_y = [1, 1, 0, 0]
    
    plt.plot(valores_x, valores_y, label="Función de pertenencia", color="blue")
    plt.axvline(x=x, color="red", linestyle="--", label=f"Grado: {grado_pertenencia:.2f}")
    
    plt.title("Función de Pertenencia Tipo Hombro Izquierda")
    plt.xlabel("Valor de x")
    plt.ylabel("Grado de pertenencia")
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()