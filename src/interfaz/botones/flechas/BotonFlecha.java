package interfaz.botones.flechas;

import interfaz.Colores;
import interfaz.botones.BotonTransparente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public abstract class BotonFlecha extends BotonTransparente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean centrado;
	protected Polygon p1;
	
	private final Color LETRAS = Colores.BLANCO;
	private final Color PASO = Colores.AZUL_CLARO;
	private final Color SELECCION = Colores.AZUL;
	
	protected void paintComponent(Graphics g){
		if(!centrado){
			Rectangle r = p1.getBounds();
			p1.translate((int)(this.getWidth()/2-r.getCenterX()), (int)(this.getHeight()/2-r.getCenterY()));
			centrado = true;
		}
		
		/*
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
		}*/
		
		if(this.getModel().isArmed()){
			g.setColor(SELECCION);
		}
		else if(this.getModel().isRollover()){
			g.setColor(PASO);
		}
		else {
			g.setColor(LETRAS);
		}
		
		g.fillPolygon(p1);
	}

}
