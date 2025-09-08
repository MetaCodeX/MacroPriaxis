package macropriaxis.multimedia.practicas;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Practica20_EventoEscenas {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 20 - Eventos de Escena - Flechas");
        
        Text texto = new Text();
        texto.setText("Texto original - Usa las flechas del teclado");
        VBox contenedor = new VBox(texto);

        Scene escena = new Scene(contenedor, 400, 300);
        escena.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case UP:
                    texto.setText("Flecha Arriba");
                    break;
                case DOWN:
                    texto.setText("Flecha Abajo");
                    break;
                case LEFT:
                    texto.setText("Flecha Izquierda");
                    break;
                case RIGHT:
                    texto.setText("Flecha Derecha");
                    break;
                default:
                    texto.setText("Presiona una flecha del teclado");
                    break;
            }
        });

        stage.setScene(escena);
        stage.show();
        
        // Solicitar foco para que detecte las teclas
        escena.getRoot().requestFocus();
    }
}