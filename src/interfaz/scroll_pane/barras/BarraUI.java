package interfaz.scroll_pane.barras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import interfaz.Colores;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class BarraUI extends BasicScrollBarUI{

	private final Color BARRA = Colores.AZUL;
	private final Color FONDO = Colores.GRIS;
	private final Color SELECCION = Colores.AZUL_CLARO;
	
	public static BarraUI createUI(JComponent c){
		return new BarraUI();
	}
	
	protected JButton createIncreaseButton(int orientation){
		BasicArrowButton boton = new BasicArrowButton(orientation, //Direccion de la flecha
                Colores.BLANCO, //Color de fondo
                new Color(130,7,7),//sombra
                new Color(130,7,7),//darkShadow
               Colores.BLANCO //highlight
                );   
		
		//se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
		
        boton.setBorder(BorderFactory.createLineBorder(BARRA,2));
        boton.setContentAreaFilled(false); 
		return boton;
	}
	
	protected JButton createDecreaseButton(int orientation){
		BasicArrowButton boton = new BasicArrowButton(orientation, //Direccion de la flecha
                Colores.BLANCO, //Color de fondo
                new Color(130,7,7),//sombra
                new Color(130,7,7),//darkShadow
               Colores.BLANCO //highlight
                );   
		
		//se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
		
        boton.setBorder(BorderFactory.createLineBorder(BARRA,2));
        boton.setContentAreaFilled(false); 
		return boton;
	}
	
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds){
		if(this.isThumbRollover() || this.isDragging){
			g.setColor(SELECCION);
		}
		else{
			g.setColor(BARRA);
		}
		
		g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
	}
	
	
	protected void paintTrack(Graphics g, JComponent c, Rectangle thumbBounds){
		g.setColor(FONDO);
		g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
		
	}
	
}
