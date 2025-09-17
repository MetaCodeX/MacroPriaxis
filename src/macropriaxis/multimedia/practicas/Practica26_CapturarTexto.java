package macropriaxis.multimedia.practicas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica26_CapturarTexto {
    
    public static void ejecutar() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Práctica 26 - Capturar Texto - Carlos Eduardo Juarez Ricardo - 09/09/2025");
            
            // Crear contenedor principal
            VBox mainContainer = new VBox(20);
            mainContainer.setAlignment(Pos.CENTER);
            mainContainer.setStyle("-fx-padding: 20;");
            
            // Crear etiqueta con nombre y fecha
            Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
            nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
            
            // Crear etiqueta de instrucciones
            Label instructionLabel = new Label("Introduce tu nombre y presiona Enviar");
            instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
            
            // Crear etiqueta para el campo de nombre
            Label etiquetaNombre = new Label("Nombre:");
            etiquetaNombre.setMinWidth(100);
            etiquetaNombre.setAlignment(Pos.BOTTOM_RIGHT);
            etiquetaNombre.setStyle("-fx-font-size: 12px;");
            
            // Crear campo de texto
            TextField nombre = new TextField();
            nombre.setMinWidth(200);
            nombre.setMaxWidth(200);
            nombre.setPromptText("Introduzca su nombre por favor.");
            nombre.setStyle("-fx-font-size: 12px;");
            
            // Crear botón de envío
            Button boton = new Button("Enviar");
            boton.setStyle("-fx-font-size: 12px; -fx-padding: 8 16;");
            
            // Crear etiqueta de resultado
            Label resultLabel = new Label("Resultado: Esperando entrada...");
            resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
            
            // Configurar evento de clic en el botón
            boton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                        if (e != null) {
                            String nombreCapturado = nombre.getText().trim();
                            if (!nombreCapturado.isEmpty()) {
                                resultLabel.setText("Resultado: El nombre es: " + nombreCapturado);
                                resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");
                                System.out.println("El nombre es: " + nombreCapturado);
                            } else {
                                resultLabel.setText("Resultado: Por favor, introduce un nombre válido");
                                resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: orange;");
                            }
                        }
                    } catch (Exception ex) {
                        resultLabel.setText("Error procesando entrada: " + ex.getMessage());
                        resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                        System.err.println("Error en evento de botón: " + ex.getMessage());
                    }
                }
            });
            
            // Crear contenedor HBox para los controles
            HBox controlsContainer = new HBox(10, etiquetaNombre, nombre, boton);
            controlsContainer.setAlignment(Pos.CENTER);
            
            // Agregar elementos al contenedor principal
            mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, controlsContainer, resultLabel);
            
            Scene scene = new Scene(mainContainer, 500, 300);
            stage.setScene(scene);
            
            // Manejar cierre de ventana con limpieza
            stage.setOnCloseRequest(e -> {
                try {
                    System.out.println("Práctica 26 cerrada correctamente");
                    // Limpiar referencias
                    boton.setOnAction(null);
                    mainContainer.getChildren().clear();
                    controlsContainer.getChildren().clear();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar práctica 26: " + ex.getMessage());
                }
            });
            
            // Mostrar la ventana
            stage.show();
            
        } catch (Exception e) {
            System.err.println("Error crítico en Práctica 26: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar Práctica 26", e);
        }
    }
}
