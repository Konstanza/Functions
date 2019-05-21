package interfaz.combo_box;

import interfaz.Colores;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ComboBoxUI extends BasicComboBoxUI{
	
	public static ComboBoxUI createUI(JComponent c){
		return new ComboBoxUI();
	}
	
	@Override
	public void paintCurrentValueBackground(Graphics g,Rectangle bounds, boolean hasFocus)
	     {
	         g.setColor(Colores.AZUL);            
	         g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	     }
	
	 @Override 
	protected JButton createArrowButton() {        
	          BasicArrowButton basicArrowButton = new BasicArrowButton(BasicArrowButton.SOUTH, //Direccion de la flecha
	                  Colores.BLANCO, //Color de fondo
	                  new Color(130,7,7),//sombra
	                  new Color(130,7,7),//darkShadow
	                 Colores.BLANCO //highlight
	                  );         
	        //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
	          basicArrowButton.setBorder(BorderFactory.createLineBorder(Colores.BLANCO,2));
	          basicArrowButton.setContentAreaFilled(false);        
	          return basicArrowButton;
	      }     
	 
	public ListCellRenderer createRenderer(){
		
		return new DefaultListCellRenderer(){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,Object value,int index, boolean isSelected,boolean cellHasFocus){
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				list.setSelectionBackground(Colores.AZUL_CLARO);
				
				this.setPreferredSize(new Dimension(this.getWidth(), 25));
				
				if(isSelected){
					setBackground(Colores.AZUL_CLARO);
					setForeground(Colores.BLANCO_AZULADO);
				}
				else {
					setForeground(Colores.AZUL);
					setBackground(Color.WHITE);
				}
				
				return this;
			}
		};
	}
}
