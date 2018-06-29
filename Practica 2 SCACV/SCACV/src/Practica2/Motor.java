package Practica2;

public class Motor {
	private static Motor instance = null;
	private boolean encendido;
	
	protected Motor() {
		encendido = false;
	}
	
	public static Motor getInstance() {
      if(instance == null) {
         instance = new Motor();
      }
      return instance;
    }
	
	public void apagarEncender() {
		encendido = !encendido;
	}
	
	public boolean getEstadoMotor() {
		return encendido;
	}

}
