package interfaz.botones;

import interfaz.Colores;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class BotonColor extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Color BORDE;
	private Color seleccion;
	
	public BotonColor(Color color){
		super("");
		setBackground( color);
		BORDE = Colores.AZUL;
		seleccion = Colores.BLANCO;
	}
	

	protected void paintComponent(Graphics g){
		if(this.getModel().isRollover()){
			
			g.setColor(seleccion);
			
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		else {
			g.setColor(BORDE);
			
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
		}
		
		int borde = 2;
		g.setColor(getBackground());
		g.fillRect(borde, borde, this.getWidth()-borde*2, this.getHeight()-borde*2);
		
	}
}
