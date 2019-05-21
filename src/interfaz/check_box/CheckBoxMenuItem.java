package interfaz.check_box;

import interfaz.Colores;
import interfaz.Fuentes;

import java.awt.Graphics;

import javax.swing.JCheckBoxMenuItem;

public class CheckBoxMenuItem extends JCheckBoxMenuItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckBoxMenuItem(String string){
		setText(string);
		setFont(Fuentes.PRINCIPAL);
	}
	
	protected void paintComponent(Graphics g){
		if(this.isSelected()){
			if(!isArmed()){
				g.setColor(Colores.BLANCO);
				g.fillRect(0, 0, 200, getHeight());
				g.setColor(Colores.AZUL);
				g.drawString("✔", getHorizontalTextPosition(), getHeight()/2+getFont().getSize()/2);
				g.drawString(getText(), getHorizontalTextPosition()+15, getHeight()/2+getFont().getSize()/2);
			}
			else {
				g.setColor(Colores.AZUL_CLARO);
				g.fillRect(0, 0, 200, getHeight());
				g.setColor(Colores.BLANCO);
				g.drawString("✔", getHorizontalTextPosition(), getHeight()/2+getFont().getSize()/2);
				g.drawString(getText(), getHorizontalTextPosition()+15, getHeight()/2+getFont().getSize()/2);
			}
			
		}
		else {
			if(isArmed()){
				g.setColor(Colores.AZUL_CLARO);
				g.fillRect(0, 0, 200, getHeight());
				g.setColor(Colores.BLANCO);
				g.drawString(getText(), getHorizontalTextPosition()+15, getHeight()/2+getFont().getSize()/2);
			}
			else {
				g.setColor(Colores.BLANCO);
				g.fillRect(0, 0, 200, getHeight());
				g.setColor(Colores.AZUL);
				g.drawString(getText(), getHorizontalTextPosition()+15, getHeight()/2+getFont().getSize()/2);
			}
		}
		
	}
}
