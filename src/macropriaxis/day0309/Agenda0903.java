/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package macropriaxis.day0309;

import macropriaxis.db.AgendaDAO;
import macropriaxis.db.AgendaDAO.Agenda;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import macropriaxis.db.SesionActual;
import macropriaxis.db.Usuario;


/**
 *
 * @author Carlo
 */
public class Agenda0903 extends javax.swing.JFrame {

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
                            
                            // Obtiene todas las agendas de la base de datos
                            List<Agenda> agendas = AgendaDAO.obtenerTodasLasAgendas();
                            // Obtiene las anotaciones específicas de la entrada seleccionada
                            String anotaciones = agendas.get(row).getAnotaciones();
                            
                            // Crea y muestra la ventana de anotaciones con los detalles
                            Anotacion0903 anotacion = new Anotacion0903();
                            // Pasa los detalles a la ventana de anotaciones
                            anotacion.mostrarDetalles(asunto, fecha, hora, anotaciones);
                            // Hace visible la ventana de anotaciones
                            anotacion.setVisible(true);
                            
                        } catch (Exception e) {
                            // Muestra un mensaje de error si hay problemas al cargar los detalles
                            JOptionPane.showMessageDialog(null, "Error al cargar los detalles: " + e.getMessage());
                        }
                    }
                }
            }
        });

        // Agregar botones y campo de búsqueda
        jButtonModificar = new javax.swing.JButton("Modificar");
        jButtonEliminar = new javax.swing.JButton("Eliminar");
        jButtonBuscar = new javax.swing.JButton("Buscar");
        jTextFieldBuscar = new javax.swing.JTextField(15);
        // Añadir listeners
        jButtonModificar.addActionListener(evt -> modificarAgenda());
        jButtonEliminar.addActionListener(evt -> eliminarAgenda());
        jButtonBuscar.addActionListener(evt -> buscarAgenda());
        // Añadir a la interfaz (al panel principal)
        jPanel1.add(jButtonModificar);
        jPanel1.add(jButtonEliminar);
        jPanel1.add(jTextFieldBuscar);
        jPanel1.add(jButtonBuscar);
        // Permitir seleccionar fila para editar
        jTable1.getSelectionModel().addListSelectionListener(e -> cargarDatosSeleccionados());

        // Mostrar el nombre del usuario logueado
        jLabelUsuario = new javax.swing.JLabel();
        Usuario usuario = SesionActual.getUsuario();
        if (usuario != null) {
            jLabelUsuario.setText("Usuario: " + usuario.getNombre() + " " + usuario.getApellidos());
        } else {
            jLabelUsuario.setText("Usuario: -");
        }
        jLabelUsuario.setFont(new java.awt.Font("Consolas", 1, 16));
        jLabelUsuario.setForeground(new java.awt.Color(255,255,255));
        jPanel1.add(jLabelUsuario);
        // Mejorar disposición de botones y campos
        jButton1.setToolTipText("Añadir nueva anotación");
        jButtonModificar.setToolTipText("Modificar la anotación seleccionada");
        jButtonEliminar.setToolTipText("Eliminar la anotación seleccionada");
        jButtonBuscar.setToolTipText("Buscar por asunto");
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
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JLabel jLabelUsuario;
    // End of variables declaration//GEN-END:variables

    private void configurarTabla() {
        // Obtiene el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        // Limpia todas las filas existentes en la tabla
        modelo.setRowCount(0);
    }
    
    private void actualizarTabla() {
        try {
            Usuario usuario = SesionActual.getUsuario();
            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "No hay usuario logueado");
                return;
            }
            // Obtiene solo las agendas del usuario logueado
            List<Agenda> agendas = AgendaDAO.obtenerAgendasPorUsuario(usuario.getId());
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            for (Agenda agenda : agendas) {
                modelo.addRow(new Object[]{
                    agenda.getAsunto(),
                    new SimpleDateFormat("dd-MM-yyyy").format(agenda.getFecha()),
                    new SimpleDateFormat("HH:mm").format(agenda.getHora())
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las agendas: " + e.getMessage());
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
        if (jTextField1.getText().trim().isEmpty() || 
            jTextField2.getText().trim().isEmpty() || 
            jTextField3.getText().trim().isEmpty() || 
            jTextArea1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }
        try {
            String fechaStr = jTextField2.getText().trim().replace("-", "/");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            java.util.Date utilDate = dateFormat.parse(fechaStr);
            Date sqlDate = new Date(utilDate.getTime());
            String horaStr = jTextField3.getText().trim();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            timeFormat.setLenient(false);
            java.util.Date utilTime = timeFormat.parse(horaStr);
            Time sqlTime = new Time(utilTime.getTime());
            Usuario usuario = SesionActual.getUsuario();
            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "No hay usuario logueado");
                return;
            }
            // Guardar la nueva entrada con el usuario_id
            AgendaDAO.insertarAgenda(
                usuario.getId(),
                jTextField1.getText().trim(),
                sqlDate,
                sqlTime,
                jTextArea1.getText().trim()
            );
            limpiarCampos();
            actualizarTabla();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha u hora incorrecto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la agenda: " + e.getMessage());
        }
    }

    // Guardar el ID de la agenda seleccionada
    private Integer agendaSeleccionadaId = null;

    private void cargarDatosSeleccionados() {
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            try {
                Usuario usuario = SesionActual.getUsuario();
                if (usuario == null) return;
                List<Agenda> agendas = AgendaDAO.obtenerAgendasPorUsuario(usuario.getId());
                Agenda ag = agendas.get(row);
                agendaSeleccionadaId = ag.getId();
                jTextField1.setText(ag.getAsunto());
                jTextField2.setText(new SimpleDateFormat("dd/MM/yyyy").format(ag.getFecha()));
                jTextField3.setText(new SimpleDateFormat("HH:mm").format(ag.getHora()));
                jTextArea1.setText(ag.getAnotaciones());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage());
            }
        }
    }

    private void modificarAgenda() {
        if (agendaSeleccionadaId == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una anotación para modificar");
            return;
        }
        try {
            String fechaStr = jTextField2.getText().trim().replace("-", "/");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            java.util.Date utilDate = dateFormat.parse(fechaStr);
            Date sqlDate = new Date(utilDate.getTime());
            String horaStr = jTextField3.getText().trim();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            timeFormat.setLenient(false);
            java.util.Date utilTime = timeFormat.parse(horaStr);
            Time sqlTime = new Time(utilTime.getTime());
            Usuario usuario = SesionActual.getUsuario();
            if (usuario == null) return;
            AgendaDAO.modificarAgenda(agendaSeleccionadaId, usuario.getId(), jTextField1.getText().trim(), sqlDate, sqlTime, jTextArea1.getText().trim());
            limpiarCampos();
            actualizarTabla();
            agendaSeleccionadaId = null;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha u hora incorrecto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la agenda: " + e.getMessage());
        }
    }

    private void eliminarAgenda() {
        if (agendaSeleccionadaId == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una anotación para eliminar");
            return;
        }
        try {
            AgendaDAO.eliminarAgenda(agendaSeleccionadaId);
            limpiarCampos();
            actualizarTabla();
            agendaSeleccionadaId = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la agenda: " + e.getMessage());
        }
    }

    private void buscarAgenda() {
        String texto = jTextFieldBuscar.getText().trim();
        try {
            Usuario usuario = SesionActual.getUsuario();
            if (usuario == null) return;
            List<Agenda> agendas = AgendaDAO.obtenerAgendasPorUsuario(usuario.getId());
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            for (Agenda agenda : agendas) {
                if (agenda.getAsunto().toLowerCase().contains(texto.toLowerCase())) {
                    modelo.addRow(new Object[]{
                        agenda.getAsunto(),
                        new SimpleDateFormat("dd-MM-yyyy").format(agenda.getFecha()),
                        new SimpleDateFormat("HH:mm").format(agenda.getHora())
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage());
        }
    }
}
