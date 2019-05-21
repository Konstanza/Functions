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

public class DialogSalir extends Dialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogSalir(Ventana ventana){
		super(ventana, 275, 100);
		
		this.setLocationRelativeTo(null);
		
		
		OptionPane optionPane = new OptionPane();
		optionPane.setBackground(Colores.BLANCO);
		optionPane.setMessage("¿Está seguro de que desea salir?");
		optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
		optionPane.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());
		
		BotonOpaco[] a = new BotonOpaco[2];
		
		a[0] = new BotonOpaco("Si");
		a[1] = new BotonOpaco("No");
		
		a[0].setPreferredSize(new Dimension(80, 25));
		a[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		a[1].setPreferredSize(new Dimension(80, 25));
		a[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				setLocationRelativeTo(null);
				ventana.getCanvas().dibujar();
			}
			
		});
		
		optionPane.setOptions(a);
		
		contentPane.add(optionPane);
		
	}
}
