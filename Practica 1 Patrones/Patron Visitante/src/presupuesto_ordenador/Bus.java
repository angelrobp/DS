package presupuesto_ordenador;

public class Bus extends Equipo{

	public Bus(String nombre, double precio) {
		super(nombre, precio);
	}

	@Override
	public double precioNeto() {
		return precio;
	}

	@Override
	public double precioConDescuento(int tc) {
		return precio - ((precio*tc)/100);
	}

	@Override
	public void aceptar(VisitanteEquipo ve, int tc) {
		ve.Visitar(this, tc);
		
	}

}
