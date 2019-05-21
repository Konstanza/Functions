package interfaz.dialogs.option_panes;
import interfaz.Colores;
import interfaz.Fuentes;
import interfaz.botones.BotonOpaco;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicOptionPaneUI;

public class OptionPaneUI extends BasicOptionPaneUI{

	public static OptionPaneUI createUI(JComponent x){
		return new OptionPaneUI();	}
	
	protected Container createMessageArea(){
		JPanel p = new JPanel();
		p.setBackground(Colores.GRIS_CLARO);
		
		JLabel message = new JLabel((String) this.getMessage());
		message.setFont(Fuentes.PRINCIPAL);
		message.setForeground(Colores.AZUL);
		message.setBackground(Colores.GRIS_CLARO);
		
		p.add(message);
		
		return p;
	}
	
	protected Container createButtonArea(){
		JPanel p = new JPanel();
		p.setBackground(Colores.GRIS_CLARO);
		
		Object[] b = this.getButtons();
		
		for(int i = 0; i < b.length; i++){
			if(BotonOpaco.class.isAssignableFrom(b[i].getClass())){
				p.add((BotonOpaco) b[i]);
			}
		}
		//System.out.println(b[0]);
		return p;
	}
}
