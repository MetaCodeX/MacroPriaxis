import matplotlib.pyplot as plt
## Saturación derecha: f(x) = 0 si x <= alpha, f(x) = (x - alpha)/(beta - alpha) si alpha < x < beta, f(x) = 1 si x >= beta
##By MetaCodeX
def hombro_derecho(x, alpha, beta):
    if x <= alpha:
        return 0, "No pertenece"
    elif alpha < x < beta:
        return (x - alpha) / (beta - alpha), "Transición"
    else:
        return 1, "Plena pertenencia"

def main():
    alpha = float(input("valor de alpha: "))
    beta = float(input("valor de beta: "))
    x = float(input("valor de x : "))

    grado_pertenencia, rango = hombro_derecho(x, alpha, beta)
    print(f"\nResultado:")
    print(f"Grado de pertenencia: {grado_pertenencia}")
    print(f"Rango: {rango}")
##Graficar
    valores_x = [alpha - 1, alpha, beta, beta + 1]  
    valores_y = [0, 0, 1, 1] 
    
    plt.plot(valores_x, valores_y, label="Función de pertenencia", color="blue")
    plt.axvline(x=x, color="red", linestyle="--", label=f"Grado de pertenencia: {grado_pertenencia:.2f}")
    
    plt.title("Función de Pertenencia Tipo Hombro")
    plt.xlabel("Valor de x")
    plt.ylabel("Grado de pertenencia")
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()