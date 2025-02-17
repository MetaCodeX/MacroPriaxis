package macropriaxis.util;

import javax.swing.*;
import java.awt.Image;
/**
 *
 * @author MetaCodeX
 */
public class ImageLoader {

    public static void setImageToLabel(JLabel label, String imagePath) {
        ImageIcon icon = new ImageIcon(ImageLoader.class.getResource(imagePath));
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