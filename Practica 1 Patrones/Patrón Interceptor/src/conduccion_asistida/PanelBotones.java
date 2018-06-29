package conduccion_asistida;

import java.awt.*;
import javax.swing.*;

public class PanelBotones extends JPanel {
	private javax.swing.JButton BotonAcelerar;
	private javax.swing.JToggleButton BotonEncender;
	private javax.swing.JLabel EtiqMostrarEstado;
	private JPanel PanelSuperior, PanelInferior;

	public PanelBotones() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.green,4));
		
		PanelSuperior = new JPanel(new GridBagLayout());
		PanelSuperior.setBorder(BorderFactory.createRaisedSoftBevelBorder());

		EtiqMostrarEstado = new JLabel("APAGADO");
		EtiqMostrarEstado.setForeground(Color.RED);
		PanelSuperior.add(EtiqMostrarEstado);
		
		PanelInferior = new JPanel(new FlowLayout());
		PanelInferior.setBorder(BorderFactory.createRaisedSoftBevelBorder());

		BotonAcelerar = new JButton("Acelerar");
		PanelInferior.add(BotonAcelerar);

		BotonEncender = new JToggleButton("Encender", false);
		BotonEncender.setForeground(Color.RED);
		PanelInferior.add(BotonEncender);
		PanelInferior.setPreferredSize(new Dimension(225,75));
		
		add(PanelSuperior);
		add(PanelInferior);
		
		BotonAcelerar.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            BotonAcelerarActionPerformed(evt);
	        }
	    });
		
		BotonEncender.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            BotonEncenderActionPerformed(evt);
	        }
	    });
		
	};// constructor
	
	synchronized private void BotonAcelerarActionPerformed(java.awt.event.ActionEvent evt) {

		if (this.BotonEncender.isSelected()) {

			this.EtiqMostrarEstado.setText("ACELERANDO ");
			this.EtiqMostrarEstado.setForeground(Color.green);
			repaint();
		}
	};

	synchronized private void BotonEncenderActionPerformed(java.awt.event.ActionEvent evt) {
		if (BotonEncender.isSelected()) {
			this.BotonEncender.setText("Apagar");
			this.EtiqMostrarEstado.setText("ENCENDIDO");
			this.EtiqMostrarEstado.setForeground(Color.blue);
			this.BotonEncender.setForeground(Color.red);
			repaint();
		} else {
			this.BotonEncender.setText("Encender");
			this.EtiqMostrarEstado.setText("APAGADO");
			this.EtiqMostrarEstado.setForeground(Color.red);
			this.BotonEncender.setForeground(Color.blue);
			repaint();
		}
	};
}
