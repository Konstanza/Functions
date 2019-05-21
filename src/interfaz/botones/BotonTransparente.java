package interfaz.botones;

import interfaz.Colores;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class BotonTransparente extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BotonTransparente(String text){
		setText(text);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	
	public BotonTransparente(){
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	
	protected void paintComponent(Graphics g){
		if(this.getModel().isRollover() || getModel().isArmed()){
			g.setColor(Colores.GRIS_CLARO);
		}
		else {
			g.setColor(Colores.NEGRO);
		}
		
		g.drawString(getText(), (int) (getWidth()/2-g.getFont().getStringBounds(getText(), ((Graphics2D) g).getFontRenderContext()).getCenterX()), getHeight()/2+g.getFont().getSize()/2-2);

	}
}
