package macropriaxis.multimedia.practicas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class Practica17_DespliegueImagen6 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Despliegue de imagen 6");

        // Usar una imagen por defecto desde resources en lugar de ruta local
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
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error cargando imagen: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}