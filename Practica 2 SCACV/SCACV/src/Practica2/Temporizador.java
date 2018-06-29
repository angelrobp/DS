package Practica2;

public class Temporizador extends Thread{
	private static Temporizador instance = null;
	private double distancia;
	private ControlDeVelocidad control;
	
	protected Temporizador(ControlDeVelocidad control) {
		distancia = 0;
		this.control = control;
	}
	public static Temporizador getInstance(ControlDeVelocidad control) {
      if(instance == null) {
         instance = new Temporizador(control);
      }
      return instance;
    }
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	@Override
	public void run() {
		while(true) {
			double velocidad = control.getVelocidad();
			distancia += velocidad*0.1/3600;
			control.notificarObservadores();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
