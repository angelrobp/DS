package Practica2;

import java.util.ArrayList;

public abstract class Observable {
	
	protected ArrayList<Observador> observadores;
	
	protected Observable(){
		observadores = new ArrayList<Observador>();
	}
	
	public void incluirObservador(Observador o){
		observadores.add(o);
	}
	
	public void notificarObservadores(){
		for(Observador o : observadores)
			o.manejarEvento();
	}
	
}
