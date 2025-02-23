package macropriaxis.util;

import javax.swing.*;
import java.awt.Image;

public class ImageLoader {

    public static void setImageToLabel(JLabel label, String imagePath) {
        System.out.println("Intentando cargar imagen desde: " + imagePath);

    
        ImageIcon icon = new ImageIcon(ImageLoader.class.getResource(imagePath));
        if (icon.getImage() == null) {
            System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
            return;
        }

        
        Image scaledImage = icon.getImage().getScaledInstance(
                label.getWidth(), 
                label.getHeight(), 
                Image.SCALE_SMOOTH 
        );

        
        label.setIcon(new ImageIcon(scaledImage));
        System.out.println("Imagen escalada y asignada al JLabel correctamente.");
    }
}