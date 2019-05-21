package interfaz.botones;

import interfaz.Colores;
import interfaz.Fuentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class BotonOpaco extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Color FONDO = Colores.AZUL;
	private final Color LETRAS = Colores.BLANCO_AZULADO;
	private final Color SELECCION = Colores.AZUL_CLARO;
	
	public BotonOpaco(String text){
		super(text);
	}
	
	protected void paintComponent(Graphics g){
		g.setFont(Fuentes.PRINCIPAL);
		
		int x, y;
		Graphics2D g2 = (Graphics2D) g;
		
		x = (int) (this.getWidth()/2 - g.getFont().getStringBounds(this.getText(), g2.getFontRenderContext()).getWidth()/2);
		y = this.getHeight()/2 + g.getFont().getSize()/2;
		
		if(this.getModel().isArmed()){
			
			g.setColor(LETRAS);
			
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(FONDO);
			
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		}
		
		else if(this.getModel().isRollover()){
			g.setColor(SELECCION);
			
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(LETRAS);
		}
		else {
			g.setColor(FONDO);
			
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(LETRAS);
		}
		
		g.drawString(this.getText(), x, y);
	}
}
