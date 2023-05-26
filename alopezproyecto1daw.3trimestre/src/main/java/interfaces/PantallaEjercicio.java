package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaEjercicio {
    private JFrame frame;

    

        public PantallaEjercicio() {
            // Crea una nueva ventana (JFrame)
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500); // Ajusta el tamaño según sea necesario

            // Ruta relativa del archivo GIF
            String rutaGIF = "src/main/java/gifs/yoga.gif";

            // Verifica si el archivo existe
            java.io.File file = new java.io.File(rutaGIF);
            if (!file.exists()) {
                System.out.println("El archivo no existe: " + file.getAbsolutePath());
                throw new RuntimeException("El archivo no existe: " + file.getAbsolutePath());
            } else {
                System.out.println("El archivo existe: " + file.getAbsolutePath());
            }

            // Carga el GIF en un ImageIcon
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());

            // Crea un JLabel y configúralo para mostrar el icono
            JLabel label = new JLabel(icon);

            // Agrega el JLabel a la ventana
            frame.getContentPane().add(label, BorderLayout.CENTER);
        }

        public void mostrar() {
            // Hace visible la ventana
            frame.setVisible(true);
        }
    }


	