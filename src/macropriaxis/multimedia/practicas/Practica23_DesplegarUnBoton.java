package macropriaxis.multimedia.practicas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica23_DesplegarUnBoton {
    
    private static Button btn;
    
    public static void ejecutar() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Práctica 23 - Desplegar un Botón - Carlos Eduardo Juarez Ricardo - 09/09/2025");
            
            // Crear contenedor principal
            VBox mainContainer = new VBox(20);
            mainContainer.setAlignment(Pos.CENTER);
            mainContainer.setStyle("-fx-padding: 20;");
            
            // Crear etiqueta con nombre y fecha
            Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
            nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
            
            // Crear etiqueta de instrucciones
            Label instructionLabel = new Label("Haz clic en el botón para cambiar su estado");
            instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
            
            // Crear botón con estado inicial
            btn = new Button("Apagado");
            btn.setStyle("-fx-font-size: 16px; -fx-padding: 15 30; -fx-background-color: lightgray;");
            
            // Crear etiqueta de estado
            Label statusLabel = new Label("Estado actual: Apagado");
            statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
            
            // Configurar evento de clic en el botón usando lambda
            btn.setOnAction(e -> buttonClick(statusLabel));
            
            // Crear contenedor StackPane para el botón
            StackPane root = new StackPane();
            root.getChildren().add(btn);
            
            // Agregar elementos al contenedor principal
            mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, root, statusLabel);
            
            Scene scene = new Scene(mainContainer, 400, 350);
            stage.setScene(scene);
            
            // Manejar cierre de ventana con limpieza
            stage.setOnCloseRequest(e -> {
                try {
                    System.out.println("Práctica 23 cerrada correctamente");
                    // Limpiar referencias
                    btn.setOnAction(null);
                    mainContainer.getChildren().clear();
                    root.getChildren().clear();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar práctica 23: " + ex.getMessage());
                }
            });
            
            // Mostrar la ventana
            stage.show();
            
        } catch (Exception e) {
            System.err.println("Error crítico en Práctica 23: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar Práctica 23", e);
        }
    }
    
    private static void buttonClick(Label statusLabel) {
        try {
            if (btn.getText().equals("Apagado")) {
                btn.setText("Encendido");
                btn.setStyle("-fx-font-size: 16px; -fx-padding: 15 30; -fx-background-color: lightgreen;");
                statusLabel.setText("Estado actual: Encendido");
                statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");
                System.out.println("Botón cambiado a: Encendido");
            } else {
                btn.setText("Apagado");
                btn.setStyle("-fx-font-size: 16px; -fx-padding: 15 30; -fx-background-color: lightgray;");
                statusLabel.setText("Estado actual: Apagado");
                statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
                System.out.println("Botón cambiado a: Apagado");
            }
        } catch (Exception e) {
            statusLabel.setText("Error cambiando estado: " + e.getMessage());
            statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
            System.err.println("Error en buttonClick: " + e.getMessage());
        }
    }
}
