package presupuesto_ordenador;

public class VisitantePrecio implements VisitanteEquipo{

	private double precioAcumulado; 
	
	public VisitantePrecio() {
		precioAcumulado=0;
	}

	public double getPrecioAcumulado() {
		return precioAcumulado;
	}

	public double calcularDescuento (Equipo e, int tc) {
		return e.precioConDescuento(tc);	
	}
	
	@Override
	public void Visitar(Equipo e, int tc) {
		precioAcumulado += calcularDescuento(e, tc);	
	}

}
