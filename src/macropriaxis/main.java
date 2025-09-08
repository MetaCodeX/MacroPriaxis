package macropriaxis;

import macropriaxis.util.SplashScreen;
import javax.swing.Timer;

/**
 *
 * @author MetaCodeX
 */


public class main {

    public static void main(String[] args) {
        // Mostrar el SplashScreen
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);
        
        // Simular proceso de carga
        Timer progressTimer = new Timer(30, null);
        final int[] progress = {0};
        
        progressTimer.addActionListener(e -> {
            progress[0] += 1;
            splash.setProgress(progress[0]);
            
            if (progress[0] >= 100) {
                progressTimer.stop();
                splash.dispose();
                
                // Mostrar la ventana de login/register
                java.awt.EventQueue.invokeLater(() -> {
                    new macropriaxis.gui.LoginRegister().setVisible(true);
                });
            }
        });
        
        progressTimer.start();
    }
}   