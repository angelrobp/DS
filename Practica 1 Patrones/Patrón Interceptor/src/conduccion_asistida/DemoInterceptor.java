package conduccion_asistida;

public class DemoInterceptor {

	public DemoInterceptor() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		GestorFiltros gestorFiltros = new GestorFiltros(new Interfaz());
		gestorFiltros.addFiltro(new CalcularDistancia());
		gestorFiltros.addFiltro(new CalcularVelocidad());
		Cliente cliente = new Cliente();
		cliente.setGestorFiltros(gestorFiltros);
		cliente.sentPeticion(500); // numero inicial de vueltas del eje

	}

}
