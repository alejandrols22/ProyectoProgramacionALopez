package clases;

import java.util.HashSet;

public class Receta extends Entidad {
    private HashSet<Alimento> ingredientes;

    public Receta(String nombre) {
        super(nombre);
        this.ingredientes = new HashSet<>();
    }

    public HashSet<Alimento> getIngredientes() {
        return this.ingredientes;
    }

    public void agregar_ingrediente(Alimento alimento) {
        // implementación
    }

    public void eliminar_ingrediente(Alimento alimento) {
        // implementación
    }

    public void calcular_valores_nutricionales() {
        // implementación
    }

    public float getCarbohidratos() {
        // implementación
        return 0.0f;
    }

    public float getProteinas() {
        // implementación
        return 0.0f;
    }

    public float getGrasas() {
        // implementación
        return 0.0f;
    }
}

