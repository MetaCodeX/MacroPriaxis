package macropriaxis.util;

import javax.swing.*;
import java.awt.Image;
import java.io.File;

public class ImageLoader {

    public static void setImageToLabel(JLabel label, String imagePath) {
        System.out.println("Intentando cargar imagen desde: " + imagePath);

        // Cargar la imagen desde el sistema de archivos
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            System.err.println("Error: No se pudo encontrar la imagen en la ruta: " + imagePath);
            return;
        }

        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        if (icon.getImage() == null) {
            System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
            return;
        }

        // Escalar la imagen al tamaño del JLabel
        Image scaledImage = icon.getImage().getScaledInstance(
                label.getWidth(), 
                label.getHeight(), 
                Image.SCALE_SMOOTH 
        );

        // Asignar la imagen escalada al JLabel
        label.setIcon(new ImageIcon(scaledImage));
        System.out.println("Imagen escalada y asignada al JLabel correctamente.");
    }
}