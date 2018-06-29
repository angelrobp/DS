package conduccion_asistida;

public class GestorFiltros {

	private CadenaFiltros cadenaFiltros;
	
	/*
	 * Crear cadena de filtros
	 */
	public GestorFiltros(Interfaz o) {
		cadenaFiltros = new CadenaFiltros(o);
		
	}
	/*
	 * Añadir filtro a la cadena
	 */
	public void addFiltro(Filtro f) {
		cadenaFiltros.addFiltro(f);
	}
	/*
	 * Enviar petición a todos los filtros de la cadena
	 */
	public void peticionFiltro(double peticion) {
		cadenaFiltros.ejecutar(peticion);
	}

}
