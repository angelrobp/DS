package Practica2;

public class ObservadorMotor implements Observador {
	private ControlDeVelocidad control;
	private boolean motor;
	public ObservadorMotor(ControlDeVelocidad control){
		this.control = control;
	}
	public boolean get() {
		return motor;
	}
	@Override
	public void manejarEvento() {
		// TODO Auto-generated method stub
		motor=control.getEstadoMotor();
	}
}
