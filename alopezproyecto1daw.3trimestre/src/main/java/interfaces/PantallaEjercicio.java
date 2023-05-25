package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaEjercicio {

    private JFrame frame;
    private JPanel panel;
    private JLabel ejercicioLabel;
    private JLabel mensajeLabel;
    private int indiceEjercicio;

    private String[] ejercicios = {
            "ejercicio1.gif",
            "ejercicio2.gif",
            "ejercicio3.gif"
    };

    private String imagenDescanso = "descanso.jpg";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PantallaEjercicio().crearInterfaz();
            }
        });
    }

    public void crearInterfaz() {
        frame = new JFrame("Rutina de Ejercicios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ejercicioLabel = new JLabel();
        mensajeLabel = new JLabel();

        JButton empezarButton = new JButton("Empezar Rutina");
        empezarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empezarRutina();
            }
        });

        JButton descansoButton = new JButton("Descanso");
        descansoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarImagenDescanso();
            }
        });

        JButton siguienteButton = new JButton("Siguiente Ejercicio");
        siguienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarSiguienteEjercicio();
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(descansoButton);
        buttonsPanel.add(siguienteButton);

        panel.add(ejercicioLabel, BorderLayout.CENTER);
        panel.add(mensajeLabel, BorderLayout.SOUTH);
        panel.add(empezarButton, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void empezarRutina() {
        indiceEjercicio = 0;
        mostrarEjercicio();
    }

    private void mostrarEjercicio() {
        if (indiceEjercicio < ejercicios.length) {
            String rutaEjercicio = ejercicios[indiceEjercicio];
            ImageIcon imagenEjercicio = new ImageIcon(getClass().getResource(rutaEjercicio));
            ejercicioLabel.setIcon(imagenEjercicio);
            mensajeLabel.setText("");
        } else {
            ejercicioLabel.setIcon(null);
            mensajeLabel.setText("¡Enhorabuena! Has terminado la rutina.");
        }
    }

    private void mostrarImagenDescanso() {
        ImageIcon imagenDescanso = new ImageIcon(getClass().getResource(imagenDescanso));
        ejercicioLabel.setIcon(imagenDescanso);
        mensajeLabel.setText("Descansando...");
    }

    private void mostrarSiguienteEjercicio() {
        indiceEjercicio++;
        mostrarEjercicio();
    }
}