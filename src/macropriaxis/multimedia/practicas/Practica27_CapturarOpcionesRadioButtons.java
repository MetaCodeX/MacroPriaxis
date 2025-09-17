package macropriaxis.multimedia.practicas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica27_CapturarOpcionesRadioButtons {
    
    public static void ejecutar() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Práctica 27 - Capturar Opciones RadioButtons - Carlos Eduardo Juarez Ricardo - 09/09/2025");
            
            // Crear contenedor principal
            VBox mainContainer = new VBox(20);
            mainContainer.setAlignment(Pos.CENTER);
            mainContainer.setStyle("-fx-padding: 20;");
            
            // Crear etiqueta con nombre y fecha
            Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
            nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
            
            // Crear etiqueta de instrucciones
            Label instructionLabel = new Label("Selecciona una opción para ver el evento");
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
            
            // Crear etiqueta de resultado
            Label resultLabel = new Label("Resultado: Ninguna opción seleccionada");
            resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
            
            // Configurar listener para cambios en el grupo
            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                    try {
                        if (group.getSelectedToggle() != null) {
                            String selectedOption = group.getSelectedToggle().getUserData().toString();
                            resultLabel.setText("Resultado: Opción seleccionada: " + selectedOption);
                            resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");
                            System.out.println("Opción seleccionada: " + selectedOption);
                        } else {
                            resultLabel.setText("Resultado: Ninguna opción seleccionada");
                            resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
                        }
                    } catch (Exception e) {
                        resultLabel.setText("Error procesando selección: " + e.getMessage());
                        resultLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                        System.err.println("Error en listener de radio buttons: " + e.getMessage());
                    }
                }
            });
            
            // Crear contenedor HBox para los botones de radio
            HBox radioContainer = new HBox(20, rb1, rb2);
            radioContainer.setAlignment(Pos.CENTER);
            
            // Agregar elementos al contenedor principal
            mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, radioContainer, resultLabel);
            
            Scene scene = new Scene(mainContainer, 500, 300);
            stage.setScene(scene);
            
            // Manejar cierre de ventana con limpieza
            stage.setOnCloseRequest(e -> {
                try {
                    System.out.println("Práctica 27 cerrada correctamente");
                    // Limpiar referencias
                    mainContainer.getChildren().clear();
                    radioContainer.getChildren().clear();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar práctica 27: " + ex.getMessage());
                }
            });
            
            // Mostrar la ventana
            stage.show();
            
        } catch (Exception e) {
            System.err.println("Error crítico en Práctica 27: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar Práctica 27", e);
        }
    }
}
