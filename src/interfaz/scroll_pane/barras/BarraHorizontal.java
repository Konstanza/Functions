package interfaz.scroll_pane.barras;

import javax.swing.JScrollBar;

public class BarraHorizontal extends JScrollBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BarraHorizontal(){
		setOrientation(HORIZONTAL);
		setUI(BarraUI.createUI(this));
	}
}
