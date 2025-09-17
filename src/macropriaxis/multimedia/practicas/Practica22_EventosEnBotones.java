package macropriaxis.multimedia.practicas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica22_EventosEnBotones {
    
    public static void ejecutar() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Práctica 22 - Eventos en Botones - Carlos Eduardo Juarez Ricardo - 09/09/2025");
            
            // Crear contenedor principal
            VBox mainContainer = new VBox(20);
            mainContainer.setAlignment(Pos.CENTER);
            mainContainer.setStyle("-fx-padding: 20;");
            
            // Crear etiqueta con nombre y fecha
            Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
            nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
            
            // Crear etiqueta de instrucciones
            Label instructionLabel = new Label("Haz clic en el botón para ver el evento");
            instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
            
            // Crear botón
            Button boton = new Button("Presionar");
            boton.setStyle("-fx-font-size: 14px; -fx-padding: 10 20;");
            
            // Crear etiqueta de estado
            Label statusLabel = new Label("Estado: Esperando clic en el botón");
            statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
            
            // Configurar evento de clic en el botón
            boton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                        if (e != null) {
                            statusLabel.setText("Estado: Botón presionado correctamente");
                            statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");
                            System.out.println("Se presionó el botón");
                        }
                    } catch (Exception ex) {
                        statusLabel.setText("Error procesando evento de botón: " + ex.getMessage());
                        statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                        System.err.println("Error en evento de botón: " + ex.getMessage());
                    }
                }
            });
            
            // Crear contenedor para el botón
            HBox buttonContainer = new HBox(boton);
            buttonContainer.setAlignment(Pos.CENTER);
            
            // Agregar elementos al contenedor principal
            mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, buttonContainer, statusLabel);
            
            Scene scene = new Scene(mainContainer, 500, 300);
            stage.setScene(scene);
            
            // Manejar cierre de ventana con limpieza
            stage.setOnCloseRequest(e -> {
                try {
                    System.out.println("Práctica 22 cerrada correctamente");
                    // Limpiar referencias
                    boton.setOnAction(null);
                    mainContainer.getChildren().clear();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar práctica 22: " + ex.getMessage());
                }
            });
            
            // Mostrar la ventana
            stage.show();
            
        } catch (Exception e) {
            System.err.println("Error crítico en Práctica 22: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar Práctica 22", e);
        }
    }
}
