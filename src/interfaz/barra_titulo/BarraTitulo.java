package interfaz.barra_titulo;

import interfaz.Colores;
import interfaz.Fuentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import principal.ventanas.Ventana;

public class BarraTitulo extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	protected final Color COLOR = Colores.AZUL;
	
	public Point MousePosition;
	
	public BarraTitulo(Ventana ventana, Rectangle bounds){
		setBounds(bounds);
		setLayout(null);
		addContainerListener(ventana.getControl());
		
		Icon icon = new ImageIcon(ventana.getIcon().getScaledInstance(getHeight()-4, getHeight()-4, Image.SCALE_DEFAULT));
		
		int espacioX = 5;
		
		titulo = new JLabel("  "+ventana.getTitulo());
		titulo.setIcon(icon);
		titulo.setFont(Fuentes.TITULO);
		titulo.setBounds(espacioX, getHeight()/2-icon.getIconHeight()/2, 150, icon.getIconHeight());
		add(titulo);
		
	}
	

	protected void paintComponent(Graphics g){
		
		int red = COLOR.getRed()-10;
		int green = COLOR.getGreen()-10;
		int blue = COLOR.getBlue()-10;
		
		for(int i = 0; i < getHeight(); i++){
			Color color = new Color(red+i*2, green+i*2, blue+i*2);
			g.setColor(color);
			
			g.drawLine(0, i, getWidth()-1, i);
		}
		
		g.setColor(Colores.BLANCO);
	}

	public Color getColor() {
		return COLOR;
	}
}
