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
	 * A�adir filtro a la cadena
	 */
	public void addFiltro(Filtro f) {
		cadenaFiltros.addFiltro(f);
	}
	/*
	 * Enviar petici�n a todos los filtros de la cadena
	 */
	public void peticionFiltro(double peticion) {
		cadenaFiltros.ejecutar(peticion);
	}

}
