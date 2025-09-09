package macropriaxis.multimedia.practicas;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Practica14_DespliegueImagen3 {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 14 - Despliegue de imagen 3 - Carlos Eduardo Juarez Ricardo - 09/09/2025");

        Image image = new Image("https://www.uv.mx/dgdaie/files/2013/08/zlogosimbolo_color.jpg", 100, 100, false, false);
        ImageView imageView = new ImageView(image);

        HBox hbox = new HBox(imageView);

        Scene scene = new Scene(hbox, 400, 200);
        stage.setScene(scene);
        stage.show();
    }
}