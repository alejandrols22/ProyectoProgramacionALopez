package clases;

import java.util.HashSet;

public class Receta extends Entidad {
    private HashSet<Alimento> ingredientes;
    private float caloriasTotales;
    private float carbohidratos;
    private float proteinas;
    private float grasas;

    public Receta(String nombre) {
        super(nombre);
        this.ingredientes = new HashSet<>();
        this.carbohidratos = 0.0f;
        this.proteinas = 0.0f;
        this.grasas = 0.0f;
    }

    public HashSet<Alimento> getIngredientes() {
        return ingredientes;
    }

    public void agregarIngrediente(Alimento alimento) {
        ingredientes.add(alimento);
        calcularValoresNutricionales();
    }

    public void eliminarIngrediente(Alimento alimento) {
        ingredientes.remove(alimento);
        calcularValoresNutricionales();
    }

    public void calcularValoresNutricionales() {
        this.carbohidratos = 0.0f;
        this.proteinas = 0.0f;
        this.grasas = 0.0f;
        this.caloriasTotales = 0.0f;

        for (Alimento alimento : this.ingredientes) {
            this.carbohidratos += alimento.getCarbohidratos() * alimento.getCantidad() / 100f;
            this.proteinas += alimento.getProteinas() * alimento.getCantidad() / 100f;
            this.grasas += alimento.getGrasas() * alimento.getCantidad() / 100f;
            this.caloriasTotales += alimento.getCalorias() * alimento.getCantidad() / 100f;
        }
    }

    public float getCarbohidratos() {
        return carbohidratos;
    }

    public float getProteinas() {
        return proteinas;
    }

    public float getGrasas() {
        return grasas;
    }

    public float getCaloriasTotales() {
        return caloriasTotales;
    }
}