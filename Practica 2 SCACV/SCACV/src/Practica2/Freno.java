package Practica2;

public class Freno extends Thread{
	private static Freno instance = null;
	ControlDeVelocidad control;
	private boolean estado;
	
	protected Freno(ControlDeVelocidad control) {
		this.control = control;
		estado = false;
	}
	
	public static Freno getInstance(ControlDeVelocidad control) {
      if(instance == null) {
         instance = new Freno(control);
      }
      return instance;
    }
	
	public void frenar(double n) {
		control.disminuirVelocidadEje(n);
		control.notificarObservadores();
	}
	
	public void cambiarEstadoFreno() {
		estado = !estado;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public void run() {
		while(true) {
			if(estado)
				frenar(5);
			else if(!control.getEstadoMotor())
				frenar(1);
			else if(control.getEstadoPalanca()==Estado.APAGADO_SCACV)
				frenar(1);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
