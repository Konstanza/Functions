package interfaz.combo_box;

import interfaz.Colores;
import interfaz.Fuentes;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class ComboBox extends JComboBox<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComboBox(){
		setForeground(Colores.BLANCO_AZULADO);        
		setBorder(BorderFactory.createLineBorder(Colores.AZUL, 2));
		setFont(Fuentes.PRINCIPAL);
		setUI(ComboBoxUI.createUI(this));
	}
}
