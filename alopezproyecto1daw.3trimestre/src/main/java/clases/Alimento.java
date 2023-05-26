package clases;

public abstract class Alimento extends Entidad {
	protected float cantidad;
	protected float carbohidratos;
	protected float proteinas;
	protected float grasas;
	protected float calorias;

	public Alimento(String nombre, float cantidad, float carbohidratos, float proteinas, float grasas, float calorias) {
		super(nombre);
		this.cantidad = cantidad;
		this.carbohidratos = carbohidratos;
		this.proteinas = proteinas;
		this.grasas = grasas;
		this.calorias = calorias;
	}

	public Alimento(String nombre, float cantidad, float calorias) {
		super(nombre);
		this.cantidad = cantidad;
		this.calorias = calorias;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getCalorias() {
		return this.calorias;
	}

	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}
	 public float getCarbohidratos() {
	        return carbohidratos;
	    }

	    public void setCarbohidratos(float carbohidratos) {
	        this.carbohidratos = carbohidratos;
	    }

	    public float getProteinas() {
	        return proteinas;
	    }

	    public void setProteinas(float proteinas) {
	        this.proteinas = proteinas;
	    }

	    public float getGrasas() {
	        return grasas;
	    }

	    public void setGrasas(float grasas) {
	        this.grasas = grasas;
	    }

	   
	}
