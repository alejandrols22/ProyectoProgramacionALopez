package interfaces;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PantallaEjercicio extends JFrame {
	private JLabel label;
	private int gifIndex;
	private String[] gifPaths;
	private Clip audioClip;
	private JButton audioButton;
	private boolean isPlaying;
	private JComboBox<String> rutinaComboBox;
	private JPanel panel;

	private String[] resistenciaGifs = { "src/main/java/gifs/RESISTENCIA/1SaltarComba.gif",
			"src/main/java/gifs/RESISTENCIA/2Burpee.gif", "src/main/java/gifs/RESISTENCIA/3SaltosDeTijera.gif",
			"src/main/java/gifs/RESISTENCIA/4MarchaEstacionaria.gif",
			"src/main/java/gifs/RESISTENCIA/5EscaladorMontaña.gif", "src/main/java/gifs/RESISTENCIA/6EjercicioStep.gif",
			"src/main/java/gifs/RESISTENCIA/7JumpingLunges.gif", "src/main/java/gifs/RESISTENCIA/8TuckJumps.gif",
			"src/main/java/gifs/RESISTENCIA/9SentadillasLateral.gif",
			"src/main/java/gifs/RESISTENCIA/10StarJumpsgif.gif" };

	private String[] fuerzaGifs = { "src/main/java/gifs/FUERZA/1flexion.gif",
			"src/main/java/gifs/FUERZA/2EstocadasHaciaAdelante.gif", "src/main/java/gifs/FUERZA/3plancha.gif",
			"src/main/java/gifs/FUERZA/4PlanchaLateral.gif", "src/main/java/gifs/FUERZA/5EstocaLateral.gif",
			"src/main/java/gifs/FUERZA/6Dips.gif", "src/main/java/gifs/FUERZA/7FlexionElevada.gif",
			"src/main/java/gifs/FUERZA/8Sentadilla.gif", "src/main/java/gifs/FUERZA/9LevantamientoDePantorrillas.gif",
			"src/main/java/gifs/FUERZA/10KickBack.gif" };

	private String[] flexibilidadGifs = {

			"src/main/java/gifs/FLEXIBILIDAD/2GatePose.gif", "src/main/java/gifs/FLEXIBILIDAD/3CobraPose.gif",
			"src/main/java/gifs/FLEXIBILIDAD/4MoonPose.gif",

			"src/main/java/gifs/FLEXIBILIDAD/6WideLegged.gif", "src/main/java/gifs/FLEXIBILIDAD/7MountainPose.gif",

			"src/main/java/gifs/FLEXIBILIDAD/10TabelTopPose.gif" };

	private String[] resistenciaSongs = { "src/main/java/canciones/1Phonk.wav",
			"src/main/java/canciones/2Motivacional.wav", "src/main/java/canciones/3TheChain.wav",
			"src/main/java/canciones/4MrBlueSky.wav", "src/main/java/canciones/5TotoAfrica.wav",
			"src/main/java/canciones/6Maniac.wav", "src/main/java/canciones/7EyeOfTiger.wav" };

	private String[] fuerzaSongs = { "src/main/java/canciones/1Phonk.wav", "src/main/java/canciones/2Motivacional.wav",
			"src/main/java/canciones/3TheChain.wav", "src/main/java/canciones/4MrBlueSky.wav",
			"src/main/java/canciones/5TotoAfrica.wav", "src/main/java/canciones/6Maniac.wav",
			"src/main/java/canciones/7EyeOfTiger.wav" };

	private String[] flexibilidadSongs = { "src/main/java/canciones/1Phonk.wav",
			"src/main/java/canciones/2Motivacional.wav", "src/main/java/canciones/3TheChain.wav",
			"src/main/java/canciones/4MrBlueSky.wav", "src/main/java/canciones/5TotoAfrica.wav",
			"src/main/java/canciones/6Maniac.wav", "src/main/java/canciones/7EyeOfTiger.wav" };

	public PantallaEjercicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setBackground(new Color(50, 50, 50));
		setLayout(new BorderLayout());

		gifPaths = resistenciaGifs; // Por defecto, muestra los GIFs de resistencia
		gifIndex = 0;

		ImageIcon icon = new ImageIcon(gifPaths[gifIndex]);
		label = new JLabel(icon);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(new Color(50, 50, 50));

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
		siguienteButton.setForeground(Color.WHITE);
		siguienteButton.setBackground(new Color(70, 70, 70));
		panel.add(siguienteButton);

		rutinaComboBox = new JComboBox<>();
		rutinaComboBox.addItem("Resistencia");
		rutinaComboBox.addItem("Fuerza");
		rutinaComboBox.addItem("Flexibilidad");
		rutinaComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rutinaSeleccionada = (String) rutinaComboBox.getSelectedItem();
				if (rutinaSeleccionada.equals("Resistencia")) {
					gifPaths = resistenciaGifs;
				} else if (rutinaSeleccionada.equals("Fuerza")) {
					gifPaths = fuerzaGifs;
				} else if (rutinaSeleccionada.equals("Flexibilidad")) {
					gifPaths = flexibilidadGifs;
				}
				gifIndex = 0;
				ImageIcon newIcon = new ImageIcon(gifPaths[gifIndex]);
				label.setIcon(newIcon);
			}
		});
		rutinaComboBox.setForeground(Color.WHITE);
		rutinaComboBox.setBackground(new Color(70, 70, 70));
		panel.add(rutinaComboBox);

		String[] songPaths = resistenciaSongs; // Por defecto, muestra las canciones de resistencia
		String[] songNames = new String[songPaths.length];
		for (int i = 0; i < songPaths.length; i++) {
			songNames[i] = "Canción " + (i + 1);
		}

		JComboBox<String> songComboBox = new JComboBox<>(songNames);
		songComboBox.setForeground(Color.WHITE);
		songComboBox.setBackground(new Color(70, 70, 70));
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
		audioButton.setForeground(Color.WHITE);
		audioButton.setBackground(new Color(70, 70, 70));
		panel.add(audioButton);

		JButton backButton = new JButton("Volver a la pantalla principal");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopAudio(); // Detener reproducción de audio
				new PantallaPrincipal().setVisible(true);
				dispose();
			}
		});
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(new Color(70, 70, 70));
		panel.add(backButton);

		add(label, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
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
			JOptionPane.showMessageDialog(this, "Error al reproducir el audio: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
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

	public static void main(String[] args) {
		PantallaEjercicio ejercicio = new PantallaEjercicio();
		ejercicio.setVisible(true);
	}
}
