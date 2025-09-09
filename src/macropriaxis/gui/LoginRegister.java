package macropriaxis.gui;

import macropriaxis.db.Usuario;
import macropriaxis.db.UsuarioDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegister extends JFrame {
    private JPanel panelMain;
    private CardLayout cardLayout;
    // Login
    private JTextField txtMatriculaLogin;
    private JTextField txtEmailLogin;
    private JButton btnLogin;
    private JButton btnGoRegister;
    private JButton btnGuest;
    private JLabel lblLoginMsg;
    // Register
    private JTextField txtNombre, txtApellidos, txtFechaNacimiento, txtCiudad, txtTelefono, txtDireccion, txtEmail, txtMatricula, txtCarrera;
    private JButton btnRegister;
    private JButton btnGoLogin;
    private JLabel lblRegisterMsg;

    public LoginRegister() {
        setTitle("Login / Registro de Usuario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(575, 575);
        setLocationRelativeTo(null);
        // Fondo de pantalla
        macropriaxis.util.ImageLoader.setFrameBackgroundImage(this, "/macropriaxis/media/MSN1.jpg");
        cardLayout = new CardLayout();
        panelMain = new JPanel(cardLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
            }
        };
        panelMain.setOpaque(false);
        panelMain.add(createLoginPanel(), "login");
        panelMain.add(createRegisterPanel(), "register");
        add(panelMain);
        cardLayout.show(panelMain, "login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setForeground(new Color(30,30,30));
        panel.add(lblMatricula, gbc);
        gbc.gridx = 1;
        txtMatriculaLogin = new JTextField();
        txtMatriculaLogin.setPreferredSize(new Dimension(300, 32));
        panel.add(txtMatriculaLogin, gbc);
        gbc.gridx = 0; gbc.gridy++;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(new Color(30,30,30));
        panel.add(lblEmail, gbc);
        gbc.gridx = 1;
        txtEmailLogin = new JTextField();
        txtEmailLogin.setPreferredSize(new Dimension(300, 32));
        panel.add(txtEmailLogin, gbc);
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBackground(new Color(100,200,255));
        btnLogin.setForeground(Color.BLACK);
        panel.add(btnLogin, gbc);
        gbc.gridy++;
        btnGoRegister = new JButton("Registrarse");
        btnGoRegister.setBackground(new Color(200,255,200));
        btnGoRegister.setForeground(Color.BLACK);
        panel.add(btnGoRegister, gbc);
        gbc.gridy++;
        btnGuest = new JButton("Acceder como Invitado");
        btnGuest.setBackground(new Color(255,200,100));
        btnGuest.setForeground(Color.BLACK);
        panel.add(btnGuest, gbc);
        gbc.gridy++;
        lblLoginMsg = new JLabel("", SwingConstants.CENTER);
        lblLoginMsg.setForeground(Color.RED);
        panel.add(lblLoginMsg, gbc);
        btnLogin.addActionListener(e -> loginAction());
        btnGoRegister.addActionListener(e -> cardLayout.show(panelMain, "register"));
        btnGuest.addActionListener(e -> guestLoginAction());
        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel[] labels = {
            new JLabel("Nombre:"), new JLabel("Apellidos:"), new JLabel("Fecha Nacimiento (YYYY-MM-DD):"),
            new JLabel("Ciudad:"), new JLabel("Teléfono:"), new JLabel("Dirección:"),
            new JLabel("Email:"), new JLabel("Matrícula:"), new JLabel("Carrera:")
        };
        JTextField[] fields = {
            txtNombre = new JTextField(), txtApellidos = new JTextField(), txtFechaNacimiento = new JTextField(),
            txtCiudad = new JTextField(), txtTelefono = new JTextField(), txtDireccion = new JTextField(),
            txtEmail = new JTextField(), txtMatricula = new JTextField(), txtCarrera = new JTextField()
        };
        for (int i = 0; i < labels.length; i++) {
            labels[i].setForeground(new Color(30,30,30));
            gbc.gridx = 0;
            panel.add(labels[i], gbc);
            gbc.gridx = 1;
            fields[i].setPreferredSize(new Dimension(300, 32));
            panel.add(fields[i], gbc);
            gbc.gridy++;
        }
        gbc.gridx = 0; gbc.gridwidth = 2;
        btnRegister = new JButton("Registrar");
        btnRegister.setBackground(new Color(255,220,120));
        btnRegister.setForeground(Color.BLACK);
        panel.add(btnRegister, gbc);
        gbc.gridy++;
        btnGoLogin = new JButton("Volver a Login");
        btnGoLogin.setBackground(new Color(200,255,200));
        btnGoLogin.setForeground(Color.BLACK);
        panel.add(btnGoLogin, gbc);
        gbc.gridy++;
        lblRegisterMsg = new JLabel("", SwingConstants.CENTER);
        lblRegisterMsg.setForeground(Color.RED);
        panel.add(lblRegisterMsg, gbc);
        btnRegister.addActionListener(e -> registerAction());
        btnGoLogin.addActionListener(e -> cardLayout.show(panelMain, "login"));
        return panel;
    }

    private void loginAction() {
        String matricula = txtMatriculaLogin.getText().trim();
        String email = txtEmailLogin.getText().trim();
        UsuarioDAO dao = new UsuarioDAO();
        boolean found = false;
        if (!matricula.isEmpty()) {
            for (Usuario u : dao.buscarPorNombre("") ) {
                if (u.getMatricula().equals(matricula) || u.getEmail().equalsIgnoreCase(email)) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            lblLoginMsg.setForeground(Color.GREEN);
            lblLoginMsg.setText("¡Login exitoso!");
            // Guardar usuario logueado en SesionActual
            for (Usuario u : dao.buscarPorNombre("")) {
                if (u.getMatricula().equals(matricula) || u.getEmail().equalsIgnoreCase(email)) {
                    macropriaxis.db.SesionActual.setUsuario(u);
                    break;
                }
            }
            // Abrir ventana principal
            SwingUtilities.invokeLater(() -> {
                new macropriaxis.IndexGigas().setVisible(true);
                this.dispose();
            });
        } else {
            lblLoginMsg.setForeground(Color.RED);
            lblLoginMsg.setText("Usuario no encontrado");
        }
    }

    private void registerAction() {
        Usuario usuario = new Usuario(
            txtNombre.getText().trim(),
            txtApellidos.getText().trim(),
            txtFechaNacimiento.getText().trim(),
            txtCiudad.getText().trim(),
            txtTelefono.getText().trim(),
            txtDireccion.getText().trim(),
            txtEmail.getText().trim(),
            txtMatricula.getText().trim(),
            txtCarrera.getText().trim()
        );
        UsuarioDAO dao = new UsuarioDAO();
        // Validar que matrícula o email no existan
        boolean exists = false;
        for (Usuario u : dao.buscarPorNombre("")) {
            if (u.getMatricula().equals(usuario.getMatricula()) || u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            lblRegisterMsg.setForeground(Color.RED);
            lblRegisterMsg.setText("Matrícula o email ya registrados");
        } else {
            boolean ok = dao.insertarUsuario(usuario);
            if (ok) {
                lblRegisterMsg.setForeground(Color.GREEN);
                lblRegisterMsg.setText("¡Registro exitoso! Ahora puedes iniciar sesión.");
            } else {
                lblRegisterMsg.setForeground(Color.RED);
                lblRegisterMsg.setText("Error al registrar usuario");
            }
        }
    }

    private void guestLoginAction() {
        // Crear un usuario invitado para acceso offline
        Usuario usuarioInvitado = new Usuario(
            "Invitado",
            "Usuario",
            "2024-01-01",
            "Offline",
            "N/A",
            "N/A",
            "invitado@offline.local",
            "GUEST001",
            "Acceso Temporal"
        );
        
        // Establecer el usuario invitado en la sesión actual
        macropriaxis.db.SesionActual.setUsuario(usuarioInvitado);
        
        // Mostrar mensaje de confirmación
        lblLoginMsg.setForeground(Color.BLUE);
        lblLoginMsg.setText("Acceso como invitado - Modo offline activado");
        
        // Abrir ventana principal después de un breve delay
        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(1000); // Pequeño delay para mostrar el mensaje
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            new macropriaxis.IndexGigas().setVisible(true);
            this.dispose();
        });
    }
} 