package macropriaxis.multimedia;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Gestor optimizado para aplicaciones JavaFX
 * Maneja correctamente el ciclo de vida de JavaFX
 */
public class JavaFXManager extends Application {
    
    private static Stage primaryStage;
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static volatile boolean hasError = false;
    private static volatile boolean isShuttingDown = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            primaryStage = stage;
            primaryStage.setTitle("JavaFX - MacroPriaxis");
            primaryStage.setWidth(1);
            primaryStage.setHeight(1);
            primaryStage.setOpacity(0);
            primaryStage.setX(-1000);
            primaryStage.setY(-1000);
            primaryStage.show();
            
            isInitialized.set(true);
            System.out.println("JavaFX inicializado correctamente");
        } catch (Exception e) {
            hasError = true;
            System.err.println("Error al inicializar JavaFX: " + e.getMessage());
            throw e;
        }
    }
    
    public static void initializeJavaFX() {
        if (isInitialized.get() || hasError || isShuttingDown) {
            return;
        }
        
        synchronized (JavaFXManager.class) {
            if (isInitialized.get() || hasError || isShuttingDown) {
                return;
            }
            
            try {
                // Verificar si JavaFX ya está disponible
                if (Platform.isFxApplicationThread()) {
                    isInitialized.set(true);
                    return;
                }
                
                // Configurar propiedades del sistema para evitar warnings
                System.setProperty("javafx.preloader", "");
                System.setProperty("javafx.verbose", "false");
                
                // Lanzar JavaFX en un hilo separado
                new Thread(() -> {
                    try {
                        Application.launch(JavaFXManager.class);
                    } catch (Exception e) {
                        System.err.println("Error al lanzar JavaFX: " + e.getMessage());
                        hasError = true;
                    }
                }, "JavaFX-Launcher").start();
                
                // Esperar un poco para la inicialización
                Thread.sleep(2000);
                
            } catch (Exception e) {
                System.err.println("Error al inicializar JavaFX: " + e.getMessage());
                hasError = true;
            }
        }
    }
    
    public static void executePractice(Runnable practiceCode) {
        if (isShuttingDown) {
            System.err.println("JavaFX se está cerrando, no se pueden ejecutar más prácticas");
            return;
        }
        
        if (hasError) {
            System.err.println("JavaFX no se pudo inicializar correctamente");
            return;
        }
        
        if (!isInitialized.get()) {
            initializeJavaFX();
        }
        
        if (hasError || !isInitialized.get()) {
            System.err.println("No se puede ejecutar la práctica debido a errores de inicialización");
            return;
        }
        
        // Ejecutar la práctica en el hilo de JavaFX
        try {
            if (Platform.isFxApplicationThread()) {
                practiceCode.run();
            } else {
                Platform.runLater(practiceCode);
            }
        } catch (Exception e) {
            System.err.println("Error ejecutando práctica: " + e.getMessage());
            e.printStackTrace();
            showErrorDialog("Error en la práctica", "Se produjo un error al ejecutar la práctica: " + e.getMessage());
        }
    }
    
    private static void showErrorDialog(String title, String message) {
        try {
            Platform.runLater(() -> {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            });
        } catch (Exception e) {
            System.err.println("Error mostrando diálogo: " + e.getMessage());
        }
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static boolean isAvailable() {
        return isInitialized.get() && !hasError && !isShuttingDown;
    }
    
    public static boolean hasError() {
        return hasError;
    }
    
    public static void shutdown() {
        isShuttingDown = true;
        if (isInitialized.get()) {
            Platform.runLater(() -> {
                try {
                    if (primaryStage != null) {
                        primaryStage.close();
                    }
                    Platform.exit();
                } catch (Exception e) {
                    System.err.println("Error al cerrar JavaFX: " + e.getMessage());
                }
            });
        }
    }
    
    public static void reset() {
        try {
            // Cerrar JavaFX si está ejecutándose
            if (isInitialized.get()) {
                shutdown();
                Thread.sleep(1000);
            }
            
            // Resetear todos los estados
            hasError = false;
            isShuttingDown = false;
            isInitialized.set(false);
            
            System.out.println("JavaFX Manager reseteado correctamente");
        } catch (Exception e) {
            System.err.println("Error al resetear JavaFX Manager: " + e.getMessage());
            // Forzar reset de estados
            hasError = false;
            isShuttingDown = false;
            isInitialized.set(false);
        }
    }
}