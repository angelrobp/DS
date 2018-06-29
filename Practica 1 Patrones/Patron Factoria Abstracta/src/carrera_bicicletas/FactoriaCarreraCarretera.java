package carrera_bicicletas;

public class FactoriaCarreraCarretera implements FactoriaCarrera{

	@Override
	public Carrera crearCarrera(int n) {
		return new CarreraCarretera(n);		
	}

}
