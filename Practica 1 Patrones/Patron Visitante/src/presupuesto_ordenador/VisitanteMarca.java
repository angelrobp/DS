package presupuesto_ordenador;

public class VisitanteMarca implements VisitanteEquipo {

	private String marcas;

	public VisitanteMarca() {
		marcas = "";
	}

	public String getMarcas() {
		return marcas;
	}

	public String consultarMarca(Equipo e) {
		return e.nombre();
	}

	@Override
	public void Visitar(Equipo e, int tc) {
		marcas += consultarMarca(e) + "\n";
	}

}
