package macropriaxis.multimedia.practicas;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica08_CambiandoCursor {
    
    public static void ejecutar() {
        Stage escenario = new Stage();
        
        Label etiqueta = new Label("Hola mundo... (Cursor OPEN_HAND)");
        VBox contenedor = new VBox();
        contenedor.getChildren().add(etiqueta);
        contenedor.setAlignment(Pos.CENTER);
        
        Scene escena = new Scene(contenedor, 500, 300);
        escena.setCursor(Cursor.OPEN_HAND);
        
        escenario.setTitle("Práctica 8 - Escenario principal");
        escenario.setScene(escena);
        escenario.show();
    }
}