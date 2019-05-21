package interfaz.dialogs;

import interfaz.Colores;
import interfaz.botones.BotonOpaco;
import interfaz.dialogs.option_panes.OptionPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import plano.DibujoPlano;
import principal.ventanas.Ventana;

public class DialogOk extends Dialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogOk(Ventana ventana, String mensaje) {
		super(ventana, 275, 100);
		
		this.setLocationRelativeTo(null);
		
		OptionPane optionPane = new OptionPane();
		optionPane.setBackground(Colores.BLANCO);
		optionPane.setMessage(mensaje);
		optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
		optionPane.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		
		BotonOpaco a;
		
		a = new BotonOpaco("Aceptar");
		
		
		a.setPreferredSize(new Dimension(80, 25));
		a.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ventana.getCanvas().dibujar();
			}
			
		});
		
		optionPane.setOptions(new BotonOpaco[]{a});
		
		contentPane.add(optionPane);
		
		
		
	}

}
