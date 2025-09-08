package macropriaxis.multimedia.practicas;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class Practica17_DespliegueImagen6 {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 17 - Despliegue de imagen 6");

        try {
            Image image;
            try {
                FileInputStream input = new FileInputStream("src/macropriaxis/media/kasane.gif");
                image = new Image(input, 300, 100, false, false);
            } catch (Exception e) {
                // Si no se encuentra la imagen local, usar una imagen web
                image = new Image("https://www.uv.mx/escolar/licenciatura2022/img/logouv_solo.jpg", 300, 100, false, false);
            }
            
            ImageView imageView = new ImageView(image);
            HBox hbox = new HBox(imageView);
            Scene scene = new Scene(hbox, 400, 200);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error cargando imagen: " + e.getMessage());
        }
    }
}