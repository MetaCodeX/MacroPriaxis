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

public class Practica24_DesplegarUnBotonConEstado {
    
    public static void ejecutar() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Práctica 24 - Desplegar un Botón con Estado - Carlos Eduardo Juarez Ricardo - 09/09/2025");
            
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
            Button btn = new Button("Apagado");
            btn.setStyle("-fx-font-size: 16px; -fx-padding: 15 30; -fx-background-color: lightgray;");
            
            // Crear etiqueta de estado
            Label statusLabel = new Label("Estado actual: Apagado");
            statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
            
            // Configurar evento de clic en el botón usando EventHandler
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (event != null) {
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
                        }
                    } catch (Exception e) {
                        statusLabel.setText("Error cambiando estado: " + e.getMessage());
                        statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                        System.err.println("Error en handle: " + e.getMessage());
                    }
                }
            });
            
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
                    System.out.println("Práctica 24 cerrada correctamente");
                    // Limpiar referencias
                    btn.setOnAction(null);
                    mainContainer.getChildren().clear();
                    root.getChildren().clear();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar práctica 24: " + ex.getMessage());
                }
            });
            
            // Mostrar la ventana
            stage.show();
            
        } catch (Exception e) {
            System.err.println("Error crítico en Práctica 24: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar Práctica 24", e);
        }
    }
}
