package conduccion_asistida;

public class Cliente {

	private GestorFiltros gestorFiltros;
	
	public void setGestorFiltros(GestorFiltros g) {
		gestorFiltros = g;
	}
	
	/*
	 * Enviar petición a todos los filtros de la cadena
	 */
	public void sentPeticion(double p) {
		gestorFiltros.peticionFiltro(p);
	}
}
