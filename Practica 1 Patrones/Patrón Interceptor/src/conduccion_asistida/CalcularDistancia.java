package conduccion_asistida;

import java.util.Random;

public class CalcularDistancia implements Filtro{

	private double distancia;
	
	public CalcularDistancia() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double ejecutar(Object o) {
		double velocidad= (double) o;
		
		//Este fragmento es un calculo aleatorio, no se calcula nada real
		Random r = new Random();
		distancia= velocidad*r.nextInt(100)/100;
			
		return distancia;
	}

	@Override
	public String toString() {
		return "La distancia calculada es de: " + distancia + " m";
	}
}
