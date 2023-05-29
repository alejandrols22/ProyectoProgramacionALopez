package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import enums.NivelActividad;
import enums.ObjetivoUsuario;

public class PantallaRegistro extends JFrame {

    JTextField textEmail;
    private JTextField textNombre;
    JTextField textTelefono;
    JPasswordField textPassword;
    private JTextField textEdad;
    JTextField textPeso;
    JTextField textAltura;
    JComboBox<Character> comboSexo;
    JComboBox<NivelActividad> comboNivelActividad;
    JComboBox<ObjetivoUsuario> comboObjetivo;
    JSpinner spinnerObjetivoDiarioCalorias;
    protected JButton botonRegistrarse;
    protected JButton botonYaTengoCuenta;

    public PantallaRegistro() {
        // Configuración inicial de la ventana
        setTitle("Registro");
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
        textEmail.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        textNombre = new JTextField(20);
        textNombre.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel labelTelefono = new JLabel("Teléfono:");
        labelTelefono.setForeground(Color.WHITE);
        labelTelefono.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        textTelefono = new JTextField(20);
        textTelefono.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel labelPassword = new JLabel("Contraseña:");
        labelPassword.setForeground(Color.WHITE);
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        textPassword = new JPasswordField(20);
        textPassword.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel etiquetaEdad = new JLabel("Edad:");
        etiquetaEdad.setForeground(Color.WHITE);
        etiquetaEdad.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        textEdad = new JTextField(20);
        textEdad.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel etiquetaPeso = new JLabel("Peso:");
        etiquetaPeso.setForeground(Color.WHITE);
        etiquetaPeso.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        textPeso = new JTextField(20);
        textPeso.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel etiquetaAltura = new JLabel("Altura:");
        etiquetaAltura.setForeground(Color.WHITE);
        etiquetaAltura.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        textAltura = new JTextField(20);
        textAltura.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel etiquetaSexo = new JLabel("Sexo:");
        etiquetaSexo.setForeground(Color.WHITE);
        etiquetaSexo.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        comboSexo = new JComboBox<>();
        comboSexo.addItem('h');
        comboSexo.addItem('m');
        comboSexo.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel etiquetaNivelActividad = new JLabel("Nivel de Actividad:");
        etiquetaNivelActividad.setForeground(Color.WHITE);
        etiquetaNivelActividad.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        NivelActividad[] opcionesNivelActividad = NivelActividad.values();
        comboNivelActividad = new JComboBox<>(opcionesNivelActividad);
        comboNivelActividad.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel etiquetaObjetivo = new JLabel("Objetivo:");
        etiquetaObjetivo.setForeground(Color.WHITE);
        etiquetaObjetivo.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        ObjetivoUsuario[] opcionesObjetivo = ObjetivoUsuario.values();
        comboObjetivo = new JComboBox<>(opcionesObjetivo);
        comboObjetivo.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        JLabel etiquetaCalorias = new JLabel("Objetivo Calorías:");
        etiquetaCalorias.setForeground(Color.WHITE);
        etiquetaCalorias.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font
        spinnerObjetivoDiarioCalorias = new JSpinner(new SpinnerNumberModel(2000, 500, 4000, 100));
        spinnerObjetivoDiarioCalorias.setFont(new Font("Arial", Font.PLAIN, 30)); // bigger font

        botonRegistrarse = createButton("Registrarse");
        botonYaTengoCuenta = createButton("Ya tengo cuenta");

        
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        // Añadiendo los componentes al panel
        c.gridx = 0; c.gridy = 0;
        contentPanel.add(labelEmail, c);

        c.gridx = 1; c.gridy = 0;
        contentPanel.add(textEmail, c);

        c.gridx = 0; c.gridy = 1;
        contentPanel.add(labelNombre, c);

        c.gridx = 1; c.gridy = 1;
        contentPanel.add(textNombre, c);

        c.gridx = 0; c.gridy = 2;
        contentPanel.add(labelTelefono, c);

        c.gridx = 1; c.gridy = 2;
        contentPanel.add(textTelefono, c);

        c.gridx = 0; c.gridy = 3;
        contentPanel.add(labelPassword, c);

        c.gridx = 1; c.gridy = 3;
        contentPanel.add(textPassword, c);

        c.gridx = 0; c.gridy = 4;
        contentPanel.add(etiquetaEdad, c);

        c.gridx = 1; c.gridy = 4;
        contentPanel.add(textEdad, c);

        c.gridx = 0; c.gridy = 5;
        contentPanel.add(etiquetaPeso, c);

        c.gridx = 1; c.gridy = 5;
        contentPanel.add(textPeso, c);

        c.gridx = 0; c.gridy = 6;
        contentPanel.add(etiquetaAltura, c);

        c.gridx = 1; c.gridy = 6;
        contentPanel.add(textAltura, c);

        c.gridx = 0; c.gridy = 7;
        contentPanel.add(etiquetaSexo, c);

        c.gridx = 1; c.gridy = 7;
        contentPanel.add(comboSexo, c);

        c.gridx = 0; c.gridy = 8;
        contentPanel.add(etiquetaNivelActividad, c);

        c.gridx = 1; c.gridy = 8;
        contentPanel.add(comboNivelActividad, c);

        c.gridx = 0; c.gridy = 9;
        contentPanel.add(etiquetaObjetivo, c);

        c.gridx = 1; c.gridy = 9;
        contentPanel.add(comboObjetivo, c);

        c.gridx = 0; c.gridy = 10;
        contentPanel.add(etiquetaCalorias, c);

        c.gridx = 1; c.gridy = 10;
        contentPanel.add(spinnerObjetivoDiarioCalorias, c);

        c.gridx = 0; c.gridy = 11;
        contentPanel.add(botonRegistrarse, c);

        c.gridx = 1; c.gridy = 11;
        contentPanel.add(botonYaTengoCuenta, c);

        // Añadir el panel a la ventana
        this.add(contentPanel, BorderLayout.CENTER);

        // Listener para el botón de registro
        botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textEmail.getText();
                String nombre = textNombre.getText();
                String telefono = textTelefono.getText();
                String contraseña = new String(textPassword.getPassword());
                int edad = Integer.parseInt(textEdad.getText());
                float peso = Float.parseFloat(textPeso.getText());
                float altura = Float.parseFloat(textAltura.getText());
                Character sexo = (Character) comboSexo.getSelectedItem();
                NivelActividad nivelActividad = (NivelActividad) comboNivelActividad.getSelectedItem();
                ObjetivoUsuario objetivo = (ObjetivoUsuario) comboObjetivo.getSelectedItem();
                short objetivoDiarioCalorias = Short.parseShort(spinnerObjetivoDiarioCalorias.getValue().toString());

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/programaciondb4", "root", "admin1234qslia.xjl")) {
                    String queryEntidad = "INSERT INTO Entidad (nombre) VALUES (?)";
                    PreparedStatement statementEntidad = connection.prepareStatement(queryEntidad, Statement.RETURN_GENERATED_KEYS);
                    statementEntidad.setString(1, nombre);
                    statementEntidad.executeUpdate();

                    ResultSet generatedKeys = statementEntidad.getGeneratedKeys();
                    int entidadId = 0;
                    if (generatedKeys.next()) {
                        entidadId = generatedKeys.getInt(1);
                    }

                    String queryUsuario = "INSERT INTO Usuario (entidadId, edad, peso, altura, sexo, nivel_actividad, objetivo, objetivo_diario_calorias, email, contraseña, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statementUsuario = connection.prepareStatement(queryUsuario);
                    statementUsuario.setInt(1, entidadId);
                    statementUsuario.setInt(2, edad);
                    statementUsuario.setFloat(3, peso);
                    statementUsuario.setFloat(4, altura);
                    statementUsuario.setString(5, sexo.toString());
                    statementUsuario.setString(6, nivelActividad.name());
                    statementUsuario.setString(7, objetivo.name());
                    statementUsuario.setInt(8, objetivoDiarioCalorias);
                    statementUsuario.setString(9, email);
                    statementUsuario.setString(10, contraseña);
                    statementUsuario.setString(11, telefono);

                    statementUsuario.executeUpdate();

                    statementEntidad.close();
                    statementUsuario.close();

                    // Redirigir a PantallaPrincipal después del registro exitoso
                    PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                    pantallaPrincipal.setVisible(true);
                    dispose(); // Cerrar la ventana actual de registro
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Listener para el botón de "Ya tengo cuenta"
        botonYaTengoCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaLogin pantallaLogin = new PantallaLogin();
                pantallaLogin.setVisible(true);
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


