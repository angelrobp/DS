package carrera_bicicletas;

public class FactoriaCarreraMontana implements FactoriaCarrera{

	@Override
	public Carrera crearCarrera(int n) {
		return new CarreraMontana(n);		
	}

}
