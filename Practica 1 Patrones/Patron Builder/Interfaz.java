package builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Interfaz extends JFrame{
	private JTextField fieldId, fieldClave;
	private JLabel labelId, labelClave;
	private JButton boton_Guardar;
	private JPanel panelGeneral, panelSuperior, panelInferior;
	public Interfaz() {
		setSize(new Dimension(500, 500));
		panelGeneral = new JPanel();
		panelGeneral.setLayout(new FlowLayout());
		panelGeneral.setBorder(BorderFactory.createLineBorder(Color.green, 4));
		
		panelSuperior = new JPanel(new FlowLayout());
		panelSuperior.setSize(500, 250);
		panelSuperior.setBorder(BorderFactory.createRaisedBevelBorder());
		
		fieldId = new JTextField();
		panelSuperior.add(fieldId);
		fieldClave = new JTextField();
		panelSuperior.add(fieldClave);
		
		panelInferior = new JPanel(new FlowLayout());
		panelInferior.setBorder(BorderFactory.createRaisedBevelBorder());
		panelInferior.setPreferredSize(new Dimension(225, 75));
		boton_Guardar = new JButton("Crear");
		panelInferior.add(boton_Guardar);
		
		panelGeneral.add(panelSuperior);
		panelGeneral.add(panelInferior);
		
		add(panelGeneral);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent we) {
			System.exit(0);
		}
		});
		boton_Guardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				BotonGuardarActionPerformed(evt);
				
			}
		});
	}
	
	private void BotonGuardarActionPerformed(ActionEvent evt) {
		Product automovil = new Product.BuilderProduct("audi01", "01").marca("Audi").modelo("A8").build();
		System.out.println(automovil.toString());
	}
}
