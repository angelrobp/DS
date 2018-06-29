package Practica2;

public class Palanca {
	private static Palanca instance = null;
	private Estado estado;
	protected Palanca() {
		estado = Estado.APAGADO_SCACV;
	}
	public static Palanca getInstance() {
      if(instance == null) {
         instance = new Palanca();
      }
      return instance;
    }
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
