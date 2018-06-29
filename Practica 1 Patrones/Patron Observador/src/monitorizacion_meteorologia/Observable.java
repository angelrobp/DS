package monitorizacion_meteorologia;

import java.util.ArrayList;

public class Observable {

	protected ArrayList<Observador> observadores;
	public void incluirObservador(Observador o) {
		observadores.add(o);
	};
	public void notificarObservador() {
		for (int i = 0; i < observadores.size(); i++) {
			observadores.get(i).manejarEvento();
		}
	};

}
