package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import utilidad.DAO;

public class PantallaLogin extends JFrame {

    private JTextField textEmail;
    private JPasswordField textPassword;
    private JButton botonIngresar;

    public PantallaLogin() {
        // Configuración inicial de la ventana
        setTitle("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(50, 50, 50));

        // Paneles para la organización de los componentes
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(50, 50, 50));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(300, 500, 300, 500));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20); // increased padding for larger components

        // Configuración de los componentes
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setForeground(Color.WHITE);
        labelEmail.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        textEmail = new JTextField(20);
        textEmail.setPreferredSize(new Dimension(300, 40)); // bigger size

        JLabel labelPassword = new JLabel("Contraseña:");
        labelPassword.setForeground(Color.WHITE);
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        textPassword = new JPasswordField(20);
        textPassword.setPreferredSize(new Dimension(300, 40)); // bigger size

        botonIngresar = createButton("Ingresar");
        JButton botonRegistrarse = createButton("Registrarse");

        // Añadiendo los componentes al panel
        c.gridx = 0; c.gridy = 0;
        contentPanel.add(labelEmail, c);

        c.gridx = 1; c.gridy = 0;
        contentPanel.add(textEmail, c);

        c.gridx = 0; c.gridy = 1;
        contentPanel.add(labelPassword, c);

        c.gridx = 1; c.gridy = 1;
        contentPanel.add(textPassword, c);

        c.gridx = 0; c.gridy = 2;
        contentPanel.add(botonIngresar, c);

        c.gridx = 1; c.gridy = 2;
        contentPanel.add(botonRegistrarse, c);

        // Añadir el panel a la ventana
        this.add(contentPanel, BorderLayout.CENTER);

        // Listeners para los botones
        botonIngresar.addActionListener(e -> {
            String email = textEmail.getText();
            String contraseña = new String(textPassword.getPassword());

            HashMap<String, Object> restricciones = new HashMap<>();
            restricciones.put("email", email);
            restricciones.put("contraseña", contraseña);

            try {
                if (DAO.consultar("usuario", new LinkedHashSet<>(), restricciones).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
                    ArrayList<Object> usuarios = DAO.consultar("usuario", new LinkedHashSet<>(), new HashMap<>());

                    // Mostrar la lista de usuarios existentes
                    System.out.println("Usuarios existentes:");
                    for (Object usuario : usuarios) {
                        System.out.println(usuario);
                    }
                } else {
                    new PantallaPrincipal().setVisible(true);
                    dispose();
                }
            } catch (SQLException ex) {
                System.err.println("Error al iniciar sesión: " + ex.getMessage());
            }
        });

        botonRegistrarse.addActionListener(e -> {
            new PantallaRegistro().setVisible(true);
            dispose();
        });
    }

    private JButton createButton(String text) {
        JButton button = new CustomButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 35)); // bigger font
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        return button;
    }

    private class CustomButton extends JButton {
        public CustomButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(new Color(200, 200, 200));
            } else {
                g.setColor(getBackground());
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
            super.paintComponent(g);
        }
    }
}

