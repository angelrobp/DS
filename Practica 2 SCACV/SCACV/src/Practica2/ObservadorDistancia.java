package Practica2;

public class ObservadorDistancia implements Observador{
	private ControlDeVelocidad control;
	private double distancia;
	public ObservadorDistancia(ControlDeVelocidad control){
		this.control = control;
	}
	public double get() {
		return distancia;
	}
	@Override
	public void manejarEvento() {
		// TODO Auto-generated method stub
		distancia=control.getDistancia();
	}
}
