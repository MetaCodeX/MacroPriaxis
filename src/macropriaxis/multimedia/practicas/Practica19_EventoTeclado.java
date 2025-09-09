package macropriaxis.multimedia.practicas;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Practica19_EventoTeclado {
    
    public static void ejecutar() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Práctica 19 - Evento de Teclado - Carlos Eduardo Juarez Ricardo - 09/09/2025");
            
            // Crear contenedor principal
            VBox mainContainer = new VBox(20);
            mainContainer.setAlignment(Pos.CENTER);
            mainContainer.setStyle("-fx-padding: 20;");
            
            // Crear etiqueta con nombre y fecha
            Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
            nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
            
            // Crear etiqueta de instrucciones
            Label instructionLabel = new Label("Presiona cualquier tecla para ver el evento");
            instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
            
            // Crear texto que mostrará la tecla presionada
            Text texto = new Text("Original - Presiona cualquier tecla");
            texto.setStyle("-fx-font-size: 16px; -fx-fill: black;");
            
            // Agregar elementos al contenedor
            mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, texto);
            
            Scene scene = new Scene(mainContainer, 500, 300);
            
            // Configurar el manejador de eventos de teclado con mejor manejo de errores
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    try {
                        if (ke != null && ke.getCode() != null) {
                            texto.setText("Tecla Presionada: " + ke.getCode().toString());
                            texto.setStyle("-fx-font-size: 16px; -fx-fill: green;");
                        } else {
                            texto.setText("Error: Evento de teclado inválido");
                            texto.setStyle("-fx-font-size: 16px; -fx-fill: red;");
                        }
                    } catch (Exception e) {
                        texto.setText("Error procesando tecla: " + e.getMessage());
                        texto.setStyle("-fx-font-size: 16px; -fx-fill: red;");
                        System.err.println("Error en evento de teclado: " + e.getMessage());
                    }
                }
            });

            stage.setScene(scene);
            
            // Configurar eventos de ventana
            stage.setOnShown(e -> {
                try {
                    stage.requestFocus();
                    scene.getRoot().requestFocus();
                } catch (Exception ex) {
                    System.err.println("Error configurando foco: " + ex.getMessage());
                }
            });
            
            // Manejar cierre de ventana con limpieza
            stage.setOnCloseRequest(e -> {
                try {
                    System.out.println("Práctica 19 cerrada correctamente");
                    // Limpiar referencias
                    scene.setOnKeyPressed(null);
                    mainContainer.getChildren().clear();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar práctica 19: " + ex.getMessage());
                }
            });
            
            // Mostrar la ventana
            stage.show();
            
            // Solicitar foco después de mostrar
            javafx.application.Platform.runLater(() -> {
                try {
                    stage.requestFocus();
                    scene.getRoot().requestFocus();
                } catch (Exception e) {
                    System.err.println("Error solicitando foco: " + e.getMessage());
                }
            });
            
        } catch (Exception e) {
            System.err.println("Error crítico en Práctica 19: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar Práctica 19", e);
        }
    }
}