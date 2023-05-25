package clases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import interfaces.PantallaLogin;
import interfaces.PantallaRegistro;
public class Main {
	
	public static void main(String[] args) {
		/*
		launch(args);
		try {
			BufferedImage imagen = new BufferedImage(800,600,BufferedImage.TYPE_BYTE_GRAY);
				ImageIO.write(imagen, "JPG", new File("./miImagen.jpg"));
			for(short i = 0;i<imagen.getWidth();i++) {
				for(short j=0;j<imagen.getHeight();j++) {
					imagen.setRGB(i, j, Integer.parseInt("123123",16));
				}
			}
		} catch (IOException e) {
			
		}
		*/
		new PantallaLogin().setVisible(true);

	}

}
