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

public class Practica01_CodigoBase {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 1 - Código Base JavaFX - Carlos Eduardo Juarez Ricardo - 09/09/2025");
        
        try {
            // Crear contenedor principal
            VBox mainContainer = new VBox(20);
            mainContainer.setAlignment(Pos.CENTER);
            mainContainer.setStyle("-fx-padding: 20;");
            
            // Crear etiqueta con nombre y fecha
            Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
            nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
            
            // Crear etiqueta de instrucciones
            Label instructionLabel = new Label("Práctica 1: Código Base JavaFX");
            instructionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: blue;");
            
            // Crear etiqueta de estado
            Label statusLabel = new Label("Presiona el botón para ver el mensaje");
            statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: gray;");
            
            // Crear botón principal
            Button btn = new Button();
            btn.setText("Oprimir botón");
            btn.setStyle("-fx-font-size: 14px; -fx-padding: 10 20;");
            
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        System.out.println("Hello World!");
                        statusLabel.setText("¡Mensaje enviado a consola: Hello World!");
                        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: green;");
                    } catch (Exception e) {
                        statusLabel.setText("Error al procesar evento: " + e.getMessage());
                        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: red;");
                        System.err.println("Error en evento del botón: " + e.getMessage());
                    }
                }
            });
            
            // Crear contenedor para el botón
            StackPane buttonContainer = new StackPane();
            buttonContainer.getChildren().add(btn);
            buttonContainer.setAlignment(Pos.CENTER);
            
            // Agregar elementos al contenedor principal
            mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, buttonContainer, statusLabel);
            
            Scene scene = new Scene(mainContainer, 500, 300);
            
            stage.setScene(scene);
            stage.show();
            
            // Simular los métodos init() y stop()
            System.out.println("Inicia la ejecución del programa - Práctica 1");
            
            // Manejar cierre de ventana con limpieza de recursos
            stage.setOnCloseRequest(e -> {
                System.out.println("Finaliza la ejecución del programa - Práctica 1");
                // Limpiar recursos si es necesario
                btn.setOnAction(null);
            });
            
        } catch (Exception e) {
            System.err.println("Error al crear la práctica 1: " + e.getMessage());
            e.printStackTrace();
            
            // Crear una ventana de error simple
            Stage errorStage = new Stage();
            errorStage.setTitle("Error - Práctica 1");
            
            Label errorLabel = new Label("Error al inicializar la práctica: " + e.getMessage());
            errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: red; -fx-padding: 20;");
            
            Scene errorScene = new Scene(new StackPane(errorLabel), 400, 200);
            errorStage.setScene(errorScene);
            errorStage.show();
        }
    }
}