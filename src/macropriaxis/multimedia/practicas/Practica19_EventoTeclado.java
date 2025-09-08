package macropriaxis.multimedia.practicas;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Practica19_EventoTeclado {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 19 - Evento de Teclado");
        
        HBox hbox = new HBox();
        Text texto = new Text("Original - Presiona cualquier tecla");
        hbox.getChildren().add(texto);
        
        Scene scene = new Scene(hbox, 400, 250);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                texto.setText("Tecla Presionada: " + ke.getCode());
            }
        });

        stage.setScene(scene);
        stage.show();
        
        // Solicitar foco para que detecte las teclas
        scene.getRoot().requestFocus();
    }
}