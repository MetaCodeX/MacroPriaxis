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
     * Este método es útil para mostrar imágenes en etiquetas individuales.
     *
     * @param label     El JLabel al que se asignará la imagen.
     * @param imagePath La ruta de la imagen (relativa al classpath).
     */
    public static void setImageToLabel(JLabel label, String imagePath) {
        // Registra el intento de carga de la imagen
        System.out.println("Intentando cargar imagen desde: " + imagePath);

        // Obtiene la URL del recurso usando el ClassLoader
        URL imageUrl = ImageLoader.class.getResource(imagePath);
        if (imageUrl == null) {
            // Si no se encuentra la imagen, registra el error y termina
            System.err.println("Error: No se pudo encontrar el recurso en: " + imagePath);
            return;
        }
        // Registra que la imagen fue encontrada exitosamente
        System.out.println("Imagen encontrada en: " + imageUrl.toString());

        // Intenta cargar la imagen
        try {
            if (imagePath.toLowerCase().endsWith(".gif")) {
                // Manejo especial para archivos GIF para mantener la animación
                ImageIcon icon = new ImageIcon(imageUrl);
                if (icon.getImage() == null) {
                    // Si no se puede cargar el GIF, registra el error
                    System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
                    return;
                }
                // Asigna el GIF al JLabel sin escalarlo para preservar la animación
                label.setIcon(icon);
                System.out.println("GIF asignado al JLabel sin escalar.");
            } else {
                // Para otros formatos de imagen (PNG, JPG, etc.)
                BufferedImage image = ImageIO.read(imageUrl);
                if (image == null) {
                    // Si no se puede cargar la imagen, registra el error
                    System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
                    return;
                }

                // Escala la imagen al tamaño del JLabel manteniendo la calidad
                Image scaledImage = image.getScaledInstance(
                        label.getWidth(),
                        label.getHeight(),
                        Image.SCALE_SMOOTH
                );

                // Asigna la imagen escalada al JLabel
                label.setIcon(new ImageIcon(scaledImage));
                System.out.println("Imagen escalada y asignada al JLabel correctamente.");
            }
        } catch (IOException e) {
            // Captura y registra cualquier error durante la carga de la imagen
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    /**
     * Carga una imagen y la asigna a un JPanel, escalándola si es necesario.
     * Este método es útil para establecer imágenes de fondo en paneles.
     *
     * @param panel     El JPanel al que se asignará la imagen.
     * @param imagePath La ruta de la imagen (relativa al classpath).
     */
    public static void setImageToPanel(JPanel panel, String imagePath) {
        // Registra el intento de carga de la imagen
        System.out.println("Intentando cargar imagen desde: " + imagePath);

        // Obtiene la URL del recurso usando el ClassLoader
        URL imageUrl = ImageLoader.class.getResource(imagePath);
        if (imageUrl == null) {
            // Si no se encuentra la imagen, registra el error y termina
            System.err.println("Error: No se pudo encontrar el recurso en: " + imagePath);
            return;
        }
        // Registra que la imagen fue encontrada exitosamente
        System.out.println("Imagen encontrada en: " + imageUrl.toString());

        // Intenta cargar la imagen
        try {
            if (imagePath.toLowerCase().endsWith(".gif")) {
                // Manejo especial para archivos GIF para mantener la animación
                ImageIcon icon = new ImageIcon(imageUrl);
                if (icon.getImage() == null) {
                    // Si no se puede cargar el GIF, registra el error
                    System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
                    return;
                }

                // Crea un JLabel para mostrar el GIF y lo añade al panel
                JLabel gifLabel = new JLabel(icon);
                panel.removeAll(); // Limpia el panel antes de agregar el GIF
                panel.setOpaque(false); // Hace el panel transparente
                panel.add(gifLabel);
                panel.revalidate();
                panel.repaint();
                System.out.println("GIF asignado al JPanel sin escalar.");
            } else {
                // Para otros formatos de imagen (PNG, JPG, etc.)
                BufferedImage image = ImageIO.read(imageUrl);
                if (image == null) {
                    // Si no se puede cargar la imagen, registra el error
                    System.err.println("Error: No se pudo cargar la imagen desde: " + imagePath);
                    return;
                }

                // Escala la imagen al tamaño del panel manteniendo la calidad
                Image scaledImage = image.getScaledInstance(
                        panel.getWidth(),
                        panel.getHeight(),
                        Image.SCALE_SMOOTH
                );

                // Crea un JLabel con la imagen escalada y lo añade al panel
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                panel.removeAll(); // Limpia el panel antes de agregar la imagen
                panel.setOpaque(false); // Hace el panel transparente
                panel.add(imageLabel);
                panel.revalidate();
                panel.repaint();
                System.out.println("Imagen escalada y asignada al JPanel correctamente.");
            }
        } catch (IOException e) {
            // Captura y registra cualquier error durante la carga de la imagen
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    /**
     * Panel optimizado para manejar imágenes de fondo con mejor rendimiento
     */
    private static class OptimizedBackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;
        private BufferedImage scaledImage;
        private int lastWidth;
        private int lastHeight;

        public OptimizedBackgroundPanel(String imagePath) {
            setOpaque(false);
            loadImage(imagePath);
        }

        private void loadImage(String imagePath) {
            try {
                URL imageUrl = ImageLoader.class.getResource(imagePath);
                if (imageUrl != null) {
                    backgroundImage = ImageIO.read(imageUrl);
                }
            } catch (IOException e) {
                System.err.println("Error cargando imagen de fondo: " + e.getMessage());
            }
        }

        private void updateScaledImage() {
            int currentWidth = getWidth();
            int currentHeight = getHeight();

            if (scaledImage == null || currentWidth != lastWidth || currentHeight != lastHeight) {
                if (backgroundImage != null && currentWidth > 0 && currentHeight > 0) {
                    scaledImage = new BufferedImage(currentWidth, currentHeight, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2d = scaledImage.createGraphics();
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.drawImage(backgroundImage, 0, 0, currentWidth, currentHeight, null);
                    g2d.dispose();
                    
                    lastWidth = currentWidth;
                    lastHeight = currentHeight;
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            updateScaledImage();
            if (scaledImage != null) {
                g.drawImage(scaledImage, 0, 0, null);
            }
        }
    }

    /**
     * Configura un JFrame con una imagen de fondo manteniendo los componentes visibles.
     * Versión optimizada para mejor rendimiento.
     * 
     * @param frame El JFrame a configurar
     * @param imagePath La ruta de la imagen
     */
    public static void setFrameBackgroundImage(JFrame frame, String imagePath) {
        System.out.println("Configurando imagen de fondo optimizada para JFrame: " + imagePath);
        
        Container contentPane = frame.getContentPane();
        
        // Verifica si el contentPane usa GroupLayout
        if (contentPane.getLayout() instanceof GroupLayout) {
            // Para GroupLayout, creamos un panel intermedio
            OptimizedBackgroundPanel backgroundPanel = new OptimizedBackgroundPanel(imagePath);
            backgroundPanel.setLayout(new BorderLayout());
            
            // Preservamos el contentPane original con su GroupLayout
            if (contentPane instanceof JPanel) {
                JPanel originalPanel = (JPanel) contentPane;
                originalPanel.setOpaque(false);
                backgroundPanel.add(originalPanel, BorderLayout.CENTER);
            }
            
            frame.setContentPane(backgroundPanel);
        } else {
            // Para otros layouts, mantenemos la lógica original optimizada
            OptimizedBackgroundPanel backgroundPanel = new OptimizedBackgroundPanel(imagePath);
            LayoutManager originalLayout = contentPane.getLayout();
            backgroundPanel.setLayout(originalLayout != null ? originalLayout : new BorderLayout());
            
            Component[] components = contentPane.getComponents();
            for (Component comp : components) {
                if (originalLayout instanceof BorderLayout) {
                    backgroundPanel.add(comp, ((BorderLayout)originalLayout).getConstraints(comp));
                } else if (originalLayout instanceof GridBagLayout) {
                    backgroundPanel.add(comp, ((GridBagLayout)originalLayout).getConstraints(comp));
                } else {
                    backgroundPanel.add(comp);
                }
            }
            
            frame.setContentPane(backgroundPanel);
        }
        
        frame.revalidate();
    }
}