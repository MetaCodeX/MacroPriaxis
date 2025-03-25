/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macropriaxis.serpent;
import macropriaxis.IndexGigas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class serpent extends JPanel implements ActionListener, KeyListener {
    private final int ANCHO =1200;
    private final int ALTO = 800;
    private final int UNIDAD_TAMANO = 20;
    private final int UNIDADES_JUEGO = (ANCHO * ALTO) / UNIDAD_TAMANO;
    private final int DELAY = 160;
    
    private final ArrayList<Integer> serpienteX = new ArrayList<>();
    private final ArrayList<Integer> serpienteY = new ArrayList<>();
    
    private int comidaX;
    private int comidaY;
    private int longitudSerpiente = 1;
    private char direccion = 'D';
    private boolean enJuego = false;
    private int puntaje = 0;
    
    private Timer timer;
    private final Random random;
    
    public serpent() {
        random = new Random();
        this.setPreferredSize(new Dimension(ANCHO, ALTO));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
        iniciarJuego();
    }
    
    private void iniciarJuego() {
        serpienteX.clear();
        serpienteY.clear();
        longitudSerpiente = 120;
        direccion = 'D';
        puntaje = 0;
        
        // Posición inicial de la serpiente
        for (int i = 0; i < longitudSerpiente; i++) {
            serpienteX.add(ANCHO/2 - i * UNIDAD_TAMANO);
            serpienteY.add(ALTO/2);
        }
        
        generarComida();
        enJuego = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void generarComida() {
        comidaX = random.nextInt(ANCHO/UNIDAD_TAMANO) * UNIDAD_TAMANO;
        comidaY = random.nextInt(ALTO/UNIDAD_TAMANO) * UNIDAD_TAMANO;
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujar(g);
    }
    
    private void dibujar(Graphics g) {
        if (enJuego) {
            // Dibujar comida
            g.setColor(Color.RED);
            g.fillRect(comidaX, comidaY, UNIDAD_TAMANO, UNIDAD_TAMANO);
            
            // Dibujar serpiente
            for (int i = 0; i < serpienteX.size(); i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(serpienteX.get(i), serpienteY.get(i), 
                          UNIDAD_TAMANO, UNIDAD_TAMANO);
            }
            
            // Mostrar puntaje
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Puntaje: " + puntaje, 10, 30);
        } else {
            gameOver(g);
        }
    }
    
    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        String gameOverText = "¡Juego Terminado!";
        String puntajeText = "Puntaje Final: " + puntaje;
        String reiniciarText = "Presiona ESPACIO para reiniciar";
        
        g.drawString(gameOverText, 
                    (ANCHO - metrics.stringWidth(gameOverText))/2, ALTO/2 - 50);
        g.drawString(puntajeText, 
                    (ANCHO - metrics.stringWidth(puntajeText))/2, ALTO/2);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        metrics = getFontMetrics(g.getFont());
        g.drawString(reiniciarText, 
                    (ANCHO - metrics.stringWidth(reiniciarText))/2, ALTO/2 + 50);
    }
    
    private void mover() {
        for (int i = serpienteX.size() - 1; i > 0; i--) {
            serpienteX.set(i, serpienteX.get(i - 1));
            serpienteY.set(i, serpienteY.get(i - 1));
        }
        
        switch (direccion) {
            case 'U' -> {
                serpienteY.set(0, serpienteY.get(0) - UNIDAD_TAMANO);
                if (serpienteY.get(0) < 0) {
                    serpienteY.set(0, ALTO - UNIDAD_TAMANO);
                }
            }
            case 'D' -> {
                serpienteY.set(0, serpienteY.get(0) + UNIDAD_TAMANO);
                if (serpienteY.get(0) >= ALTO) {
                    serpienteY.set(0, 0);
                }
            }
            case 'L' -> {
                serpienteX.set(0, serpienteX.get(0) - UNIDAD_TAMANO);
                if (serpienteX.get(0) < 0) {
                    serpienteX.set(0, ANCHO - UNIDAD_TAMANO);
                }
            }
            case 'R' -> {
                serpienteX.set(0, serpienteX.get(0) + UNIDAD_TAMANO);
                if (serpienteX.get(0) >= ANCHO) {
                    serpienteX.set(0, 0);
                }
            }
        }
    }
    
    private void verificarComida() {
        if (serpienteX.get(0) == comidaX && serpienteY.get(0) == comidaY) {
            serpienteX.add(serpienteX.get(serpienteX.size() - 1));
            serpienteY.add(serpienteY.get(serpienteY.size() - 1));
            longitudSerpiente++;
            puntaje += 10;
            generarComida();
        }
    }
    
    private void verificarColisiones() {
        // Solo verificar colisión con el cuerpo
        for (int i = 1; i < serpienteX.size(); i++) {
            if (serpienteX.get(0).equals(serpienteX.get(i)) && 
                serpienteY.get(0).equals(serpienteY.get(i))) {
                enJuego = false;
                break;
            }
        }
        
        if (!enJuego) {
            timer.stop();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (enJuego) {
            mover();
            verificarComida();
            verificarColisiones();
        }
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (direccion != 'R') direccion = 'L';
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (direccion != 'L') direccion = 'R';
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (direccion != 'D') direccion = 'U';
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (direccion != 'U') direccion = 'D';
                break;
            case KeyEvent.VK_SPACE:
                if (!enJuego) iniciarJuego();
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new serpent());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
