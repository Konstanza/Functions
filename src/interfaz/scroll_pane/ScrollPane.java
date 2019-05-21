package interfaz.scroll_pane;

import interfaz.scroll_pane.barras.BarraHorizontal;
import interfaz.scroll_pane.barras.BarraVertical;

import javax.swing.JScrollPane;

import principal.ventanas.Ventana;

public class ScrollPane extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScrollPane(Ventana ventana){
		addContainerListener(ventana.getControl());
		this.setVerticalScrollBar(new BarraVertical());
		this.setHorizontalScrollBar(new BarraHorizontal());
	}
}
