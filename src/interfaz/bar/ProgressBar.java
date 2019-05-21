package interfaz.bar;

import interfaz.Colores;

import java.awt.Graphics;

import javax.swing.JProgressBar;

public class ProgressBar extends JProgressBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProgressBar(int min, int max){
		super(min, max);
		setBackground(Colores.BLANCO);
		setForeground(Colores.NEGRO);
		
	}
	
	protected void paintComponent(Graphics g){
		g.setColor(getForeground());
		
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int borde = 1;
		
		int pix = getValue()*(getWidth()-borde*2)/getMaximum();
		g.fillRect(borde, borde, pix, getHeight()-borde*2);
		
		g.setColor(getBackground());
		g.fillRect(borde+pix, borde, getWidth()-pix, getHeight()-borde*2);	
	}
}
