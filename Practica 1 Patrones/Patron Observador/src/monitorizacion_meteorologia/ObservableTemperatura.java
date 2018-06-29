package monitorizacion_meteorologia;

import java.util.ArrayList;

public class ObservableTemperatura extends Observable {

	private int temperatura;
	private static ObservableTemperatura instance = null;

	public static ObservableTemperatura getInstance() {
		if (instance == null) {
			instance = new ObservableTemperatura();
		}
		return instance;
	}

	public ObservableTemperatura() {
		temperatura = 0;
		observadores = new ArrayList<>();
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

}
