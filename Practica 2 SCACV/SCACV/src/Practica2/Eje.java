package Practica2;

public class Eje {
	private static Eje instance = null;
	private double rpm;
	private double rpmTotales;
	private double radioRueda;
	
	protected Eje() {
		rpm = 0.0;
		rpmTotales = 0.0;
		radioRueda = 0.3;
	}
	
	public static Eje getInstance() {
      if(instance == null) {
         instance = new Eje();
      }
      return instance;
    }
	
	public double getRevoluciones() {
		return rpm;
	}
	
	public void setRevoluciones(double revoluciones) {
		this.rpm = revoluciones;
	}
	
	public double getRadio() {
		return radioRueda;
	}
	
	public void aumentarVelocidad(double n) {
		rpm += n;
		rpmTotales += n;
	}
	
	public void disminuirVelocidad(double n) {
		rpm -= n;
		if(rpm<=0) {
			rpm=0;
		}
	}
	
	public double getRevolucionesTotales() {
		return rpmTotales;
	}
}
