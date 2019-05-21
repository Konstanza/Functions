package interfaz.menu;

import interfaz.Colores;

import java.awt.Graphics;

import javax.swing.JPopupMenu;

public class PopupMenu extends JPopupMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g){
		g.setColor(Colores.AZUL);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
