package macropriaxis.util;

import javax.swing.*;
import java.awt.Image;
import java.net.URL;
/**
 *
 * @author Hipolito
 */
public class ImageLoader {

    public static void setImageToLabel(JLabel label, String imagePath) {
        System.out.println("Intentando cargar imagen desde: " + imagePath);
        URL resourceUrl = ImageLoader.class.getResource(imagePath);
        if (resourceUrl == null) {
            System.err.println("URL del recurso es null para: " + imagePath);
            return;
        }
        
        ImageIcon icon = new ImageIcon(resourceUrl);
        if (icon.getImage() == null) {
            System.err.println("No se pudo cargar la imagen: " + imagePath);
            return;
        }

     
        Image scaledImage = icon.getImage().getScaledInstance(
                label.getWidth(), 
                label.getHeight(), 
                Image.SCALE_SMOOTH 
        );

      
        label.setIcon(new ImageIcon(scaledImage));
    }
}