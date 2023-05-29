package interfaces;

import javax.swing.*;
import java.awt.*;

public class PantallaImagen extends JFrame {

    public PantallaImagen() {
        // Configuración inicial de la ventana
        setTitle("Pantalla de Imagen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Cargar la imagen
        ImageIcon imagenIcono = new ImageIcon("src/main/java/imagenes/imagen.jpg");
        Image imagen = imagenIcono.getImage();

        // Crear un panel personalizado para mostrar la imagen como fondo
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Configurar el diseño del panel
        panelFondo.setLayout(new BorderLayout());

      

        // Crear el botón "Volver"
        JButton botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Arial", Font.BOLD, 16));

        // Configurar la acción del botón "Volver"
        botonVolver.addActionListener(e -> {
            // Lógica para volver a la pantalla principal
            PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
            pantallaPrincipal.setVisible(true);
            dispose();
        });

        // Agregar el botón "Volver" al panelFondo en la posición inferior
        panelFondo.add(botonVolver, BorderLayout.SOUTH);

        // Añadir el panelFondo a la ventana
        add(panelFondo);

        // Establecer la ventana en modo de pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaImagen pantalla = new PantallaImagen();
            pantalla.setVisible(true);
        });
    }
}

