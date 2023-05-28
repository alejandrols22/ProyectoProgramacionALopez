package interfaces;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PantallaEjercicio {
    private JFrame frame;
    private JLabel label;
    private int gifIndex;
    private String[] gifPaths;
    private Clip audioClip;
    private JButton audioButton;
    private boolean isPlaying;

    public PantallaEjercicio() {
        // Crea una nueva ventana (JFrame)
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500); // Ajusta el tamaño según sea necesario

        // Rutas relativas de los archivos GIF
        gifPaths = new String[] {
                "src/main/java/gifs/RESISTENCIA/2Burpee.gif",
                "src/main/java/gifs/RESISTENCIA/4MarchaEstacionaria.gif",
                "src/main/java/gifs/RESISTENCIA/6EjercicioStep.gif"
        };
        gifIndex = 0;

        // Carga el primer GIF en un ImageIcon y crea un JLabel para mostrarlo
        ImageIcon icon = new ImageIcon(gifPaths[gifIndex]);
        label = new JLabel(icon);

        // Crea un panel para contener los botones
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Crea el botón "Siguiente" y agrega un ActionListener para cambiar el GIF
        JButton siguienteButton = new JButton("Siguiente");
        siguienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gifIndex++;
                if (gifIndex >= gifPaths.length) {
                    gifIndex = 0;
                }
                ImageIcon newIcon = new ImageIcon(gifPaths[gifIndex]);
                label.setIcon(newIcon);
            }
        });
        panel.add(siguienteButton);

        // Crea el botón "Reproducir/Detener audio" y agrega un ActionListener
        audioButton = new JButton("Reproducir audio");
        audioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPlaying) {
                    stopAudio();
                } else {
                    playAudio();
                }
            }
        });
        panel.add(audioButton);

        // Agrega el JLabel y el panel al contenedor principal de la ventana
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
    }

    private void playAudio() {
        try {
            // Carga el archivo de audio
            File audioFile = new File("src/main/java/canciones/cancion.wav");
            audioClip = AudioSystem.getClip();
            audioClip.open(AudioSystem.getAudioInputStream(audioFile));

            // Reproduce el audio en un bucle continuo
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

            // Actualiza el texto del botón
            audioButton.setText("Detener audio");

            // Marca el estado de reproducción como activo
            isPlaying = true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al reproducir el audio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stopAudio() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            audioClip.close();
        }

        // Actualiza el texto del botón
        audioButton.setText("Reproducir audio");

        // Marca el estado de reproducción como inactivo
        isPlaying = false;
    }

    public void mostrar() {
        // Hace visible la ventana
        frame.setVisible(true);
    }
}