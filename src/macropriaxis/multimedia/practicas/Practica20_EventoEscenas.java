package macropriaxis.multimedia.practicas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Practica20_EventoEscenas {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 20 - Eventos de Escena - Flechas - Carlos Eduardo Juarez Ricardo - 09/09/2025");
        
        // Crear contenedor principal
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-padding: 20;");
        
        // Crear etiqueta con nombre y fecha
        Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
        nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
        
        // Crear etiqueta de instrucciones
        Label instructionLabel = new Label("Usa las flechas del teclado para navegar");
        instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
        
        // Crear texto que mostrará la flecha presionada
        Text texto = new Text("Texto original - Usa las flechas del teclado");
        texto.setStyle("-fx-font-size: 16px; -fx-fill: black;");

        Scene escena = new Scene(mainContainer, 500, 350);
        
        // Configurar el manejador de eventos de teclado con manejo de errores
        escena.setOnKeyPressed((KeyEvent e) -> {
            try {
                if (e == null || e.getCode() == null) {
                    texto.setText("Error: Evento de teclado inválido");
                    texto.setStyle("-fx-font-size: 16px; -fx-fill: red;");
                    return;
                }
                
                switch (e.getCode()) {
                    case UP:
                        texto.setText("↑ Flecha Arriba");
                        texto.setStyle("-fx-font-size: 18px; -fx-fill: green;");
                        break;
                    case DOWN:
                        texto.setText("↓ Flecha Abajo");
                        texto.setStyle("-fx-font-size: 18px; -fx-fill: green;");
                        break;
                    case LEFT:
                        texto.setText("← Flecha Izquierda");
                        texto.setStyle("-fx-font-size: 18px; -fx-fill: green;");
                        break;
                    case RIGHT:
                        texto.setText("→ Flecha Derecha");
                        texto.setStyle("-fx-font-size: 18px; -fx-fill: green;");
                        break;
                    default:
                        texto.setText("Presiona una flecha del teclado (↑↓←→)");
                        texto.setStyle("-fx-font-size: 16px; -fx-fill: orange;");
                        break;
                }
            } catch (Exception ex) {
                texto.setText("Error procesando tecla: " + ex.getMessage());
                texto.setStyle("-fx-font-size: 16px; -fx-fill: red;");
            }
        });
        
        // Agregar elementos al contenedor
        mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, texto);

        stage.setScene(escena);
        stage.show();
        
        // Solicitar foco para que detecte las teclas
        stage.requestFocus();
        escena.getRoot().requestFocus();
        
        // Manejar cierre de ventana
        stage.setOnCloseRequest(e -> {
            System.out.println("Práctica 20 cerrada");
        });
        
        // Asegurar que la ventana tenga foco después de mostrarse
        stage.setOnShown(e -> {
            stage.requestFocus();
            escena.getRoot().requestFocus();
        });
    }
}