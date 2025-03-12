/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package macropriaxis.day0903;

import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlo
 */
public class Agenda0903 extends javax.swing.JFrame {
    private static final String JSON_FILE = "src/macropriaxis/offline/agenda.json";

    /**
     * Creates new form Agenda0903
     */
    public Agenda0903() {
        // Configurar el Look and Feel antes de inicializar componentes
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Si falla, continuará con el Look and Feel por defecto
            System.out.println("No se pudo establecer el Look and Feel Nimbus");
        }

        // Inicializa los componentes de la interfaz gráfica generados por NetBeans
        initComponents();
        // Hace el panel principal transparente para mostrar la imagen de fondo
        jPanel1.setOpaque(false);
        // Establece la imagen de fondo de la ventana
        macropriaxis.util.ImageLoader.setFrameBackgroundImage(this, "/macropriaxis/media/WIN7.png");
        // Centra la ventana en la pantalla
        this.setLocationRelativeTo(null);
        // Configura la tabla con sus columnas y propiedades
        configurarTabla();
        // Carga los datos existentes en la tabla
        actualizarTabla();
        
        // Configura el botón Añadir con un listener lambda para guardar nuevas entradas
        jButton1.addActionListener(evt -> guardarAgenda());
        
        // Configura el botón de menú para regresar a la ventana principal
        jButton2.addActionListener(evt -> {
            // Crea y muestra la ventana principal
            new Main0903().setVisible(true);
            // Cierra la ventana actual de agenda
            this.dispose();
        });
        
