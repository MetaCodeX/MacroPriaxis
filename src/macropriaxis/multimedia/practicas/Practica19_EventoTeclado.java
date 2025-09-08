package macropriaxis.multimedia.practicas;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Practica19_EventoTeclado extends Application {
    
    private HBox hbox = new HBox();
    public Text texto = new Text("Original - Presiona cualquier tecla");

    @Override
    public void start(Stage primaryStage) {
        hbox.getChildren().add(texto);
        Scene scene = new Scene(hbox, 400, 250);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                texto.setText("Tecla Presionada: " + ke.getCode());
            }
        });

        primaryStage.setTitle("Evento de Teclado");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Solicitar foco para que detecte las teclas
        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}