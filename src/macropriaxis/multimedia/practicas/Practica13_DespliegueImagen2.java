package macropriaxis.multimedia.practicas;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Practica13_DespliegueImagen2 {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 13 - Despliegue de imagen 2");

        Image image = new Image("https://www.uv.mx/veracruz/nutricion/files/2021/04/Flor_con_uv_sin_fondo.png", true);
        ImageView imageView = new ImageView(image);

        HBox hbox = new HBox(imageView);

        Scene scene = new Scene(hbox, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}