package macropriaxis.util;

import javax.swing.*;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    /**
     * Carga una imagen y la asigna a un JLabel, escalándola si es necesario.
     *
     * @param label     El JLabel al que se asignará la imagen.
     * @param imagePath La ruta de la imagen (relativa al classpath).
     */
    public static void setImageToLabel(JLabel label, String imagePath) {
        System.out.println("Intentando cargar imagen desde: " + imagePath);

        // Obtener la URL del recurso
        URL imageUrl = ImageLoader.class.getResource(imagePath);
        if (imageUrl == null) {
            System.err.println("Error: No se pudo encontrar el recurso en: " + imagePath);
            return;
        }
        System.out.println("Imagen encontrada en: " + imageUrl.toString());

        // Cargar la imagen
        try {
            if (imagePath.toLowerCase().endsWith(".gif")) {
                // Manejo especial para GIFs (no escalar para preservar la animación)
                ImageIcon icon = new ImageIcon(imageUrl);
                if (icon.getImage() == null) {
                    System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
                    return;
                }
                label.setIcon(icon);
                System.out.println("GIF asignado al JLabel sin escalar.");
            } else {
                // Cargar y escalar otras imágenes (PNG, JPG, etc.)
                BufferedImage image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
                    return;
                }

                // Escalar la imagen al tamaño del JLabel
                Image scaledImage = image.getScaledInstance(
                        label.getWidth(),
                        label.getHeight(),
                        Image.SCALE_SMOOTH
                );

                // Asignar la imagen escalada al JLabel
                label.setIcon(new ImageIcon(scaledImage));
                System.out.println("Imagen escalada y asignada al JLabel correctamente.");
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    /**
     * Carga una imagen y la devuelve como un ImageIcon.
     *
     * @param imagePath La ruta de la imagen (relativa al classpath).
     * @return Un ImageIcon con la imagen cargada, o null si no se pudo cargar.
     */
    public static ImageIcon loadImageIcon(String imagePath) {
        System.out.println("Intentando cargar imagen desde: " + imagePath);

        // Obtener la URL del recurso
        URL imageUrl = ImageLoader.class.getResource(imagePath);
        if (imageUrl == null) {
            System.err.println("Error: No se pudo encontrar el recurso en: " + imagePath);
            return null;
        }
        System.out.println("Imagen encontrada en: " + imageUrl.toString());

        // Cargar la imagen
        try {
            if (imagePath.toLowerCase().endsWith(".gif")) {
                // Manejo especial para GIFs
                return new ImageIcon(imageUrl);
            } else {
                // Cargar otras imágenes (PNG, JPG, etc.)
                BufferedImage image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
                    return null;
                }
                return new ImageIcon(image);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            return null;
        }
    }

    /**
     * Escala una imagen al tamaño especificado.
     *
     * @param icon  El ImageIcon original.
     * @param width  El ancho deseado.
     * @param height La altura deseada.
     * @return Un ImageIcon escalado.
     */
    public static ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        if (icon == null) {
            System.err.println("Error: El ImageIcon proporcionado es nulo.");
            return null;
        }

        Image scaledImage = icon.getImage().getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );
        return new ImageIcon(scaledImage);
    }
}