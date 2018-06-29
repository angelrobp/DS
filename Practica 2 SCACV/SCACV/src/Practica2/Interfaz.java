package Practica2;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

public class Interfaz extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private JToggleButton BotonAcelerar;
    private JButton BotonArrancar;
    private JButton BotonFrenar;
    private JToggleButton BotonMantener;
    private JToggleButton BotonReiniciar;
    private JToggleButton BotonSCACV;
    private JLabel EtiqEstado;
    private JLabel EtiqVelocidad;
    private JLabel distancia;
    private ButtonGroup grupo;
    private JLabel labelEstado;
    private JLabel labelVelocidadActual;
    private JLabel labelVelocidadAlmacenada;
    private JLabel labelDistancia;
    private JLabel labelGasolina;
    private JPanel panel;
    private JPanel panelPalanca;
    private JPanel panelVelocidad;
    private JPanel panelMotor;
    private JLabel velocidadAlmacenada;
    private JLabel velocidadAlmacenado;
    private DecimalFormat decimales;
    private DecimalFormat decimales_2;
	private Estado estado;
	private ControlDeVelocidad control;
	private ObservadorVelocidad observadorVelocidad;
	private ObservadorVelocidadAlmacenada observadorVelocidadAlmacenada;
	private ObservadorFreno observadorFreno;
	private ObservadorEstado observadorEstado;
	private ObservadorMotor observadorMotor;
	private ObservadorDistancia observadorDistancia;
    
    public Interfaz() {
        initComponents();
    }

    private void initComponents() {
    	control = ControlDeVelocidad.getInstance();
		decimales = new DecimalFormat("0.0");
		decimales_2 = new DecimalFormat("0.000");
        grupo = new ButtonGroup();
        panelPalanca = new JPanel();
        BotonAcelerar = new JToggleButton();
        BotonMantener = new JToggleButton();
        BotonReiniciar = new JToggleButton();
        BotonSCACV = new JToggleButton();
        panel = new JPanel();
        EtiqEstado = new JLabel();
        labelEstado = new JLabel();
        panelVelocidad = new JPanel();
        EtiqVelocidad = new JLabel();
        labelVelocidadActual = new JLabel();
        labelDistancia = new JLabel();
        distancia = new JLabel();
        panelMotor = new JPanel();
        BotonArrancar = new JButton();
        BotonFrenar = new JButton();
        labelVelocidadAlmacenada = new JLabel();
        velocidadAlmacenada = new JLabel();
        velocidadAlmacenado = new JLabel();
        labelGasolina = new JLabel();

        grupo.add(BotonAcelerar);
        grupo.add(BotonReiniciar);
        grupo.add(BotonMantener);


        panelPalanca.setBackground(new Color(153, 153, 153));
        
        BotonArrancar.setText("Arrancar");
        BotonFrenar.setText("Frenar");
        BotonAcelerar.setText("Acelerar");
        BotonMantener.setText("Mantener");
        BotonReiniciar.setText("Reiniciar");
        BotonSCACV.setText("Apagar");

        grupo.add(BotonAcelerar);
        grupo.add(BotonMantener);        
        grupo.add(BotonReiniciar);
        grupo.add(BotonSCACV);
        
        EtiqEstado.setForeground(new Color(255, 51, 51));
        EtiqEstado.setText("Apagado");
        labelEstado.setText("Estado");
        EtiqVelocidad.setText("0 km/h");
        labelVelocidadActual.setText("Velocidad actual:");
        labelDistancia.setText("Distancia:");
        distancia.setText("0 km");
        labelVelocidadAlmacenada.setText("Velocidad almacenada:");
        velocidadAlmacenado.setText("0 km/h");
        labelGasolina.setText("Gasolina: ");
        
        observadorVelocidad = new ObservadorVelocidad(control);
        observadorVelocidadAlmacenada = new ObservadorVelocidadAlmacenada(control);
        observadorMotor = new ObservadorMotor(control);
        observadorFreno = new ObservadorFreno(control);
        observadorEstado = new ObservadorEstado(control);
        observadorDistancia = new ObservadorDistancia(control);
        control.incluirObservador(observadorVelocidad);
        control.incluirObservador(observadorVelocidadAlmacenada);
        control.incluirObservador(observadorMotor);
        control.incluirObservador(observadorFreno);
        control.incluirObservador(observadorEstado);
        control.incluirObservador(observadorDistancia);
		
		BotonArrancar.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				control.arrancarApagarMotor();
				if(control.getEstadoMotor()) {
					BotonArrancar.setText("Apagar");
					BotonArrancar.setForeground(Color.RED);
					EtiqEstado.setForeground(Color.GREEN);
				}else {
					BotonArrancar.setText("Arrancar");
					BotonArrancar.setForeground(Color.GREEN);
					EtiqEstado.setForeground(Color.RED);
				}
			}
		});
		BotonAcelerar.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				control.Acelerar();
			}
		});
		BotonSCACV.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				control.apagarSCACV();
			}
		});
		BotonFrenar.addMouseListener(new java.awt.event.MouseAdapter()
	    {
	        public void mousePressed(java.awt.event.MouseEvent evt)
	        {
	        	control.Frenar();
	        }
	        public void mouseReleased(java.awt.event.MouseEvent evt)
	        {
	        	control.Frenar();
	        }
	    });
		BotonReiniciar.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				control.Reiniciar();
			}
		});
		BotonMantener.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				control.mantener();
			}
		});

        GroupLayout panelPalancaLayout = new GroupLayout(panelPalanca);
        panelPalanca.setLayout(panelPalancaLayout);
        panelPalancaLayout.setHorizontalGroup(
            panelPalancaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelPalancaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelPalancaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelPalancaLayout.createSequentialGroup()
                        .addComponent(BotonAcelerar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(BotonMantener)
                        .addContainerGap())
                    .addGroup(panelPalancaLayout.createSequentialGroup()
                        .addComponent(BotonReiniciar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotonSCACV)
                        .addGap(18, 18, 18))))
        );
        panelPalancaLayout.setVerticalGroup(
            panelPalancaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelPalancaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPalancaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonAcelerar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonMantener, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(panelPalancaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonReiniciar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonSCACV, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panel.setBackground(new Color(153, 153, 153));
        panel.setForeground(new Color(153, 153, 153));
        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(EtiqEstado, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                    .addGroup(GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                        .addComponent(labelEstado)
                        .addGap(13, 13, 13)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelEstado)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EtiqEstado, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        panelVelocidad.setBackground(new Color(153, 153, 153));
        GroupLayout panelVelocidadLayout = new GroupLayout(panelVelocidad);
        panelVelocidad.setLayout(panelVelocidadLayout);
        panelVelocidadLayout.setHorizontalGroup(
            panelVelocidadLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.LEADING, panelVelocidadLayout.createSequentialGroup()
            	.addGap(40,40,40)
                .addComponent(labelVelocidadActual)
                .addGap(90, 90, 90))
            .addGroup(panelVelocidadLayout.createSequentialGroup()
                .addGroup(panelVelocidadLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelVelocidadLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(labelDistancia, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelVelocidadLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(EtiqVelocidad, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelVelocidadLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(distancia, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVelocidadLayout.setVerticalGroup(
            panelVelocidadLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelVelocidadLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelVelocidadActual)
                .addComponent(EtiqVelocidad, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDistancia)
                .addComponent(distancia, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        panelMotor.setBackground(new Color(153, 153, 153));
        GroupLayout panelMotorLayout = new GroupLayout(panelMotor);
        panelMotor.setLayout(panelMotorLayout);
        panelMotorLayout.setHorizontalGroup(
            panelMotorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMotorLayout.createSequentialGroup()
                .addGroup(panelMotorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelMotorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BotonFrenar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMotorLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(panelMotorLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(BotonArrancar, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelMotorLayout.createSequentialGroup()
                                .addComponent(velocidadAlmacenada)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(velocidadAlmacenado, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, panelMotorLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelVelocidadAlmacenada)
                .addGap(32, 32, 32))
        );
        panelMotorLayout.setVerticalGroup(
            panelMotorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMotorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelVelocidadAlmacenada, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addGroup(panelMotorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelMotorLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(velocidadAlmacenada))
                    .addGroup(panelMotorLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(velocidadAlmacenado, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(BotonArrancar)
                .addGap(10, 10, 10)
                .addComponent(BotonFrenar)
                .addGap(9, 9, 9))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(panelMotor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelPalanca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelVelocidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelMotor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelVelocidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelPalanca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }
    
    public void actualizarBotones(){
		if(!observadorFreno.get() && observadorMotor.get()) {
			switch(observadorEstado.get()) {
				case ACELERANDO:
					BotonAcelerar.setSelected(true);
					BotonArrancar.setEnabled(false);
					BotonMantener.setEnabled(true);
					BotonReiniciar.setEnabled(false);
					BotonFrenar.setEnabled(true);
					BotonSCACV.setEnabled(true);
					break;
				case APAGADO_SCACV:
					BotonSCACV.setSelected(true);
					BotonArrancar.setEnabled(true);
					BotonMantener.setEnabled(false);
					BotonAcelerar.setEnabled(true);
					if(control.getVelocidadAlmacenada()>0)
						BotonReiniciar.setEnabled(true);
					else
						BotonReiniciar.setEnabled(false);
					BotonFrenar.setEnabled(true);
					break;
				case MANTENIENDO:
					BotonMantener.setSelected(true);
					BotonArrancar.setEnabled(false);
					BotonFrenar.setEnabled(true);
					BotonAcelerar.setEnabled(true);
					BotonReiniciar.setEnabled(false);
					BotonSCACV.setEnabled(true);
					break;
				case REINICIANDO:
					BotonMantener.setEnabled(false);
					BotonArrancar.setEnabled(false);
					BotonAcelerar.setEnabled(true);
					BotonReiniciar.setSelected(true);
					BotonFrenar.setEnabled(true);
					break;
			}
			EtiqEstado.setText(estado.toString());
		}
		if(observadorFreno.get()) {
			EtiqEstado.setText("FRENANDO");
		}
		if(!observadorMotor.get()) {
			BotonMantener.setEnabled(false);
			BotonArrancar.setEnabled(true);
			BotonAcelerar.setEnabled(false);
			BotonFrenar.setEnabled(false);
			BotonReiniciar.setEnabled(false);
			BotonSCACV.setEnabled(false);
			EtiqEstado.setText("NO_ARRANCADO");
		}
	}

    public void refrescarPantalla(){
    	estado = control.getEstadoPalanca();
		EtiqVelocidad.setText(decimales.format(observadorVelocidad.get())+" km/h");
		velocidadAlmacenado.setText(decimales.format(observadorVelocidadAlmacenada.get())+" km/h");
		distancia.setText(decimales_2.format(observadorDistancia.get())+" Km");
		actualizarBotones();
		repaint();
	}

	@Override
	public void run() {
		while(true){
			refrescarPantalla();
		}
	}
}