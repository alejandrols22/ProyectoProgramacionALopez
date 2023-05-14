package clases;

import java.util.ArrayList;
import java.util.HashMap;

import enums.NivelActividad;
import enums.ObjetivoUsuario;

public class Usuario {

	private byte edad;
    private float peso;
    private float altura;
    private char sexo;
    private NivelActividad nivel_actividad;
    private ObjetivoUsuario objetivo;
    private short objetivo_diario_calorias;

    private HashMap<String, Receta> recetas;
    private ArrayList<Rutina> rutina;

    float calcular_calorias_diarias() {
        // Implementación aquí
        return 0.0f;
    }

    void agregar_alimento(Alimento alimento) {
        // Implementación aquí
    }

    void eliminar_alimento(Alimento alimento) {
        // Implementación aquí
    }

    Receta crear_receta() {
        // Implementación aquí
        return null;
    }

    void eliminar_receta(String nombre) {
        // Implementación aquí
    }
}

// La clase Tuple no existe en Java de forma nativa. Podemos definir una versión simple aquí:
class Tuple<T, U> {
    public final T first;
    public final U second;

    public Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }
}