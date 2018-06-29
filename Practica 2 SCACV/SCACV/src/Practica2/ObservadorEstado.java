package Practica2;

public class ObservadorEstado implements Observador {
	private ControlDeVelocidad control;
	private Estado estado;
	public ObservadorEstado(ControlDeVelocidad control){
		this.control = control;
	}
	public Estado get() {
		return estado;
	}
	@Override
	public void manejarEvento() {
		// TODO Auto-generated method stub
		estado=control.getEstadoPalanca();
	}
}
