package monitorizacion_meteorologia;

import java.util.Random;

public class Simulador extends Thread{

	int rango_temperatura;
	ObservableTemperatura observableTemperatura;
	
	public Simulador() {
		observableTemperatura = ObservableTemperatura.getInstance();
		Pantalla observador = new Pantalla();
		observableTemperatura.incluirObservador(observador);
		rango_temperatura = 40;
		this.start();
	}
	
	public void run() {
		Random r= new Random(rango_temperatura); 
		int temperatura;
		while (true){
		 temperatura= r.nextInt(rango_temperatura);
		 observableTemperatura.setTemperatura(temperatura);
		 observableTemperatura.notificarObservador();
		 try {sleep(5000);}
		 catch(java.lang.InterruptedException e){
		 e.printStackTrace();
		 }
		}
	}
	
	public static void main (String [ ] args) {
		Simulador simulador = new Simulador();
	}

}
