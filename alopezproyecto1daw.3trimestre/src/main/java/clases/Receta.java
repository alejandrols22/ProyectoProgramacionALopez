package clases;

import java.util.HashSet;

public class Receta extends Entidad{

	private HashSet<Alimento> ingredientes;

    void agregar_ingrediente(Alimento alimento) {
        // Implementación aquí
    }

    void eliminar_ingrediente(Alimento alimento) {
        // Implementación aquí
    }

    void calcular_valores_nutricionales() {
        // Implementación aquí
    }

    float getCarbohidratos() {
        // Implementación aquí
        return 0.0f;
    }

    float getProteinas() {
        // Implementación aquí
        return 0.0f;
    }

    float getGrasas() {
        // Implementación aquí
        return 0.0f;
    }
}
