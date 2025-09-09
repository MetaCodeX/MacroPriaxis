package macropriaxis.multimedia.practicas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Practica09_CambioEscena {
    
    public static void ejecutar() {
        Stage escenario = new Stage();
        
        VBox layout = new VBox();
        VBox layout2 = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout2.setSpacing(10);

        Scene scene = new Scene(layout, 300, 300);
        Scene scene2 = new Scene(layout2, 300, 300);

        Label label1 = new Label("Primera Escena");
        Label label2 = new Label("Segunda Escena");

        Button button = new Button("Poner escena 2");
        button.setOnAction(e -> escenario.setScene(scene2));

        Button button2 = new Button("Poner escena 1");
        button2.setOnAction(e -> escenario.setScene(scene));

        TextField text = new TextField();
        text.setMaxWidth(100);
        text.setPromptText("Escribe aquí...");

        layout.getChildren().addAll(label1, button);
        layout2.getChildren().addAll(label2, button2, text);

        escenario.setTitle("Práctica 9 - Programa Principal - Carlos Eduardo Juarez Ricardo - 09/09/2025");
        escenario.setScene(scene);
        escenario.show();
    }
}