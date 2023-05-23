package clases;

public class Alimento extends Entidad {
    protected float cantidad;

    public Alimento(String nombre, float cantidad) {
        super(nombre);
        this.cantidad = cantidad;
    }

    public float getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public void calcular_calorias() {
        // implementación
    }
}
