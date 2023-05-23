package clases;

public class Ejercicio extends Entidad {
    private float calorias_quemadas_por_minuto;
    private short duracion;
    private Categoria categoria;

    public enum Categoria {
        // Define las categorías aquí...
    }

    public Ejercicio(String nombre, float calorias_quemadas_por_minuto, short duracion, Categoria categoria) {
        super(nombre);
        this.calorias_quemadas_por_minuto = calorias_quemadas_por_minuto;
        this.duracion = duracion;
        this.categoria = categoria;
    }

    public float getCalorias_quemadas_por_minuto() {
        return this.calorias_quemadas_por_minuto;
    }

    public void setCalorias_quemadas_por_minuto(float calorias_quemadas_por_minuto) {
        this.calorias_quemadas_por_minuto = calorias_quemadas_por_minuto;
    }

    public short getDuracion() {
        return this.duracion;
    }

    public void setDuracion(short duracion) {
        this.duracion = duracion;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void calcular_calorias_quemadas(int duracion) {
        // implementación
    }
}
