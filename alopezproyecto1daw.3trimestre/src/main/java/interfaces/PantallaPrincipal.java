package interfaces;

import javax.swing.*;

public class PantallaPrincipal extends JFrame {
    public PantallaPrincipal() {
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton botonVerProgreso = new JButton("Ver Progreso");
        botonVerProgreso.setBounds(100, 100, 200, 30);
        add(botonVerProgreso);
        botonVerProgreso.addActionListener(e -> {
            new PantallaVerProgreso().setVisible(true);
            this.setVisible(false);
        });

        JButton botonCrearRecetas = new JButton("Crear Recetas");
        botonCrearRecetas.setBounds(100, 150, 200, 30);
        add(botonCrearRecetas);
        botonCrearRecetas.addActionListener(e -> {
            new PantallaCrearReceta().setVisible(true);
            this.setVisible(false);
        });

        JButton botonVerRecetas = new JButton("Ver Tus Recetas");
        botonVerRecetas.setBounds(100, 200, 200, 30);
        add(botonVerRecetas);
        botonVerRecetas.addActionListener(e -> {
            new PantallaVerRecetas().setVisible(true);
            this.setVisible(false);
        });

        JButton botonCrearRutina = new JButton("Crear Rutina");
        botonCrearRutina.setBounds(100, 250, 200, 30);
        add(botonCrearRutina);
        botonCrearRutina.addActionListener(e -> {
            new PantallaCrearRutina().setVisible(true);
            this.setVisible(false);
        });

        JButton botonVerRutina = new JButton("Ver Tu Rutina");
        botonVerRutina.setBounds(100, 300, 200, 30);
        add(botonVerRutina);
        botonVerRutina.addActionListener(e -> {
            new PantallaVerRutina().setVisible(true);
            this.setVisible(false);
        });
        JButton botonActualizarDatos = new JButton("Actualiza tus datos");
        botonActualizarDatos.setBounds(100, 350, 200, 30);
        add(botonActualizarDatos);
        botonActualizarDatos.addActionListener(e -> {
            new PantallaVerRutina().setVisible(true);
            this.setVisible(false);
        });
    }
}