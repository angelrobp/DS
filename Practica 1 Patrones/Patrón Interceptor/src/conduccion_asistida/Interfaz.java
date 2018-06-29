package conduccion_asistida;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Interfaz extends JFrame{
	
	
	public Interfaz() {
		PanelBotones panelBotones = new PanelBotones();
		
		setTitle("Practica-1.4");
		
		getContentPane().add(panelBotones);
		panelBotones.setPreferredSize(new Dimension(450,150));
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		
	};// constructor

	public void ejecutar(double peticion) {
		this.pack();
		this.setVisible(true);
		System.out.println("Para un numero de vueltas iniciales del eje= " + peticion);
	}
}
