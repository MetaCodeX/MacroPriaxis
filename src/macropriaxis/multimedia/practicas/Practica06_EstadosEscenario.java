package macropriaxis.multimedia.practicas;

import javafx.stage.Stage;

public class Practica06_EstadosEscenario {
    
    public static void ejecutar() {
        Stage escenario = new Stage();
        escenario.setTitle("Práctica 6 - Escenario principal");
        escenario.setWidth(500);
        escenario.setHeight(300);
        
        escenario.setOnHiding((event) -> {
            System.out.println("Ocultando el escenario");
        });
        
        escenario.setOnHidden((event) -> {
            System.out.println("Escenario ocultado");
        });
        
        escenario.setOnShowing((event) -> {
            System.out.println("Mostrando el escenario");
        });
        
        escenario.setOnShown((event) -> {
            System.out.println("Escenario mostrado");
        });
        
        escenario.show();
    }
}