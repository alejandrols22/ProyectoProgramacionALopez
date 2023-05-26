package clases;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private List<Tuple<Ejercicio, Float>> ejercicios;
    private float caloriasTotales;

    public Rutina() {
        this.ejercicios = new ArrayList<>();
        this.caloriasTotales = 0.0f;
    }

    public List<Tuple<Ejercicio, Float>> getEjercicios() {
        return this.ejercicios;
    }

    public void agregar_ejercicio(Ejercicio ejercicio, float duracion) {
        Tuple<Ejercicio, Float> ejercicioConDuracion = new Tuple<>(ejercicio, duracion);
        this.ejercicios.add(ejercicioConDuracion);
        actualizar_calorias_quemadas_totales();
    }

    public void eliminar_ejercicio(Ejercicio ejercicio) {
        ejercicios.removeIf(tuple -> tuple.getFirst().equals(ejercicio));
        actualizar_calorias_quemadas_totales();
    }

    public void actualizar_calorias_quemadas_totales() {
        this.caloriasTotales = 0.0f;
        for(Tuple<Ejercicio, Float> ejercicio : this.ejercicios) {
            this.caloriasTotales += ejercicio.getFirst().getCalorias_quemadas_por_minuto() * ejercicio.getSecond();
        }
    }

    public float getDuracion() {
        float duracionTotal = 0.0f;
        for(Tuple<Ejercicio, Float> ejercicio : this.ejercicios) {
            duracionTotal += ejercicio.getSecond();
        }
        return duracionTotal;
    }

    public float getCaloriasTotales() {
        return this.caloriasTotales;
    }
}

