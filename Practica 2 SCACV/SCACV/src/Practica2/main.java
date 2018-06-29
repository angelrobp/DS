package Practica2;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class main extends JApplet {
	private static final long serialVersionUID = 1L;

    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    crearGui();
                }
            });
        } catch (Exception e) {
            System.err.println("No se ha podido crear la interfaz: " + e);
        }
    }
    
    private void crearGui() {
    	Interfaz panel = new Interfaz();
    	Thread thread1 = new Thread(panel, "Thread 1");
		thread1.start();
    	Frame title = (Frame)this.getParent().getParent();
        title.setTitle("Práctica 2");
		resize(new Dimension(500,318));
		getContentPane().add(panel);
    }
}
