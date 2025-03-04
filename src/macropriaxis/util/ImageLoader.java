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
     * Configura un JFrame con una imagen de fondo manteniendo los componentes visibles.
     * Este método es especialmente útil para crear interfaces con fondos personalizados.
     * 
     * @param frame El JFrame a configurar
     * @param imagePath La ruta de la imagen
     */
    public static void setFrameBackgroundImage(JFrame frame, String imagePath) {
        // Registra el inicio de la configuración
        System.out.println("Configurando imagen de fondo para JFrame: " + imagePath);
        
        // Obtiene el panel de contenido actual del frame
        Container contentPane = frame.getContentPane();
        
        // Crea un nuevo panel con la imagen de fondo personalizada
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Sobrescribe el método de pintado para dibujar la imagen de fondo
                super.paintComponent(g);
                try {
                    // Intenta cargar y dibujar la imagen
                    URL imageUrl = ImageLoader.class.getResource(imagePath);
                    if (imageUrl != null) {
                        Image img = ImageIO.read(imageUrl);
                        if (img != null) {
                            // Escala y dibuja la imagen al tamaño del panel
                            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                        }
                    }
                } catch (IOException e) {
                    // Registra cualquier error durante la carga de la imagen
                    System.err.println("Error cargando imagen de fondo: " + e.getMessage());
                }
            }
        };
        
        // Maneja la preservación de componentes y sus constraints
        if (contentPane != null) {
            // Obtiene el layout actual
            LayoutManager originalLayout = contentPane.getLayout();
            
            if (originalLayout instanceof GroupLayout) {
                // Manejo especial para GroupLayout debido a sus restricciones
                if (contentPane instanceof JPanel) {
                    // Hace el panel original transparente
                    JPanel originalPanel = (JPanel) contentPane;
                    originalPanel.setOpaque(false);
                    
                    // Configura el nuevo panel de fondo
                    backgroundPanel.setLayout(new BorderLayout());
                    backgroundPanel.setOpaque(false);
                    
                    // Mantiene el panel original intacto dentro del nuevo panel
                    backgroundPanel.add(originalPanel, BorderLayout.CENTER);
                }
            } else {
                // Manejo para otros tipos de layouts
                if (originalLayout != null) {
                    // Copia el layout original al nuevo panel
                    backgroundPanel.setLayout(originalLayout);
                    
                    // Manejo específico según el tipo de layout
                    if (originalLayout instanceof BorderLayout) {
                        // Preserva las constraints del BorderLayout
                        for (Component comp : contentPane.getComponents()) {
                            Object constraints = ((BorderLayout)originalLayout).getConstraints(comp);
                            backgroundPanel.add(comp, constraints);
                        }
                    } else if (originalLayout instanceof GridBagLayout) {
                        // Preserva las constraints del GridBagLayout
                        GridBagLayout gridBag = (GridBagLayout)originalLayout;
                        for (Component comp : contentPane.getComponents()) {
                            backgroundPanel.add(comp, gridBag.getConstraints(comp));
                        }
                    } else {
                        // Para otros layouts, añade los componentes sin constraints especiales
                        for (Component comp : contentPane.getComponents()) {
                            backgroundPanel.add(comp);
                        }
                    }
                } else {
                    // Si no hay layout, usa BorderLayout por defecto
                    backgroundPanel.setLayout(new BorderLayout());
                    for (Component comp : contentPane.getComponents()) {
                        backgroundPanel.add(comp);
                    }
                }
            }
        }
        
        // Establece el nuevo panel como contenido del frame
        frame.setContentPane(backgroundPanel);
        
        // Actualiza la visualización del frame
        frame.revalidate();
        frame.repaint();
    }
}