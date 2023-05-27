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

import interfaces.PantallaCrearReceta;
import interfaces.PantallaCrearRutina;
import interfaces.PantallaEjercicio;
import interfaces.PantallaLogin;
import interfaces.PantallaRegistro;
import interfaces.PantallaVerRecetas;
import utilidad.DAO;
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
		
		new PantallaLogin().setVisible(true);
		*/
		/*
		new PantallaEjercicio().setVisible(true);
		
		*/
		// Ejercicios de Resistencia
		Ejercicio correrLugar = new Ejercicio("Correr en el lugar", 5.0f, (short)20, Categoria.RESISTENCIA);
		Ejercicio saltosCuerda = new Ejercicio("Saltos con cuerda", 7.5f, (short)15, Categoria.RESISTENCIA);
		Ejercicio subirEscaleras = new Ejercicio("Subir escaleras", 6.0f, (short)10, Categoria.RESISTENCIA);
		Ejercicio burpees = new Ejercicio("Burpees", 8.0f, (short)10, Categoria.RESISTENCIA);
		Ejercicio highKnees = new Ejercicio("High Knees", 6.0f, (short)10, Categoria.RESISTENCIA);
		Ejercicio jumpingJacks = new Ejercicio("Jumping Jacks", 5.0f, (short)10, Categoria.RESISTENCIA);
		Ejercicio mountainClimbers = new Ejercicio("Mountain Climbers", 4.0f, (short)15, Categoria.RESISTENCIA);
		Ejercicio lunges = new Ejercicio("Lunges", 5.0f, (short)10, Categoria.RESISTENCIA);
		Ejercicio stepUps = new Ejercicio("Step Ups", 4.0f, (short)15, Categoria.RESISTENCIA);
		Ejercicio tuckJumps = new Ejercicio("Tuck Jumps", 5.0f, (short)10, Categoria.RESISTENCIA);
		Ejercicio plankJacks = new Ejercicio("Plank Jacks", 4.0f, (short)10, Categoria.RESISTENCIA);
		Ejercicio skaters = new Ejercicio("Skaters", 4.0f, (short)15, Categoria.RESISTENCIA);
		Ejercicio squatJumps = new Ejercicio("Squat Jumps", 4.5f, (short)10, Categoria.RESISTENCIA);
		Ejercicio bearCrawls = new Ejercicio("Bear Crawls", 4.0f, (short)10, Categoria.RESISTENCIA);
		Ejercicio bicycleCrunches = new Ejercicio("Bicycle Crunches", 4.5f, (short)15, Categoria.RESISTENCIA);
		Ejercicio boxJumps = new Ejercicio("Box Jumps", 5.0f, (short)15, Categoria.RESISTENCIA);
		Ejercicio broadJumps = new Ejercicio("Broad Jumps", 5.5f, (short)10, Categoria.RESISTENCIA);
		Ejercicio crabWalks = new Ejercicio("Crab Walks", 4.5f, (short)10, Categoria.RESISTENCIA);
		Ejercicio flutterKicks = new Ejercicio("Flutter Kicks", 4.0f, (short)15, Categoria.RESISTENCIA);
		Ejercicio frogJumps = new Ejercicio("Frog Jumps", 5.0f, (short)10, Categoria.RESISTENCIA);

		// Ejercicios de Fuerza
		Ejercicio pushUps = new Ejercicio("Push Ups", 4.0f, (short)10, Categoria.FUERZA);
		Ejercicio sitUps = new Ejercicio("Sit Ups", 4.0f, (short)15, Categoria.FUERZA);
		Ejercicio pullUps = new Ejercicio("Pull Ups", 4.5f, (short)10, Categoria.FUERZA);
		Ejercicio squats = new Ejercicio("Squats", 4.5f, (short)15, Categoria.FUERZA);
		Ejercicio tricepDips = new Ejercicio("Tricep Dips", 4.0f, (short)10, Categoria.FUERZA);
		Ejercicio benchPress = new Ejercicio("Bench Press", 4.5f, (short)10, Categoria.FUERZA);
		Ejercicio deadlifts = new Ejercicio("Deadlifts", 5.0f, (short)10, Categoria.FUERZA);
		Ejercicio legPress = new Ejercicio("Leg Press", 4.5f, (short)15, Categoria.FUERZA);
		Ejercicio overheadPress = new Ejercicio("Overhead Press", 4.0f, (short)10, Categoria.FUERZA);
		Ejercicio rows = new Ejercicio("Rows", 4.5f, (short)10, Categoria.FUERZA);
		Ejercicio curls = new Ejercicio("Curls", 4.0f, (short)15, Categoria.FUERZA);
		Ejercicio planks = new Ejercicio("Planks", 4.5f, (short)10, Categoria.FUERZA);
		Ejercicio russianTwists = new Ejercicio("Russian Twists", 4.5f, (short)10, Categoria.FUERZA);
		Ejercicio legCurls = new Ejercicio("Leg Curls", 4.0f, (short)15, Categoria.FUERZA);
		Ejercicio latPullDowns = new Ejercicio("Lat Pull Downs", 4.5f, (short)10, Categoria.FUERZA);
		Ejercicio chestFlies = new Ejercicio("Chest Flies", 4.0f, (short)10, Categoria.FUERZA);
		Ejercicio sideLateralRaises = new Ejercicio("Side Lateral Raises", 4.0f, (short)10, Categoria.FUERZA);
		Ejercicio tricepExtensions = new Ejercicio("Tricep Extensions", 4.0f, (short)10, Categoria.FUERZA);
		Ejercicio calfRaises = new Ejercicio("Calf Raises", 4.0f, (short)15, Categoria.FUERZA);

		// Ejercicios de Flexibilidad
		Ejercicio cobraStretch = new Ejercicio("Cobra Stretch", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio seatedToeTouch = new Ejercicio("Seated Toe Touch", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio standingHamstringStretch = new Ejercicio("Standing Hamstring Stretch", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio lungeWithSpinalTwist = new Ejercicio("Lunge with Spinal Twist", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio tricepsStretch = new Ejercicio("Triceps Stretch", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio butterflyStretch = new Ejercicio("Butterfly Stretch", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio kneelQuadStretch = new Ejercicio("Kneel Quad Stretch", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio standingBicepStretch = new Ejercicio("Standing Bicep Stretch", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio frogPose = new Ejercicio("Frog Pose", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio standingSideStretch = new Ejercicio("Standing Side Stretch", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio seatedShoulderSqueeze = new Ejercicio("Seated Shoulder Squeeze", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio camelPose = new Ejercicio("Camel Pose", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio standingCalfStretch = new Ejercicio("Standing Calf Stretch", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio recliningBoundAnglePose = new Ejercicio("Reclining Bound Angle Pose", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio bridgePose = new Ejercicio("Bridge Pose", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio seatedNeckRelease = new Ejercicio("Seated Neck Release", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio pigeonPose = new Ejercicio("Pigeon Pose", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio childPose = new Ejercicio("Child's Pose", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio standingQuadStretch = new Ejercicio("Standing Quad Stretch", 2.5f, (short)10, Categoria.FLEXIBILIDAD);
		Ejercicio sphinxPose = new Ejercicio("Sphinx Pose", 2.0f, (short)10, Categoria.FLEXIBILIDAD);
		/*
		
		Proteinas pechugaPollo = new Proteinas("Pechuga de pollo", 100f, 165f);
		Proteinas lentejas = new Proteinas("Lentejas", 100f, 116f);
		Proteinas quinoa = new Proteinas("Quinoa", 100f, 120f);
		Proteinas huevos = new Proteinas("Huevos", 100f, 155f);
		Proteinas tofu = new Proteinas("Tofu", 100f, 144f);
		Proteinas pavo = new Proteinas("Pavo", 100f, 135f);
		Proteinas salmon = new Proteinas("Salmón", 100f, 206f);
		Proteinas atun = new Proteinas("Atún", 100f, 144f);
		Proteinas tempeh = new Proteinas("Tempeh", 100f, 193f);
		Proteinas edamame = new Proteinas("Edamame", 100f, 122f);
		Proteinas yogurtGriego = new Proteinas("Yogurt Griego", 100f, 59f);
		Proteinas camarones = new Proteinas("Camarones", 100f, 85f);
		Proteinas quesoCottage = new Proteinas("Queso Cottage", 100f, 98f);
		Proteinas almendras = new Proteinas("Almendras", 100f, 579f);
		Proteinas leche = new Proteinas("Leche", 100f, 42f);
		Proteinas carneRes = new Proteinas("Carne de res", 100f, 250f);
		Proteinas carneCerdo = new Proteinas("Carne de cerdo", 100f, 242f);
		Proteinas garbanzos = new Proteinas("Garbanzos", 100f, 119f);
		Proteinas cacahuetes = new Proteinas("Cacahuetes", 100f, 567f);
		Proteinas brocoli = new Proteinas("Brócoli", 100f, 55f);
		
		
		Carbohidratos panIntegral = new Carbohidratos("Pan integral", 100f, 247f);
		Carbohidratos arrozIntegral = new Carbohidratos("Arroz integral", 100f, 111f);
		Carbohidratos cebada = new Carbohidratos("Cebada", 100f, 123f);
		Carbohidratos centeno = new Carbohidratos("Centeno", 100f, 335f);
		Carbohidratos mijo = new Carbohidratos("Mijo", 100f, 119f);
		Carbohidratos trigoSarraceno = new Carbohidratos("Trigo sarraceno", 100f, 92f);
		Carbohidratos batata = new Carbohidratos("Batata", 100f, 86f);
		Carbohidratos remolacha = new Carbohidratos("Remolacha", 100f, 43f);
		Carbohidratos zanahoria = new Carbohidratos("Zanahoria", 100f, 41f);
		Carbohidratos kiwi = new Carbohidratos("Kiwi", 100f, 41f);
		Carbohidratos melon = new Carbohidratos("Melón", 100f, 34f);
		Carbohidratos papaya = new Carbohidratos("Papaya", 100f, 43f);
		Carbohidratos ciruela = new Carbohidratos("Ciruela", 100f, 46f);
		Carbohidratos higo = new Carbohidratos("Higo", 100f, 74f);
		Carbohidratos granada = new Carbohidratos("Granada", 100f, 83f);
		Carbohidratos durazno = new Carbohidratos("Durazno", 100f, 39f);
		Carbohidratos damasco = new Carbohidratos("Damasco", 100f, 48f);
		Carbohidratos guayaba = new Carbohidratos("Guayaba", 100f, 68f);
		Carbohidratos lichi = new Carbohidratos("Lichi", 100f, 66f);
		Carbohidratos frambuesa = new Carbohidratos("Frambuesa", 100f, 52f);
		
		
		Grasas aceiteOliva = new Grasas("Aceite de oliva", 100f, 884f);
		Grasas mantequilla = new Grasas("Mantequilla", 100f, 717f);
		Grasas quesoCheddar = new Grasas("Queso cheddar", 100f, 402f);
		Grasas tocino = new Grasas("Tocino", 100f, 42f);
		Grasas salchicha = new Grasas("Salchicha", 100f, 301f);
		Grasas aceiteCoco = new Grasas("Aceite de coco", 100f, 862f);
		Grasas crema = new Grasas("Crema", 100f, 340f);
		Grasas aceitePalma = new Grasas("Aceite de palma", 100f, 884f);
		Grasas aceiteMaiz = new Grasas("Aceite de maíz", 100f, 884f);
		Grasas mayonesa = new Grasas("Mayonesa", 100f, 680f);
		Grasas mantequillaCacahuete = new Grasas("Mantequilla de cacahuete", 100f, 588f);
		Grasas aceiteSoja = new Grasas("Aceite de soja", 100f, 884f);
		Grasas mantecaCerdo = new Grasas("Manteca de cerdo", 100f, 892f);
		Grasas aceiteGirasol = new Grasas("Aceite de girasol", 100f, 884f);
		Grasas quesoRoquefort = new Grasas("Queso roquefort", 100f, 369f);
		Grasas tocinoAhumado = new Grasas("Tocino ahumado", 100f, 42f);
		Grasas salami = new Grasas("Salami", 100f, 336f);
		Grasas aceiteSesamo = new Grasas("Aceite de sésamo", 100f, 884f);
		Grasas aceiteCanola = new Grasas("Aceite de canola", 100f, 884f);
		Grasas aguacate = new Grasas("Aguacate", 100f, 160f);
		  
		
		
		 
		 // Creamos los alimentos
	    Alimento pollo = new Proteinas("Pollo", 100f, 165f);
	    Alimento arroz = new Carbohidratos("Arroz", 200f, 222f);
	    Alimento aceiteOliva = new Grasas("Aceite de oliva", 10f, 88.4f);

	 // Creamos la receta
	    Receta arrozConPollo = new Receta("Arroz con Pollo");

	    // Añadimos los ingredientes a la receta
	    arrozConPollo.agregar_ingrediente(pollo);
	    arrozConPollo.agregar_ingrediente(arroz);
	    arrozConPollo.agregar_ingrediente(aceiteOliva);
        
        System.out.println("Las calorías totales de la receta \"" + arrozConPollo.getNombre() + "\" son: " + arrozConPollo.getCaloriasTotales() + " kcal.");
        
        
	 
		
		// Alimentos de tipo Carbohidratos
		Carbohidratos arroz = new Carbohidratos("Arroz", 100, 130);
		Carbohidratos pasta = new Carbohidratos("Pasta", 100, 157);
		Carbohidratos pan = new Carbohidratos("Pan", 100, 265);
		// ... y así sucesivamente hasta 20 alimentos

		// Alimentos de tipo Grasas
		Grasas aceiteOliva = new Grasas("Aceite de Oliva", 100, 884);
		Grasas mantequilla = new Grasas("Mantequilla", 100, 717);
		Grasas quesoCheddar = new Grasas("Queso Cheddar", 100, 402);
		// ... y así sucesivamente hasta 20 alimentos

		// Alimentos de tipo Proteinas
		Proteinas pollo = new Proteinas("Pollo", 100, 165);
		Proteinas huevo = new Proteinas("Huevo", 100, 155);
		Proteinas salmon = new Proteinas("Salmon", 100, 206);
		// ... y así sucesivamente hasta 20 alimentos
		
		
		HashSet<Alimento> menu = new HashSet<>();
		menu.add(arroz);
		menu.add(pasta);
		menu.add(pan);
		
		float nuevaCantidad = 50; // la cantidad que el usuario quiere usar
		float nuevaCalorias = (nuevaCantidad / arroz.getCantidad()) * arroz.getCalorias(); // regla de tres para calcular las nuevas calorías
		arroz.setCantidad(nuevaCantidad); // actualizamos la cantidad del alimento
		arroz.setCalorias(nuevaCalorias); // actualizamos las calorías del alimento
		
		// Ahora el usuario puede seleccionar alimentos del menú para su receta
		Receta miReceta = new Receta("Mi Receta");
		miReceta.agregar_ingrediente(arroz);
		miReceta.agregar_ingrediente(pollo);
		
		
        
        PantallaEjercicio pantalla = new PantallaEjercicio();
        pantalla.mostrar();
        
        PantallaCrearRutina pantallaCrearRutina = new PantallaCrearRutina();
        pantallaCrearRutina.mostrarInterfaz();
        
        PantallaCrearReceta pantallaCrearReceta = new PantallaCrearReceta();
        pantallaCrearReceta.mostrarInterfaz();
        
        PantallaVerRecetas pantallaVerRecetas = new PantallaVerRecetas();
		pantallaVerRecetas.mostrarInterfaz();
		
		PantallaCrearRutina pantallaCrearRutina = new PantallaCrearRutina();
        pantallaCrearRutina.mostrarInterfaz();
		
        
		
*/
		ArrayList<Object> usuarios = null;
		try {
		    usuarios = DAO.consultar("usuario", new LinkedHashSet<>(), new HashMap<>());
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		// Comprueba si los usuarios no son null antes de intentar imprimirlos
		if (usuarios != null) {
		    // Mostrar la lista de usuarios existentes
		    System.out.println("Usuarios existentes:");
		    for (Object usuario : usuarios) {
		        System.out.println(usuario);
		    }
		} else {
		    System.out.println("No se pudo obtener la lista de usuarios.");
		   
		}
		 System.out.println("Usuarios obtenidos: " + usuarios.size());
		PantallaLogin pantallaLogin = new PantallaLogin();
        pantallaLogin.setVisible(true);
		
		
    }
}