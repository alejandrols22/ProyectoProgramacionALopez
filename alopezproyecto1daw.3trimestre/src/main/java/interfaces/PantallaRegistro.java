package interfaces;

import javax.swing.*;


import javax.swing.*;

import java.sql.Statement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import enums.NivelActividad;
import enums.ObjetivoUsuario;

public class PantallaRegistro extends JFrame {

    private JTextField textEmail;
    private JTextField textNombre;
    private JTextField textTelefono;
    private JPasswordField textPassword;
    private JTextField textEdad;
    private JTextField textPeso;
    private JTextField textAltura;
    private JComboBox<NivelActividad> comboNivelActividad;
    private JComboBox<ObjetivoUsuario> comboObjetivo;
    private JSpinner spinnerObjetivoDiarioCalorias;
    private JButton botonRegistrarse;
    private JButton botonYaTengoCuenta;

    public PantallaRegistro() {
        getContentPane().setLayout(null);
        setTitle("Registro");
        setSize(507, 639);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(10, 10, 80, 25);
        getContentPane().add(labelEmail);

        textEmail = new JTextField(20);
        textEmail.setBounds(100, 10, 160, 25);
        getContentPane().add(textEmail);

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setBounds(10, 40, 80, 25);
        getContentPane().add(labelNombre);

        textNombre = new JTextField(20);
        textNombre.setBounds(100, 40, 160, 25);
        getContentPane().add(textNombre);

        JLabel labelTelefono = new JLabel("Teléfono:");
        labelTelefono.setBounds(10, 70, 80, 25);
        getContentPane().add(labelTelefono);

        textTelefono = new JTextField(20);
        textTelefono.setBounds(100, 70, 160, 25);
        getContentPane().add(textTelefono);

        JLabel labelPassword = new JLabel("Contraseña:");
        labelPassword.setBounds(10, 100, 80, 25);
        getContentPane().add(labelPassword);

        textPassword = new JPasswordField(20);
        textPassword.setBounds(100, 100, 160, 25);
        getContentPane().add(textPassword);

        JLabel etiquetaEdad = new JLabel("Edad:");
        etiquetaEdad.setBounds(10, 131, 80, 25);
        getContentPane().add(etiquetaEdad);

        textEdad = new JTextField(20);
        textEdad.setBounds(100, 131, 160, 25);
        getContentPane().add(textEdad);

        JLabel etiquetaPeso = new JLabel("Peso:");
        etiquetaPeso.setBounds(10, 161, 80, 25);
        getContentPane().add(etiquetaPeso);

        textPeso = new JTextField(20);
        textPeso.setBounds(100, 161, 160, 25);
        getContentPane().add(textPeso);

        JLabel etiquetaAltura = new JLabel("Altura:");
        etiquetaAltura.setBounds(10, 191, 80, 25);
        getContentPane().add(etiquetaAltura);

        textAltura = new JTextField(20);
        textAltura.setBounds(100, 191, 160, 25);
        getContentPane().add(textAltura);

        JComboBox<Character> comboSexo = new JComboBox<>();
        comboSexo.addItem('h');
        comboSexo.addItem('m');
        comboSexo.setBounds(100, 221, 160, 25);
        getContentPane().add(comboSexo);

        JLabel etiquetaNivelActividad = new JLabel("Nivel de Actividad:");
        etiquetaNivelActividad.setBounds(10, 251, 120, 25);
        getContentPane().add(etiquetaNivelActividad);

        NivelActividad[] opcionesNivelActividad = NivelActividad.values();
        comboNivelActividad = new JComboBox<>(opcionesNivelActividad);
        comboNivelActividad.setBounds(130, 251, 160, 25);
        getContentPane().add(comboNivelActividad);

        JLabel etiquetaObjetivo = new JLabel("Objetivo:");
        etiquetaObjetivo.setBounds(10, 281, 80, 25);
        getContentPane().add(etiquetaObjetivo);

        ObjetivoUsuario[] opcionesObjetivo = ObjetivoUsuario.values();
        comboObjetivo = new JComboBox<>(opcionesObjetivo);
        comboObjetivo.setBounds(100, 281, 160, 25);
        getContentPane().add(comboObjetivo);

        JLabel etiquetaCalorias = new JLabel("Objetivo Calorías:");
        etiquetaCalorias.setBounds(10, 311, 120, 25);
        getContentPane().add(etiquetaCalorias);

        spinnerObjetivoDiarioCalorias = new JSpinner(new SpinnerNumberModel(2000, 500, 4000, 100));
        spinnerObjetivoDiarioCalorias.setBounds(130, 311, 130, 25);
        getContentPane().add(spinnerObjetivoDiarioCalorias);

        botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setBounds(66, 345, 100, 25);
        getContentPane().add(botonRegistrarse);

        botonYaTengoCuenta = new JButton("Ya tengo cuenta");
        botonYaTengoCuenta.setBounds(180, 345, 130, 25);
        getContentPane().add(botonYaTengoCuenta);

        botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textEmail.getText();
                String nombre = textNombre.getText();
                String telefono = textTelefono.getText();
                String password = new String(textPassword.getPassword());
                byte edad = Byte.parseByte(textEdad.getText());
                float peso = Float.parseFloat(textPeso.getText());
                float altura = Float.parseFloat(textAltura.getText());
                Character sexo = (Character) comboSexo.getSelectedItem();
                NivelActividad nivelActividad = (NivelActividad) comboNivelActividad.getSelectedItem();
                ObjetivoUsuario objetivo = (ObjetivoUsuario) comboObjetivo.getSelectedItem();
                short objetivoDiarioCalorias = Short.parseShort(spinnerObjetivoDiarioCalorias.getValue().toString());

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/programaciondb3", "root", "admin1234qslia.xjl")) {
                    String queryEntidad = "INSERT INTO Entidad (nombre) VALUES (?)";
                    PreparedStatement statementEntidad = connection.prepareStatement(queryEntidad, Statement.RETURN_GENERATED_KEYS);
                    statementEntidad.setString(1, nombre);
                    statementEntidad.executeUpdate();

                    ResultSet generatedKeys = statementEntidad.getGeneratedKeys();
                    int entidadId = 0;
                    if (generatedKeys.next()) {
                        entidadId = generatedKeys.getInt(1);
                    }

                    String queryUsuario = "INSERT INTO Usuario (entidadId, edad, peso, altura, sexo, nivel_actividad, objetivo, objetivo_diario_calorias, correo, contraseña, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statementUsuario = connection.prepareStatement(queryUsuario);
                    statementUsuario.setInt(1, entidadId);
                    statementUsuario.setByte(2, edad);
                    statementUsuario.setFloat(3, peso);
                    statementUsuario.setFloat(4, altura);
                    statementUsuario.setString(5, sexo.toString());
                    statementUsuario.setString(6, nivelActividad.name());
                    statementUsuario.setString(7, objetivo.name());
                    statementUsuario.setShort(8, objetivoDiarioCalorias);
                    statementUsuario.setString(9, email);
                    statementUsuario.setString(10, password);
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

        botonYaTengoCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaLogin pantallaLogin = new PantallaLogin();
                pantallaLogin.setVisible(true);
                dispose(); // Cerrar la ventana actual de registro
            }
        });
    }
}