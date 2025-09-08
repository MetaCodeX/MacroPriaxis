package macropriaxis.multimedia.practicas;

import javafx.stage.StageStyle;
import javafx.stage.Stage;

public class Practica05_EscenarioSimple {
    
    public static void ejecutar() {
        Stage escenario = new Stage();
        escenario.setTitle("Práctica 5 - Escenario principal");
        
        // Cambiar entre diferentes estilos descomentando una línea
        //escenario.initStyle(StageStyle.DECORATED);     // Estilo por defecto
        //escenario.initStyle(StageStyle.UNDECORATED);   // Sin decoraciones
        //escenario.initStyle(StageStyle.TRANSPARENT);   // Transparente
        escenario.initStyle(StageStyle.UNIFIED);         // Unificado
        
        escenario.show();
    }
}