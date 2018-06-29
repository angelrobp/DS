package Practica2;

public class Acelerador extends Thread{
	private static Acelerador instance = null;
	ControlDeVelocidad control;
	
	protected Acelerador(ControlDeVelocidad control) {
		this.control = control;
	}
	public static Acelerador getInstance(ControlDeVelocidad control) {
      if(instance == null) {
         instance = new Acelerador(control);
      }
      return instance;
    }
	public void acelerar() {
		control.aumentarVelocidadEje(5);
		control.notificarObservadores();
	}
	public void reiniciar() {
		if(control.getVelocidadEje()<=control.getVelocidadEjeAlmacenada()) {
			acelerar();
		}else {
			control.setVelocidadEje(control.getVelocidadEjeAlmacenada());
			control.setEstado(Estado.MANTENIENDO);
		}
	}
	@Override
	public void run() {
		while(true) {
			if(!control.getEstadoFreno()) {
				if(control.getEstadoPalanca()==Estado.ACELERANDO)
					acelerar();
				if(control.getEstadoPalanca()==Estado.REINICIANDO)
					reiniciar();
			}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
