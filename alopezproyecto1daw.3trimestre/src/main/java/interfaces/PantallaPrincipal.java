package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PantallaPrincipal extends JFrame {

    public PantallaPrincipal() {
        // Set the window to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(50, 50, 50)); // Set a darker background color

        // Use a GridLayout for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 20, 20));
        buttonPanel.setBackground(new Color(50, 50, 50));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(200, 500, 200, 500));

        // Use a custom Font
        Font font = new Font("Arial", Font.PLAIN, 25);

        // Use custom buttons
        CustomButton botonVerProgreso = createButton("Ver Progreso", font);
        botonVerProgreso.addActionListener(e -> {
            new PantallaVerProgreso().setVisible(true);
            this.setVisible(false);
        });

        CustomButton botonCrearRecetas = createButton("Crear Recetas", font);
        botonCrearRecetas.addActionListener(e -> {
            new PantallaCrearReceta().setVisible(true);
            this.setVisible(false);
        });

        CustomButton botonVerRecetas = createButton("Ver Tus Recetas", font);
        botonVerRecetas.addActionListener(e -> {
            new PantallaVerRecetas().setVisible(true);
            this.setVisible(false);
        });

        CustomButton botonCrearRutina = createButton("Crear Rutina", font);
        botonCrearRutina.addActionListener(e -> {
            new PantallaCrearRutina().setVisible(true);
            this.setVisible(false);
        });

        CustomButton botonVerRutina = createButton("Ver Tu Rutina", font);
        botonVerRutina.addActionListener(e -> {
            new PantallaVerRutina().setVisible(true);
            this.setVisible(false);
        });

        CustomButton botonActualizarDatos = createButton("Actualiza tus datos", font);
        botonActualizarDatos.addActionListener(e -> {
            new PantallaActualizarDatos().setVisible(true);
            this.setVisible(false);
        });

        buttonPanel.add(botonVerProgreso);
        buttonPanel.add(botonCrearRecetas);
        buttonPanel.add(botonVerRecetas);
        buttonPanel.add(botonCrearRutina);
        buttonPanel.add(botonVerRutina);
        buttonPanel.add(botonActualizarDatos);

        this.add(buttonPanel, BorderLayout.CENTER);
    }

    private CustomButton createButton(String text, Font font) {
        CustomButton button = new CustomButton(text);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        return button;
    }

    // Custom button class
    public class CustomButton extends JButton {
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
                g.setColor(new Color(50, 50, 50));
            } else if (getModel().isRollover()) {
                g.setColor(new Color(70, 70, 70));
            } else {
                g.setColor(new Color(100, 100, 100));
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
            super.paintComponent(g);
        }
    }
}

