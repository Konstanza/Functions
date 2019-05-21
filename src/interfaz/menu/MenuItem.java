package interfaz.menu;

import interfaz.Colores;
import interfaz.Fuentes;

import java.awt.Graphics;

import javax.swing.JMenuItem;

public class MenuItem extends JMenuItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuItem(String string){
		setText(string);
		setFont(Fuentes.PRINCIPAL);
	}
	
	protected void paintComponent(Graphics g){
		if(isArmed()){
			g.setColor(Colores.AZUL_CLARO);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Colores.BLANCO_AZULADO);
			g.drawString(getText(), getHorizontalTextPosition()+15, getHeight()/2+getFont().getSize()/2);
		}
		else {
			g.setColor(Colores.BLANCO);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Colores.AZUL);
			g.drawString(getText(), getHorizontalTextPosition()+15, getHeight()/2+getFont().getSize()/2);
		}
		
	}
}
