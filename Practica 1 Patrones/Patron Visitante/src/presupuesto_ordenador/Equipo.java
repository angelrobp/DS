package presupuesto_ordenador;

public abstract class Equipo {

	private String nombre;
	protected double precio;
	
	public Equipo(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public String nombre() {
		return nombre;
	}

	public abstract double precioNeto();

	public abstract double precioConDescuento(int tc);

	public abstract void aceptar(VisitanteEquipo ve, int tc);

}
