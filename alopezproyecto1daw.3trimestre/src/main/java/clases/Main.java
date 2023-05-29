package clases;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import enums.Categoria;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import interfaces.PantallaCrearReceta;
import interfaces.PantallaCrearRutina;
import interfaces.PantallaEjercicio;
import interfaces.PantallaLogin;
import interfaces.PantallaRegistro;
import interfaces.PantallaVerRecetas;
import interfaces.PantallaVerRutina;
import utilidad.DAO;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PantallaRegistro pantallaRegistro = new PantallaRegistro();
				pantallaRegistro.setVisible(true);
			}
		});
	}
}
