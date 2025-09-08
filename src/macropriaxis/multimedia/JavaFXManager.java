package macropriaxis.multimedia;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Gestor único para aplicaciones JavaFX
 * Soluciona el problema de múltiples lanzamientos
 */
public class JavaFXManager extends Application {
    
    private static Stage primaryStage;
    private static boolean isLaunched = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("JavaFX - MacroPriaxis");
        // No mostrar la ventana inicial, se mostrará cuando se llame a una práctica
    }
    
    public static void launchJavaFX() {
        if (!isLaunched) {
            isLaunched = true;
            new Thread(() -> {
                Application.launch(JavaFXManager.class);
            }).start();
            
            // Esperar a que JavaFX se inicialice
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public static void showPractice(Runnable practiceCode) {
        if (!isLaunched) {
            launchJavaFX();
        }
        
        Platform.runLater(() -> {
            try {
                practiceCode.run();
            } catch (Exception e) {
                System.err.println("Error ejecutando práctica: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static boolean isLaunched() {
        return isLaunched;
    }
}