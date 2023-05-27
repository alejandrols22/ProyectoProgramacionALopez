// -*- coding: utf-8 -*-

package interfaces;

import javax.swing.*;
import interfaces.PantallaPrincipal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textEmail.getText();
                String contraseña = new String(textPassword.getPassword());

                HashMap<String, Object> restricciones = new HashMap<>();
                restricciones.put("email", email);
                restricciones.put("contraseña", contraseña); // Modificado: Reemplazar "contraseña" por "contraseña"

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
                    // Puedes mostrar un mensaje de error más amigable al usuario aquí...
                }
            }
        });

        botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaRegistro().setVisible(true);
                dispose();
            }
        });
    }
}