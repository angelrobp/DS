package carrera_bicicletas;

import java.util.ArrayList;

public class CarreraMontana extends Thread implements Carrera{

	private ArrayList<Bicicleta> bicicletas;
	private int abandono;
	
	public CarreraMontana(int n) {
		bicicletas = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			bicicletas.add(new BicicletaCarretera(TC.MONTANA, i));
		}
		abandono = n * 20 / 100;
		
		start();
	}
	
	@Override
	public void retirarBicicleta() {
		int i = bicicletas.size();
		int numero = 0;
		System.out.println("Retirada Montaña:");
		System.out.flush();
		while (abandono>0) {
			numero = (int) (Math.random() * (i-1));
			System.out.println(bicicletas.get(numero).toString());
			System.out.flush();
			bicicletas.set(numero, null);
			bicicletas.remove(numero);
			abandono--;
			i--;
		}	
	};
	
	public void run() { 
		long time_start, time_end;
		boolean finCarrera = false;
		boolean retirada = false;
		time_start = System.currentTimeMillis();
		while (!finCarrera) {
			
			time_end = System.currentTimeMillis();
			if ((time_end - time_start) >= 60000) {
				finCarrera = true;
			}
			else if (((time_end - time_start) >= 30000) && (!retirada)) {
				retirada = true;
				retirarBicicleta();
			}
		}
		System.out.println("Carrera de montaña acaba");
	}

	
}
