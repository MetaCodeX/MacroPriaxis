package macropriaxis.multimedia.practicas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica07_EscenaVacia {
    
    public static void ejecutar() {
        Stage escenario = new Stage();
        
        Label etiqueta = new Label("Hola mundo...");
        VBox contenedor = new VBox();
        contenedor.getChildren().add(etiqueta);
        contenedor.setAlignment(Pos.CENTER);
        
        Scene escena = new Scene(contenedor, 500, 300);
        
        escenario.setTitle("Práctica 7 - Escenario principal");
        escenario.setScene(escena);
        escenario.show();
    }
}