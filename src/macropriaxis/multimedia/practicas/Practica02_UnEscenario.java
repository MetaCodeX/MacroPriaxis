package macropriaxis.multimedia.practicas;

import javafx.stage.Stage;

public class Practica02_UnEscenario {
    
    public static void ejecutar() {
        System.out.println("Inicia la ejecución del programa");
        
        Stage escenario = new Stage();
        escenario.setTitle("Práctica 2 - Código base JavaFX");
        escenario.show();
        
        escenario.setOnCloseRequest(e -> {
            System.out.println("Finaliza la ejecución del programa");
        });
    }
}