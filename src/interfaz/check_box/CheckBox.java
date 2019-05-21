package interfaz.check_box;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


import javax.swing.JCheckBox;

public class CheckBox extends JCheckBox{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public CheckBox(String s, Color fondo, Color frente){
		setText(s);
		setBackground(fondo);
		setForeground(frente);
	}
	
	protected void paintComponent(Graphics g){
		
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(getForeground());
		Rectangle2D b = g.getFont().getStringBounds("✔", ((Graphics2D)g).getFontRenderContext());
		
		
		g.drawRect((int)getHorizontalTextPosition(), (int)(getHeight()/2-b.getHeight()/2)+5, (int)b.getWidth(), (int)b.getHeight()-5);
		
		
		if(isSelected()){
			g.drawString("✔", (int)getHorizontalTextPosition(), (int)(getHeight()/2+b.getHeight()/2));
		}
		
		
		g.drawString(getText(), getHorizontalTextPosition()+15, getHeight()/2+getFont().getSize()/2);
	}
}
