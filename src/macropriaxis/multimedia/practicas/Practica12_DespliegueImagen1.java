package macropriaxis.multimedia.practicas;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica12_DespliegueImagen1 {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 12 - Despliegue de imagen 1 - Carlos Eduardo Juarez Ricardo - 09/09/2025");
        
        // Crear contenedor principal
        VBox mainContainer = new VBox(10);
        
        // Crear etiqueta con nombre y fecha
        Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
        nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
        
        // Crear etiqueta de estado
        Label statusLabel = new Label("Cargando imagen...");
        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
        
        // Crear contenedor para la imagen
        HBox imageContainer = new HBox();
        
        try {
            // Intentar cargar la imagen con manejo de errores
            Image image = new Image("https://www.uv.mx/escolar/licenciatura2022/img/logouv_solo.jpg", 
                                   300, 300, true, true, true);
            
            ImageView imageView = new ImageView(image);
            
            // Verificar si la imagen se cargó correctamente
            if (image.isError()) {
                statusLabel.setText("Error: No se pudo cargar la imagen desde la URL");
                statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: red;");
                
                // Mostrar imagen de respaldo o mensaje
                Label errorLabel = new Label("Imagen no disponible");
                errorLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
                imageContainer.getChildren().add(errorLabel);
            } else {
                statusLabel.setText("Imagen cargada exitosamente");
                statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: green;");
                imageContainer.getChildren().add(imageView);
            }
            
        } catch (Exception e) {
            statusLabel.setText("Error al cargar imagen: " + e.getMessage());
            statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: red;");
            
            Label errorLabel = new Label("Error de conexión o imagen no encontrada");
            errorLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
            imageContainer.getChildren().add(errorLabel);
        }
        
        // Configurar el layout
        mainContainer.getChildren().addAll(nameDateLabel, statusLabel, imageContainer);
        mainContainer.setStyle("-fx-alignment: center; -fx-padding: 20;");
        
        Scene scene = new Scene(mainContainer, 500, 500);
        stage.setScene(scene);
        stage.show();
        
        // Manejar cierre de ventana
        stage.setOnCloseRequest(e -> {
            System.out.println("Práctica 12 cerrada");
        });
    }
}