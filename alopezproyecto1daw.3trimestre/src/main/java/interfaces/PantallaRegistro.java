package interfaces;

import javax.swing.*;

import clases.Usuario;
import enums.NivelActividad;
import enums.ObjetivoUsuario;
import excepciones.EmailInvalidoExcepcion;

import java.awt.*;
import java.sql.SQLException;

public class PantallaRegistro extends JFrame {

    private JTextField textEmail;
    private JTextField textNombre;
    private JTextField textTelefono;
    private JPasswordField textPassword;
    private JTextField textEdad;
    
    
    private JButton botonRegistrarse;

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

        JTextField campoEdad = new JTextField(20);
        campoEdad.setBounds(100, 131, 160, 25);
        getContentPane().add(campoEdad);

        JLabel etiquetaPeso = new JLabel("Peso:");
        etiquetaPeso.setBounds(10, 161, 80, 25);
        getContentPane().add(etiquetaPeso);

        JTextField campoPeso = new JTextField(20);
        campoPeso.setBounds(100, 161, 160, 25);
        getContentPane().add(campoPeso);

        JLabel etiquetaAltura = new JLabel("Altura:");
        etiquetaAltura.setBounds(10, 191, 80, 25);
        getContentPane().add(etiquetaAltura);

        JTextField campoAltura = new JTextField(20);
        campoAltura.setBounds(100, 191, 160, 25);
        getContentPane().add(campoAltura);

        JLabel etiquetaSexo = new JLabel("Sexo:");
        etiquetaSexo.setBounds(10, 221, 80, 25);
        getContentPane().add(etiquetaSexo);

        JTextField campoSexo = new JTextField(20);
        campoSexo.setBounds(100, 221, 160, 25);
        getContentPane().add(campoSexo);

        JLabel etiquetaObjetivo = new JLabel("Objetivo:");
        etiquetaObjetivo.setBounds(10, 251, 80, 25);
        getContentPane().add(etiquetaObjetivo);

        String[] opcionesObjetivo = { "BAJAR PESO", "SUBIR PESO", "MANTENER PESO" };
        JComboBox campoObjetivo = new JComboBox(opcionesObjetivo);
        campoObjetivo.setBounds(100, 251, 160, 25);
        getContentPane().add(campoObjetivo);

        JLabel etiquetaCalorias = new JLabel("Objetivo Calorías:");
        etiquetaCalorias.setBounds(10, 281, 120, 25);
        getContentPane().add(etiquetaCalorias);

        JSpinner campoCalorias = new JSpinner(new SpinnerNumberModel(2000, 500, 4000, 100));
        campoCalorias.setBounds(130, 281, 130, 25);
        getContentPane().add(campoCalorias);
        
        
        botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setBounds(66, 325, 100, 25);
        getContentPane().add(botonRegistrarse);

        
        
        botonRegistrarse.addActionListener(e -> {
            // Aquí va el código para registrar al usuario
            new PantallaPrincipal().setVisible(true);
            this.setVisible(false);
            
            
            String email = textEmail.getText();
            String nombre = textNombre.getText();
            int telefono = Integer.parseInt(textTelefono.getText());
            String contraseña = textPassword.getText();
            byte edad = Byte.parseByte(textEdad.getText());
           
            try {
                Usuario cliente = new Usuario(nombre, email, telefono, contraseña, edad, peso, altura,
            			sexo, nivelActividad,  objetivo,  objetivoDiarioCalorias);  
                new PantallaPrincipal().setVisible(true);
                this.setVisible(false);
            } catch (SQLException | EmailInvalidoExcepcion ex) {
                // Aquí puedes mostrar un mensaje de error en la interfaz, en lugar de imprimirlo en la consola.
                System.err.println("Error al registrar al cliente: " + ex.getMessage());
            }
        });

        JButton botonYaTengoCuenta = new JButton("Ya tengo cuenta");
        botonYaTengoCuenta.setBounds(41, 361, 160, 25);
        getContentPane().add(botonYaTengoCuenta);

       
        botonYaTengoCuenta.addActionListener(e -> {
            new PantallaLogin().setVisible(true);
            this.setVisible(false);
        });
    }
}