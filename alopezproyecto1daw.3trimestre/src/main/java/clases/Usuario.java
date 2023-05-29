package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import enums.NivelActividad;
import enums.ObjetivoUsuario;
import excepciones.ClienteNoExisteException;
import excepciones.ContraseñaInvalidaExcepcion;
import excepciones.EmailInvalidoExcepcion;
import utilidad.DAO;

import java.sql.*;

public class Usuario extends Entidad {
	private String email;
	private int telefono;
	private String contraseña;

	private byte edad;
	private float peso;
	private float altura;
	private char sexo;
	private NivelActividad nivelActividad;
	private ObjetivoUsuario objetivo;
	private short objetivoDiarioCalorias;

	private HashMap<String, Receta> recetas;
	private ArrayList<Rutina> rutina;

	// Prinmer Constructor
	public Usuario(String nombre, String email, int telefono, String contraseña, byte edad, float peso, float altura,
			char sexo, String nivelActividad2, String objetivo2, String objetivoDiarioCalorias2)
			throws SQLException, EmailInvalidoExcepcion {

		super(nombre);

		if (!email.contains("@")) {
			throw new EmailInvalidoExcepcion("El email debe contener un '@'.");
		}

		// Insertar en la base de datos
		HashMap<String, Object> cols = new HashMap<>();
		cols.put("email", email);
		cols.put("nombre", nombre);
		cols.put("telefono", telefono);
		cols.put("password", contraseña.toString());
		DAO.insertar("cliente", cols);

		// Inicializar variables internas
		this.email = email;
		this.nombre = nombre;
		this.telefono = telefono;

		this.edad = edad;
		this.peso = peso;
		this.altura = altura;
		this.sexo = sexo;
		this.nivelActividad = nivelActividad;
		this.objetivo = objetivo;
		this.objetivoDiarioCalorias = objetivoDiarioCalorias;
		this.recetas = new HashMap<>();
		this.rutina = new ArrayList<>();
	}

	// Segundo constructor
	public Usuario(String email, String contraseña)
			throws ClienteNoExisteException, ContraseñaInvalidaExcepcion, SQLException {

		super(nombre);

		// Intentar recuperar de la base de datos
		LinkedHashSet<String> columnas = new LinkedHashSet<>();
		columnas.add("email");
		columnas.add("nombre");
		columnas.add("telefono");
		columnas.add("password");

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", email);

		ArrayList<Object> usuario = DAO.consultar("cliente", columnas, restricciones);

		if (usuario.isEmpty()) {
			throw new ClienteNoExisteException("El cliente no existe en la base de datos.");
		} else {
			Object obj = usuario.get(3);
			String contraseñaRecuperada = obj.toString();

			if (!contraseñaRecuperada.equals(contraseña)) {
				throw new ContraseñaInvalidaExcepcion("La contraseña proporcionada es inválida.");
			}

			// Inicializar variables internas
			this.email = usuario.get(0).toString();
			this.nombre = usuario.get(1).toString();
			this.telefono = (int) usuario.get(2);
		}
	}

	// getters y setters...

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws SQLException {
		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("email", email);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		DAO.actualizar("cliente", datosAModificar, restricciones);

		// Cambiar el valor de la variable interna
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		try {
			// Actualizar en la base de datos
			HashMap<String, Object> datosAModificar = new HashMap<>();
			datosAModificar.put("nombre", nombre);

			HashMap<String, Object> restricciones = new HashMap<>();
			restricciones.put("email", this.email);

			DAO.actualizar("cliente", datosAModificar, restricciones);

			// Cambiar el valor de la variable interna
			this.nombre = nombre;
		} catch (SQLException e) {
			// Manejar la excepción, por ejemplo:
			e.printStackTrace();
		}
	}

	public int Telefono() {
		return telefono;
	}

	public void setTelefono(int telefono) throws SQLException {
		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("telefono", telefono);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		DAO.actualizar("usuario", datosAModificar, restricciones);

		// Cambiar el valor de la variable interna
		this.telefono = telefono;
	}

	public byte getEdad() {
		return edad;
	}

	public void setEdad(byte edad) throws SQLException {
		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("edad", edad);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		DAO.actualizar("usuario", datosAModificar, restricciones);

		// Cambiar el valor de la variable interna
		this.edad = edad;

	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) throws SQLException {
		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("peso", peso);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		DAO.actualizar("usuario", datosAModificar, restricciones);

		// Cambiar el valor de la variable interna
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) throws SQLException {
		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("altura", altura);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		DAO.actualizar("usuario", datosAModificar, restricciones);

		// Cambiar el valor de la variable interna

		this.altura = altura;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) throws SQLException {

		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("sexo", sexo);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		DAO.actualizar("usuario", datosAModificar, restricciones);

		// Cambiar el valor de la variable interna

		this.sexo = sexo;
	}

	public NivelActividad getNivelActividad() {
		return nivelActividad;
	}

	public void setNivelActividad(NivelActividad nivelActividad) throws SQLException {

		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("nivelActividad", nivelActividad);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		DAO.actualizar("usuario", datosAModificar, restricciones);

		// Cambiar el valor de la variable interna

		this.nivelActividad = nivelActividad;
	}

	public ObjetivoUsuario getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(ObjetivoUsuario objetivo) {

		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("objetivo", objetivo);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		try {
			DAO.actualizar("usuario", datosAModificar, restricciones);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Cambiar el valor de la variable interna

		this.objetivo = objetivo;
	}

	public short getObjetivoDiarioCalorias() {
		return objetivoDiarioCalorias;
	}

	public void setObjetivoDiarioCalorias(short objetivoDiarioCalorias) {
		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("objetivoDiarioCalorias", objetivoDiarioCalorias);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		try {
			DAO.actualizar("usuario", datosAModificar, restricciones);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Cambiar el valor de la variable interna

		this.objetivoDiarioCalorias = objetivoDiarioCalorias;
	}

	public HashMap<String, Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(HashMap<String, Receta> recetas) {
		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("recetas", recetas);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		try {
			DAO.actualizar("usuario", datosAModificar, restricciones);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Cambiar el valor de la variable interna

		this.recetas = recetas;

	}

	public ArrayList<Rutina> getRutina() {
		return rutina;
	}

	public void setRutina(ArrayList<Rutina> rutina) {

		// Actualizar en la base de datos
		HashMap<String, Object> datosAModificar = new HashMap<>();
		datosAModificar.put("rutina", rutina);

		HashMap<String, Object> restricciones = new HashMap<>();
		restricciones.put("email", this.email);

		try {
			DAO.actualizar("usuario", datosAModificar, restricciones);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Cambiar el valor de la variable interna

		this.rutina = rutina;
	}

	public void calcular_calorias_diarias() {
		
	}

	public void agregar_alimento(Alimento alimento) {
		
	}

	public void eliminar_alimento(Alimento alimento) {
		
	}

	public void crear_receta(Receta receta) {
		
	}

	public void eliminar_receta(Receta receta) {
		
	}
}

