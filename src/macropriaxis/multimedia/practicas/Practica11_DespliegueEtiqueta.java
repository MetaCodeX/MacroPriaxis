package macropriaxis.multimedia.practicas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Practica11_DespliegueEtiqueta extends Application {
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Despliegue de etiquetas");

        Label etiqueta = new Label();
        etiqueta.setText("Etiqueta de texto");

        HBox hbox = new HBox(etiqueta);

        Scene scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}