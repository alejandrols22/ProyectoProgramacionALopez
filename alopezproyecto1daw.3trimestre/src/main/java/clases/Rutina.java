package clases;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private List<Tuple<Ejercicio, Float>> ejercicios;

    public Rutina() {
        this.ejercicios = new ArrayList<>();
    }

    public List<Tuple<Ejercicio, Float>> getEjercicios() {
        return this.ejercicios;
    }

    public void agregar_ejercicio(Ejercicio ejercicio, float duracion) {
        // implementación
    }

    public void eliminar_ejercicio(Ejercicio ejercicio) {
        // implementación
    }

    public void actualizar_calorias_quemadas_totales() {
        // implementación
    }

    public float getDuracion() {
        // implementación
        return 0.0f;
    }

    public float getCaloriasTotales() {
        // implementación
        return 0.0f;
    }
}