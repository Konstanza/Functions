package interfaz.scroll_pane.barras;

import javax.swing.JScrollBar;

public class BarraVertical extends JScrollBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BarraVertical(){
		setOrientation(VERTICAL);
		setUI(BarraUI.createUI(this));
	}
}
