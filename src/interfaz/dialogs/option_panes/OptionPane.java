package interfaz.dialogs.option_panes;

import interfaz.Colores;

import java.awt.Graphics;

import javax.swing.JOptionPane;

public class OptionPane extends JOptionPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OptionPane(){
		this.setUI(OptionPaneUI.createUI(this));
	}
	
	protected void paintComponent(Graphics g){
		g.setColor(Colores.GRIS_CLARO);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
