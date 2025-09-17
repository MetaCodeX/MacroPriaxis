package macropriaxis.multimedia.practicas;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica21_EventosEnImagenes {
    
    public static void ejecutar() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Práctica 21 - Eventos en Imágenes - Carlos Eduardo Juarez Ricardo - 09/09/2025");
            
            // Crear contenedor principal
            VBox mainContainer = new VBox(20);
            mainContainer.setAlignment(Pos.CENTER);
            mainContainer.setStyle("-fx-padding: 20;");
            
            // Crear etiqueta con nombre y fecha
            Label nameDateLabel = new Label("Carlos Eduardo Juarez Ricardo - 09/09/2025");
            nameDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: darkblue; -fx-background-color: lightblue; -fx-padding: 5;");
            
            // Crear etiqueta de instrucciones
            Label instructionLabel = new Label("Haz clic en la imagen para ver el evento");
            instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
            
            // Crear imagen (usando una imagen más visible)
            Image imagen = new Image("file:src/macropriaxis/media/Cube.png", true);
            ImageView imageView = new ImageView(imagen);
            imageView.setFitWidth(300);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            
            // Crear etiqueta de estado
            Label statusLabel = new Label("Estado: Esperando clic en la imagen");
            statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
            
            // Configurar evento de clic en la imagen
            imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    try {
                        if (me != null) {
                            statusLabel.setText("Estado: Imagen presionada - Coordenadas: (" + 
                                              (int)me.getX() + ", " + (int)me.getY() + ")");
                            statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");
                            System.out.println("Se presionó la imagen en coordenadas: (" + 
                                             (int)me.getX() + ", " + (int)me.getY() + ")");
                        }
                    } catch (Exception e) {
                        statusLabel.setText("Error procesando evento de clic: " + e.getMessage());
                        statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                        System.err.println("Error en evento de clic: " + e.getMessage());
                    }
                }
            });
            
            // Crear contenedor para la imagen
            HBox imageContainer = new HBox(imageView);
            imageContainer.setAlignment(Pos.CENTER);
            
            // Agregar elementos al contenedor principal
            mainContainer.getChildren().addAll(nameDateLabel, instructionLabel, imageContainer, statusLabel);
            
            Scene scene = new Scene(mainContainer, 500, 400);
            stage.setScene(scene);
            
            // Manejar cierre de ventana con limpieza
            stage.setOnCloseRequest(e -> {
                try {
                    System.out.println("Práctica 21 cerrada correctamente");
                    // Limpiar referencias
                    imageView.setOnMousePressed(null);
                    mainContainer.getChildren().clear();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar práctica 21: " + ex.getMessage());
                }
            });
            
            // Mostrar la ventana
            stage.show();
            
        } catch (Exception e) {
            System.err.println("Error crítico en Práctica 21: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar Práctica 21", e);
        }
    }
}
