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
    private String[] songPaths;
    private String[] songNames;
    private JComboBox<String> songComboBox;
    private JPanel panel;

    public PantallaEjercicio() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        gifPaths = new String[] {
                "src/main/java/gifs/RESISTENCIA/2Burpee.gif",
                "src/main/java/gifs/RESISTENCIA/4MarchaEstacionaria.gif",
                "src/main/java/gifs/RESISTENCIA/6EjercicioStep.gif"
        };
        gifIndex = 0;

        ImageIcon icon = new ImageIcon(gifPaths[gifIndex]);
        label = new JLabel(icon);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

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

        songPaths = new String[] {
                "src/main/java/canciones/1Phonk.wav",
                "src/main/java/canciones/2Motivacional.wav",
                "src/main/java/canciones/3TheChain.wav",
                "src/main/java/canciones/4MrBlueSky.wav",
                "src/main/java/canciones/5TotoAfrica.wav",
                "src/main/java/canciones/6Maniac.wav",
                "src/main/java/canciones/7EyeOfTiger.wav"
                
        };

        songNames = new String[] {
                "Cancion Phonk",
                "Cancion Motivacional",
                "The Chain",
                "Mr Blue Sky",
                "Toto Africa",
                "Maniac",
                "The Eye of The Tiger"
                // Add more song names corresponding to the paths above...
        };

        songComboBox = new JComboBox<>(songNames);
        panel.add(songComboBox);

        audioButton = new JButton("Reproducir audio");
        audioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPlaying) {
                    stopAudio();
                } else {
                    int selectedSongIndex = songComboBox.getSelectedIndex();
                    playAudio(songPaths[selectedSongIndex]);
                }
            }
        });
        panel.add(audioButton);

        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
    }

    private void playAudio(String songPath) {
        try {
            File audioFile = new File(songPath);
            audioClip = AudioSystem.getClip();
            audioClip.open(AudioSystem.getAudioInputStream(audioFile));

            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

            audioButton.setText("Detener audio");

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

        audioButton.setText("Reproducir audio");

        isPlaying = false;
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PantallaEjercicio().mostrar();
    }
}