        // Añade un listener para detectar doble clic en las filas de la tabla
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Verifica si fue un doble clic
                if (evt.getClickCount() == 2) {
                    // Obtiene la fila seleccionada
                    int row = jTable1.getSelectedRow();
                    if (row != -1) {
                        try {
                            // Obtiene los datos de la fila seleccionada
                            String asunto = jTable1.getValueAt(row, 0).toString();
                            String fecha = jTable1.getValueAt(row, 1).toString();
                            String hora = jTable1.getValueAt(row, 2).toString();
                            
                            // Obtener anotaciones del JSON
                            JSONParser parser = new JSONParser();
                            String anotaciones = "";
                            
                            try (FileReader reader = new FileReader(JSON_FILE)) {
                                JSONObject jsonData = (JSONObject) parser.parse(reader);
                                JSONArray agendas = (JSONArray) jsonData.get("Agenda");
                                if (agendas != null && row >= 0 && row < agendas.size()) {
                                    JSONObject agenda = (JSONObject) agendas.get(row);
                                    anotaciones = (String) agenda.get("Anotaciones");
                                }
                            }
                            
                            // Crea y muestra la ventana de anotaciones con los detalles
                            Anotacion0903 anotacion = new Anotacion0903();
                            // Pasa los detalles a la ventana de anotaciones
                            anotacion.mostrarDetalles(asunto, fecha, hora, anotaciones);
                            // Hace visible la ventana de anotaciones
                            anotacion.setVisible(true);
                            
                        } catch (Exception e) {
                            // Muestra un mensaje de error si hay problemas al cargar los detalles
                            JOptionPane.showMessageDialog(null, "Error al cargar los detalles: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agenda // Macropriaxis");

        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Consolas", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("AGENDA");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Asunto", "Fecha", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setToolTipText("Asunto");

        jTextField2.setToolTipText("Fecha");

        jTextField3.setToolTipText("Hora");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Asunto:");

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha:");

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hora:");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Consolas", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contexto:");

        jButton1.setText("Añadir");

        jButton2.setText("Menu");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton2)
                        .addGap(171, 179, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Agenda0903.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agenda0903.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agenda0903.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agenda0903.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Agenda0903().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void configurarTabla() {
        // Obtiene el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        // Limpia todas las filas existentes en la tabla
        modelo.setRowCount(0);
    }
    
    private void actualizarTabla() {
        try {
            JSONParser parser = new JSONParser();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            
            // Crear el archivo JSON si no existe
            java.io.File file = new java.io.File(JSON_FILE);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                JSONObject jsonData = new JSONObject();
                jsonData.put("Agenda", new JSONArray());
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(jsonData.toJSONString());
                }
                return; // Retorna ya que no hay datos que mostrar
            }
            
            try (FileReader reader = new FileReader(JSON_FILE)) {
                JSONObject jsonData = (JSONObject) parser.parse(reader);
                JSONArray agendas = (JSONArray) jsonData.get("Agenda");
                
                // Si agendas es null, inicializar como un array vacío
                if (agendas == null) {
                    agendas = new JSONArray();
                    jsonData.put("Agenda", agendas);
                    try (FileWriter writer = new FileWriter(JSON_FILE)) {
                        writer.write(jsonData.toJSONString());
                    }
                    return; // Retorna ya que no hay datos que mostrar
                }
                
                for (Object obj : agendas) {
                    JSONObject agenda = (JSONObject) obj;
                modelo.addRow(new Object[]{
                        agenda.get("Asunto"),
                        agenda.get("Fecha"),
                        agenda.get("Hora")
                    });
                }
            }
        } catch (Exception e) {
            // Muestra un mensaje de error si hay problemas al cargar los datos
            JOptionPane.showMessageDialog(this, "Error al cargar las agendas: " + e.getMessage());
            e.printStackTrace(); // Para ver el error detallado en la consola
        }
    }
    
    private void limpiarCampos() {
        // Limpia todos los campos de entrada
        jTextField1.setText(""); // Campo de asunto
        jTextField2.setText(""); // Campo de fecha
        jTextField3.setText(""); // Campo de hora
        jTextArea1.setText("");  // Campo de anotaciones
    }
    
    private void guardarAgenda() {
        // Validar que ningún campo esté vacío
        if (jTextField1.getText().trim().isEmpty() || 
            jTextField2.getText().trim().isEmpty() || 
            jTextField3.getText().trim().isEmpty() || 
            jTextArea1.getText().trim().isEmpty()) {
            // Muestra mensaje de error si algún campo está vacío
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }
        
        try {
            // Procesa y valida el formato de la fecha (dd/MM/yyyy)
            String fechaStr = jTextField2.getText().trim().replace("-", "/");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false); // No permite fechas inválidas
            dateFormat.parse(fechaStr); // Solo para validar
            
            // Procesa y valida el formato de la hora (HH:mm)
            String horaStr = jTextField3.getText().trim();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            timeFormat.setLenient(false); // No permite horas inválidas
            timeFormat.parse(horaStr); // Solo para validar
            
            // Crear objeto JSON
            JSONObject agenda = new JSONObject();
            agenda.put("ID", System.currentTimeMillis());
            agenda.put("Asunto", jTextField1.getText().trim());
            agenda.put("Fecha", fechaStr);
            agenda.put("Hora", horaStr);
            agenda.put("Anotaciones", jTextArea1.getText().trim());
            
            // Leer archivo existente
            JSONParser parser = new JSONParser();
            JSONObject jsonData;
            try (FileReader reader = new FileReader(JSON_FILE)) {
                jsonData = (JSONObject) parser.parse(reader);
            } catch (Exception e) {
                jsonData = new JSONObject();
                jsonData.put("Agenda", new JSONArray());
            }
            
            // Añadir nueva agenda
            JSONArray agendas = (JSONArray) jsonData.get("Agenda");
            if (agendas == null) {
                agendas = new JSONArray();
                jsonData.put("Agenda", agendas);
            }
            agendas.add(agenda);
            
            // Guardar archivo
            try (FileWriter file = new FileWriter(JSON_FILE)) {
                file.write(jsonData.toJSONString());
                file.flush();
            }
            
            // Limpia los campos después de guardar
            limpiarCampos();
            // Actualiza la tabla con la nueva entrada
            actualizarTabla();
            
            // Muestra mensaje de éxito
            JOptionPane.showMessageDialog(this, "Agenda guardada exitosamente");
            
        } catch (ParseException e) {
            // Muestra error si el formato de fecha u hora es incorrecto
            JOptionPane.showMessageDialog(this, "Error en el formato de fecha u hora: " + e.getMessage());
        } catch (IOException e) {
            // Muestra error si hay problemas al guardar en el archivo
            JOptionPane.showMessageDialog(this, "Error al guardar la agenda: " + e.getMessage());
        } catch (Exception e) {
            // Muestra error si hay problemas al guardar en el archivo
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
