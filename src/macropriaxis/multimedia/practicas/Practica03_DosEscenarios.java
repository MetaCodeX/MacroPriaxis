package macropriaxis.multimedia.practicas;

import javafx.stage.Stage;

public class Practica03_DosEscenarios {
    
    public static void ejecutar() {
        System.out.println("Inicia la ejecución del programa");
        
        Stage escenarioPrincipal = new Stage();
        Stage escenario2 = new Stage();
        
        escenarioPrincipal.setTitle("Práctica 3 - Escenario principal - Carlos Eduardo Juarez Ricardo - 09/09/2025");
        escenarioPrincipal.show();
        
        escenario2.setTitle("Práctica 3 - Escenario secundario - Carlos Eduardo Juarez Ricardo - 09/09/2025");
        escenario2.show();
        
        // Configurar para que al cerrar cualquiera se cierre el otro
        escenarioPrincipal.setOnCloseRequest(e -> {
            escenario2.close();
            System.out.println("Finaliza la ejecución del programa");
        });
        
        escenario2.setOnCloseRequest(e -> {
            escenarioPrincipal.close();
            System.out.println("Finaliza la ejecución del programa");
        });
    }
}