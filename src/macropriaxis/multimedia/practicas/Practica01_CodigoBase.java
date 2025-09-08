package macropriaxis.multimedia.practicas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Practica01_CodigoBase {
    
    public static void ejecutar() {
        Stage stage = new Stage();
        stage.setTitle("Práctica 1 - Código Base JavaFX");
        
        Button btn = new Button();
        btn.setText("Oprimir botón");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root, 700, 400);
        
        stage.setScene(scene);
        stage.show();
        
        // Simular los métodos init() y stop()
        System.out.println("Inicia la ejecución del programa");
        stage.setOnCloseRequest(e -> {
            System.out.println("Finaliza la ejecución del programa");
        });
    }
}