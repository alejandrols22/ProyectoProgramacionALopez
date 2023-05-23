package interfaces;

import javax.swing.*;

import clases.Usuario;
import excepciones.ClienteNoExisteException;
import excepciones.ContraseñaInvalidaExcepcion;

import java.awt.*;
import java.sql.SQLException;

public class PantallaLogin extends JFrame {

    private JTextField textEmail;
    private JPasswordField textPassword;
    private JButton botonIngresar;

    public PantallaLogin() {
        getContentPane().setLayout(null);
        setTitle("Login");
        setSize(371, 240);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(10, 10, 80, 25);
        getContentPane().add(labelEmail);

        textEmail = new JTextField(20);
        textEmail.setBounds(100, 10, 229, 25);
        getContentPane().add(textEmail);

        JLabel labelPassword = new JLabel("Contraseña:");
        labelPassword.setBounds(10, 40, 80, 25);
        getContentPane().add(labelPassword);

        textPassword = new JPasswordField(20);
        textPassword.setBounds(100, 40, 229, 25);
        getContentPane().add(textPassword);

        botonIngresar = new JButton("Ingresar");
        botonIngresar.setBounds(100, 70, 100, 25);
        getContentPane().add(botonIngresar);
        
        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setBounds(229, 70, 100, 25);
        getContentPane().add(botonRegistrarse);

        botonIngresar.addActionListener(e -> {
            // Aquí va el código para verificar el inicio de sesión
            new PantallaPrincipal().setVisible(true);
            this.setVisible(false);
            
            String email = textEmail.getText();
            String contraseña = textPassword.getText();

            try {
                Usuario cliente = new Usuario(email, contraseña);
                new PantallaPrincipal().setVisible(true);
                this.setVisible(false);
            } catch (ClienteNoExisteException | ContraseñaInvalidaExcepcion | SQLException ex) {
               
                System.err.println("Error al iniciar sesión: " + ex.getMessage());
            }
        });
        
        botonRegistrarse.addActionListener(e -> {
            new PantallaRegistro().setVisible(true);
            this.setVisible(false);
        });
    }
}