package macropriaxis.multimedia.practicas;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Practica15_DespliegueImagen4 {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 15 - Despliegue de imagen 4 - Carlos Eduardo Juarez Ricardo - 09/09/2025");

        Image image = new Image("https://www.uv.mx/escolar/licenciatura2022/img/logouv_solo.jpg", 300, 300, true, true, true);
        ImageView imageView = new ImageView(image);

        HBox hbox = new HBox(imageView);

        Scene scene = new Scene(hbox, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}