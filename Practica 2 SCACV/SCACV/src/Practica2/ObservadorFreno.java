package Practica2;

public class ObservadorFreno implements Observador {
	private ControlDeVelocidad control;
	private boolean freno;
	public ObservadorFreno(ControlDeVelocidad control){
		this.control = control;
	}
	public boolean get() {
		return freno;
	}
	@Override
	public void manejarEvento() {
		// TODO Auto-generated method stub
		freno=control.getEstadoFreno();
	}
}
