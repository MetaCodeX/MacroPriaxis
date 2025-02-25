package macropriaxis.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
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
     * Carga una imagen y la asigna a un JPanel, escalándola si es necesario.
     *
     * @param panel     El JPanel al que se asignará la imagen.
     * @param imagePath La ruta de la imagen (relativa al classpath).
     */
    public static void setImageToPanel(JPanel panel, String imagePath) {
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

                // Usar un JLabel interno para mostrar el GIF en el JPanel
                JLabel gifLabel = new JLabel(icon);
                panel.removeAll(); // Limpiar el panel antes de agregar el GIF
                panel.add(gifLabel);
                panel.revalidate();
                panel.repaint();
                System.out.println("GIF asignado al JPanel sin escalar.");
            } else {
                // Cargar y escalar otras imágenes (PNG, JPG, etc.)
                BufferedImage image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
                    return;
                }

                // Escalar la imagen al tamaño del JPanel
                Image scaledImage = image.getScaledInstance(
                        panel.getWidth(),
                        panel.getHeight(),
                        Image.SCALE_SMOOTH
                );

                // Usar un JLabel interno para mostrar la imagen escalada en el JPanel
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                panel.removeAll(); // Limpiar el panel antes de agregar la imagen
                panel.add(imageLabel);
                panel.revalidate();
                panel.repaint();
                System.out.println("Imagen escalada y asignada al JPanel correctamente.");
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
    }
}