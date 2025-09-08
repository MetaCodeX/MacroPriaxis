package macropriaxis.multimedia.practicas;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class Practica16_DespliegueImagen5 {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 16 - Despliegue de Imagen 5");
        
        try {
            Image image;
            try {
                FileInputStream input = new FileInputStream("src/macropriaxis/media/kasane.gif");
                image = new Image(input);
            } catch (Exception e) {
                // Si no se encuentra la imagen local, usar una imagen web
                image = new Image("https://www.uv.mx/escolar/licenciatura2022/img/logouv_solo.jpg", 200, 100, false, false);
            }
            
            ImageView imageView = new ImageView(image);
            HBox hbox = new HBox(imageView);
            Scene scene = new Scene(hbox, 400, 300);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error cargando imagen: " + e.getMessage());
        }
    }
}