package Practica2;

public class ObservadorVelocidadAlmacenada implements Observador {
	private ControlDeVelocidad control;
	private double velocidad;
	public ObservadorVelocidadAlmacenada(ControlDeVelocidad control){
		this.control = control;
	}
	public double get() {
		return velocidad;
	}
	@Override
	public void manejarEvento() {
		// TODO Auto-generated method stub
		velocidad=control.getVelocidadAlmacenada();
	}
}
