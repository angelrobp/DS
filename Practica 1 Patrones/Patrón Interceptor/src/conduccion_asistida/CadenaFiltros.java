package conduccion_asistida;

import java.util.ArrayList;

public class CadenaFiltros {

	private ArrayList<Filtro> filtros = new ArrayList<>();
	private Interfaz objetivo;

	public CadenaFiltros(Interfaz o) {
		objetivo = o;
	}
	
	public void ejecutar(double peticion) {
		for (Filtro filtro : filtros) {
			filtro.ejecutar(peticion);
			System.out.println(filtro.toString());
		}
		objetivo.ejecutar(peticion);
	}

	public void setObjetivo(Interfaz objetivo) {
		this.objetivo = objetivo;
	}

	public void addFiltro(Filtro f) {
		filtros.add(f);
	}

}
