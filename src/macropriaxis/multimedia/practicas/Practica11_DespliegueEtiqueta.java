package macropriaxis.multimedia.practicas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica11_DespliegueEtiqueta {
   
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 11 - Despliegue de etiquetas - Carlos Eduardo Juarez Ricardo - 09/09/2025");

        // Crear etiqueta con nombre y fecha
        Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
        nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");

        Label etiqueta = new Label();
        etiqueta.setText("Etiqueta de texto");

        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.getChildren().addAll(nameDateLabel, etiqueta);

        Scene scene = new Scene(mainContainer, 400, 200);
        stage.setScene(scene);
        stage.show();
    }
}