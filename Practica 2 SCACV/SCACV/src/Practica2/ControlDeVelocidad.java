package Practica2;

public class ControlDeVelocidad extends Observable{
	private static ControlDeVelocidad instance = null;
	private double velocidad;
	private double velocidad_almacenada;
	private double rpm_almacenado;
	private Palanca palanca;
	private Freno freno;
	private Acelerador acelerador;
	private Eje eje;
	private Temporizador temporizador;
	private Motor motor;
	
	protected ControlDeVelocidad() {
		super();
		velocidad = 0;
		motor = Motor.getInstance();
		palanca = Palanca.getInstance();
		freno = Freno.getInstance(this);
		acelerador = Acelerador.getInstance(this);
		temporizador = Temporizador.getInstance(this);
		eje = Eje.getInstance();
		acelerador.start();
		freno.start();
		temporizador.start();
	}
	
	public static ControlDeVelocidad getInstance() {
      if(instance == null) {
         instance = new ControlDeVelocidad();
      }
      return instance;
    }
	
	public void calcularVelocidad() {
		velocidad=2*Math.PI/60*eje.getRadio()*eje.getRevoluciones()*3.6;
	}
	
	public void Frenar() {
		freno.cambiarEstadoFreno();
	}
	
	public void apagarSCACV() {
		setEstado(Estado.APAGADO_SCACV);
	}
	
	public void mantener() {
		setEstado(Estado.MANTENIENDO);
		memorizarVelocidad(getVelocidad(),eje.getRevoluciones());
	}
	
	public void Reiniciar() {
		setEstado(Estado.REINICIANDO);
	}
	
	public void Acelerar(){
		setEstado(Estado.ACELERANDO);
	}
	
	public Estado getEstadoPalanca() {
		return palanca.getEstado();
	}
	
	public void setEstado(Estado estado) {
		palanca.setEstado(estado);
		notificarObservadores();
	}
	
	public double getVelocidad() {
		calcularVelocidad();
		return velocidad;
	}
	
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
		if(observadores.size()>0)
			notificarObservadores();
	}
	
	public double getVelocidadAlmacenada() {
		return velocidad_almacenada;
	}
	
	public void arrancarApagarMotor() {
		motor.apagarEncender();
		if(!motor.getEstadoMotor())
			reiniciarVelocidadAlmacenada();
		setEstado(Estado.APAGADO_SCACV);
		//velocidadAlmacenada = 0
	}
	
	public void reiniciarVelocidadAlmacenada() {
		velocidad_almacenada = 0;
		rpm_almacenado = 0;
	}
	
	public boolean getEstadoMotor() {
		return motor.getEstadoMotor();
	}
	
	public boolean getEstadoFreno() {
		return freno.getEstado();
	}
	
	public void aumentarVelocidadEje(double n) {
		eje.aumentarVelocidad(n);
	}
	
	public double getVelocidadEjeAlmacenada() {
		return rpm_almacenado;
	}
	
	public double getVelocidadEje() {
		return eje.getRevoluciones();
	}
	
	public void setVelocidadEje(double d) {
		eje.setRevoluciones(d);
	}
	
	public void disminuirVelocidadEje(double n) {
		if(palanca.getEstado()==Estado.MANTENIENDO)
			setEstado(Estado.APAGADO_SCACV);
		eje.disminuirVelocidad(n);
	}
	
	public void memorizarVelocidad(double velocidad, double rpm) {
		this.velocidad_almacenada = velocidad;
		rpm_almacenado = rpm;
	}
	
	public double getRadioRueda() {
		return eje.getRadio();
	}
	
	public double getDistancia() {
		return temporizador.getDistancia();
	}
	
}
