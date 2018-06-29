package presupuesto_ordenador;

public class VisitantePrecioNeto implements VisitanteEquipo{

	private double precioAcumulado;
	
	public VisitantePrecioNeto() {
		precioAcumulado=0;
	}

	public double getPrecioAcumulado() {
		return precioAcumulado;
	}
	
	public double calcularPrecio (Equipo e) {
		return e.precioNeto();	
	}
	
	@Override
	public void Visitar(Equipo e, int tc) {
		precioAcumulado += calcularPrecio(e);		
	}

}
