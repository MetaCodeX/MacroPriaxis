package macropriaxis.multimedia.practicas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Practica18_CapturaAnchoImagen extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Captura Ancho de Imagen");

        Image image = new Image("https://www.uv.mx/veracruz/nutricion/files/2021/04/Flor_con_uv_sin_fondo.png", 300, 300, true, true, true);
        ImageView imageView = new ImageView(image);

        Text anchoImagen = new Text();
        String texto = "Ancho: " + image.getRequestedWidth();
        anchoImagen.setText(texto);

        HBox hbox = new HBox(imageView, anchoImagen);

        Scene scene = new Scene(hbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}