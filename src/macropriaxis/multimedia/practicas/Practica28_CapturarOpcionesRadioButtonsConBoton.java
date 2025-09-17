package macropriaxis.multimedia.practicas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica28_CapturarOpcionesRadioButtonsConBoton {
    
    public static void ejecutar() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Práctica 28 - Capturar Opciones RadioButtons con Botón - Carlos Eduardo Juarez Ricardo - 09/09/2025");
            
            // Crear contenedor principal
            VBox mainContainer = new VBox(20);
            mainContainer.setAlignment(Pos.CENTER);
            mainContainer.setStyle("-fx-padding: 20;");
            
            // Crear etiqueta con nombre y fecha
            Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
            nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
            
            // Crear etiqueta de instrucciones
            Label instructionLabel = new Label("Selecciona una opción y presiona Enviar");
            instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
            
            // Crear grupo de botones de radio
            ToggleGroup group = new ToggleGroup();
            
            // Crear botones de radio
            RadioButton rb1 = new RadioButton("Opción 1");
            rb1.setUserData("opcion_1");
            rb1.setToggleGroup(group);
            rb1.setStyle("-fx-font-size: 12px;");
            
            RadioButton rb2 = new RadioButton("Opción 2");
            rb2.setUserData("opcion_2");
            rb2.setToggleGroup(group);
            rb2.setStyle("-fx-font-size: 12px;");
            
            // Crear botón de envío
            Button boton = new Button("Enviar");
            boton.setStyle("-fx-font-size: 12px; -fx-padding: 8 16;");
            
            // Crear etiqueta de resultado
            Label resultLabel = new Label("Resultado: Esperando selección y envío...");
            resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
            
            // Configurar evento de clic en el botón
            boton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (event != null) {
                            if (group.getSelectedToggle() != null) {
                                String selectedOption = group.getSelectedToggle().getUserData().toString();
                                resultLabel.setText("Resultado: Opción enviada: " + selectedOption);
                                resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");
                                System.out.println("Opción enviada: " + selectedOption);
                            } else {
                                resultLabel.setText("Resultado: Por favor, selecciona una opción antes de enviar");
                                resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: orange;");
                            }
                        }
                    } catch (Exception e) {
                        resultLabel.setText("Error procesando envío: " + e.getMessage());
                        resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                        System.err.println("Error en evento de botón: " + e.getMessage());
                    }
                }
            });
            
            // Crear contenedor HBox para los controles
            HBox controlsContainer = new HBox(20, rb1, rb2, boton);
            controlsContainer.setAlignment(Pos.CENTER);
            
            // Agregar elementos al contenedor principal
            mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, controlsContainer, resultLabel);
            
            Scene scene = new Scene(mainContainer, 500, 300);
            stage.setScene(scene);
            
            // Manejar cierre de ventana con limpieza
            stage.setOnCloseRequest(e -> {
                try {
                    System.out.println("Práctica 28 cerrada correctamente");
                    // Limpiar referencias
                    boton.setOnAction(null);
                    mainContainer.getChildren().clear();
                    controlsContainer.getChildren().clear();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar práctica 28: " + ex.getMessage());
                }
            });
            
            // Mostrar la ventana
            stage.show();
            
        } catch (Exception e) {
            System.err.println("Error crítico en Práctica 28: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar Práctica 28", e);
        }
    }
}
