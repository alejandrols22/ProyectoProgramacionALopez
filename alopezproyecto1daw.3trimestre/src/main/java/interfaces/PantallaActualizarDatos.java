package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.HashMap;

import enums.NivelActividad;
import enums.ObjetivoUsuario;
import utilidad.DAO;

public class PantallaActualizarDatos extends JFrame {

    JTextField telefonoField;
    private JTextField contrasenaField;
    JTextField pesoField;
    JTextField alturaField;
    JComboBox<Character> sexoField;
    JComboBox<NivelActividad> nivelActividadField;
    JComboBox<ObjetivoUsuario> objetivoField;
    JSpinner spinnerObjetivoCaloriasField;
    protected JButton actualizarButton;
    protected JButton botonVolver;

    public PantallaActualizarDatos() {
        // Configuración inicial de la ventana
        setTitle("Actualizar Datos");
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
        JLabel telefonoLabel = new JLabel("Telefono:");
        telefonoLabel.setForeground(Color.WHITE);
        telefonoLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        telefonoField = new JTextField(20);
        telefonoField.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setForeground(Color.WHITE);
        contrasenaLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        contrasenaField = new JTextField(20);
        contrasenaField.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel pesoLabel = new JLabel("Peso:");
        pesoLabel.setForeground(Color.WHITE);
        pesoLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        pesoField = new JTextField(20);
        pesoField.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel alturaLabel = new JLabel("Altura:");
        alturaLabel.setForeground(Color.WHITE);
        alturaLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        alturaField = new JTextField(20);
        alturaField.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel sexoLabel = new JLabel("Sexo:");
        sexoLabel.setForeground(Color.WHITE);
        sexoLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        sexoField = new JComboBox<>();
        sexoField.addItem('h');
        sexoField.addItem('m');
        sexoField.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel nivelActividadLabel = new JLabel("Nivel Actividad:");
        nivelActividadLabel.setForeground(Color.WHITE);
        nivelActividadLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        nivelActividadField = new JComboBox<>(NivelActividad.values());
        nivelActividadField.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel objetivoLabel = new JLabel("Objetivo:");
        objetivoLabel.setForeground(Color.WHITE);
        objetivoLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        objetivoField = new JComboBox<>(ObjetivoUsuario.values());
        objetivoField.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel objetivoCaloriasLabel = new JLabel("Objetivo Calorías:");
        objetivoCaloriasLabel.setForeground(Color.WHITE);
        objetivoCaloriasLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        spinnerObjetivoCaloriasField = new JSpinner(new SpinnerNumberModel(2000, 500, 4000, 100));
        spinnerObjetivoCaloriasField.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        actualizarButton = createButton("Actualizar");
        botonVolver = createButton("Volver");

        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        // Añadiendo los componentes al panel
        c.gridx = 0; c.gridy = 0;
        contentPanel.add(telefonoLabel, c);

        c.gridx = 1; c.gridy = 0;
        contentPanel.add(telefonoField, c);

        c.gridx = 0; c.gridy = 1;
        contentPanel.add(contrasenaLabel, c);

        c.gridx = 1; c.gridy = 1;
        contentPanel.add(contrasenaField, c);

        c.gridx = 0; c.gridy = 2;
        contentPanel.add(pesoLabel, c);

        c.gridx = 1; c.gridy = 2;
        contentPanel.add(pesoField, c);

        c.gridx = 0; c.gridy = 3;
        contentPanel.add(alturaLabel, c);

        c.gridx = 1; c.gridy = 3;
        contentPanel.add(alturaField, c);

        c.gridx = 0; c.gridy = 4;
        contentPanel.add(sexoLabel, c);

        c.gridx = 1; c.gridy = 4;
        contentPanel.add(sexoField, c);

        c.gridx = 0; c.gridy = 5;
        contentPanel.add(nivelActividadLabel, c);

        c.gridx = 1; c.gridy = 5;
        contentPanel.add(nivelActividadField, c);

        c.gridx = 0; c.gridy = 6;
        contentPanel.add(objetivoLabel, c);

        c.gridx = 1; c.gridy = 6;
        contentPanel.add(objetivoField, c);

        c.gridx = 0; c.gridy = 7;
        contentPanel.add(objetivoCaloriasLabel, c);

        c.gridx = 1; c.gridy = 7;
        contentPanel.add(spinnerObjetivoCaloriasField, c);

        c.gridx = 0; c.gridy = 8;
        contentPanel.add(actualizarButton, c);

        c.gridx = 1; c.gridy = 8;
        contentPanel.add(botonVolver, c);

        // Añadir el panel a la ventana
        this.add(contentPanel, BorderLayout.CENTER);

        // Listener para el botón de actualizar
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, Object> datosAModificar = new HashMap<>();
                datosAModificar.put("telefono", telefonoField.getText());
                datosAModificar.put("contrasena", contrasenaField.getText());
                datosAModificar.put("peso", pesoField.getText());
                datosAModificar.put("altura", alturaField.getText());
                datosAModificar.put("sexo", sexoField.getSelectedItem());
                datosAModificar.put("nivelActividad", nivelActividadField.getSelectedItem());
                datosAModificar.put("objetivo", objetivoField.getSelectedItem());
                datosAModificar.put("objetivoCalorias", spinnerObjetivoCaloriasField.getValue());

                HashMap<String, Object> restricciones = new HashMap<>();
                // Aquí tendrás que especificar las restricciones de la actualización.
                // restricciones.put("nombre_actual", "valor_actual");

                try {
                    DAO.actualizar("nombre_de_tu_tabla", datosAModificar, restricciones);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Listener para el botón de "Volver"
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                pantallaPrincipal.setVisible(true);
                dispose(); // Cerrar la ventana actual de registro
            }
        });
    }

    protected JButton createButton(String text) {
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

