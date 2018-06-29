package monitorizacion_meteorologia;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Pantalla extends JFrame implements Observador, Runnable{

	//private static  JFrame ;
	private static Panel panelSuperior;
	private static Label labelTemperatura;
	
	private int temp;
	
	Thread thr;
	
	public  Pantalla() {
		setTitle("Monitor temperatura");
		setSize(100,100);
	    panelSuperior = new Panel();
	    panelSuperior.setLayout(new BorderLayout());
	    getContentPane().add(panelSuperior);
	    labelTemperatura= new Label();
	    panelSuperior.add(labelTemperatura, BorderLayout.NORTH);
	    setVisible(true);
	    
	    this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
	    
	    thr=new Thread(this);
	    thr.start();
	}
	
	public static void refrescarPantalla() {
		labelTemperatura.setText(String.valueOf(ObservableTemperatura.getInstance().getTemperatura()));
	}
	
	@Override
	public void manejarEvento() {
		temp = ObservableTemperatura.getInstance().getTemperatura();
		
	}

	@Override
	public void run() {
		
		while (true) {
			refrescarPantalla();
		}		
	}

}
