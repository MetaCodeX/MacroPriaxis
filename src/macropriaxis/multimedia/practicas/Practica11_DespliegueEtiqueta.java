package macropriaxis.multimedia.practicas;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Practica11_DespliegueEtiqueta {
   
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 11 - Despliegue de etiquetas");

        Label etiqueta = new Label();
        etiqueta.setText("Etiqueta de texto");

        HBox hbox = new HBox(etiqueta);

        Scene scene = new Scene(hbox, 300, 150);
        stage.setScene(scene);
        stage.show();
    }
}