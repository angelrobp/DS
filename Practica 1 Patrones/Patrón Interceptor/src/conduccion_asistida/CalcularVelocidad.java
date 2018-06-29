package conduccion_asistida;

import java.util.Random;

public class CalcularVelocidad implements Filtro {

	private double velocidad;
	
	public CalcularVelocidad() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double ejecutar(Object o) {

		double distancia = (double) o;

		// Este fragmento es un calculo aleatorio, no se calcula nada real
		Random r = new Random();
		velocidad = distancia * r.nextInt(100) / 100;

		return velocidad;
	}
	
	@Override
	public String toString() {
		return "La velocidad calculada es de: " + velocidad + " m/s";
	}

}
