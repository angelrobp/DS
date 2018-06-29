package Practica2;

public class ObservadorVelocidad implements Observador {
	private ControlDeVelocidad control;
	private double velocidad;
	public ObservadorVelocidad(ControlDeVelocidad control){
		this.control = control;
	}
	public double get() {
		return velocidad;
	}
	@Override
	public void manejarEvento() {
		// TODO Auto-generated method stub
		velocidad=control.getVelocidad();
	}
}
