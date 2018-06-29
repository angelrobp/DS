package carrera_bicicletas;

public abstract class Bicicleta {

	private TC tipo;
	private int numeroParticipacion;
	
	public Bicicleta(TC tipo, int numeroParticipacion) {
		this.tipo = tipo;
		this.numeroParticipacion = numeroParticipacion;
	}

	 public TC getTipo() {
		return tipo;
		 
	 };
	 public void setTipo(TC tipo) {
		 this.tipo = tipo;
	 }

	@Override
	public String toString() {
		return  "Bicicleta - " + numeroParticipacion + " - " + tipo;
	};
	 
	 
}
