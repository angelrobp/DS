package carrera_bicicletas;

public class inicio {

	public inicio() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		FactoriaCarreraCarretera carreraCarretera = new FactoriaCarreraCarretera();
		FactoriaCarreraMontana carreraMontana = new FactoriaCarreraMontana();

		System.out.println("Carrera de carretera empieza");
		carreraCarretera.crearCarrera(10);
		System.out.println("Carrera de montaña empieza");
		carreraMontana.crearCarrera(10);
	}

}
