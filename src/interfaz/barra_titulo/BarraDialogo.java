package interfaz.barra_titulo;

import interfaz.dialogs.Dialog;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import plano.DibujoPlano;
import principal.ventanas.Ventana;

public class BarraDialogo extends BarraTitulo{

	public BarraDialogo(Ventana ventana, Dialog dialog, Rectangle bounds) {
		super(ventana, bounds);

		addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				int diferenciaX = (int) (e.getX()-MousePosition.x);
				int diferenciaY = (int) (e.getY()-MousePosition.y);
			
				dialog.setLocation(dialog.getLocationOnScreen().x+diferenciaX, dialog.getLocationOnScreen().y+diferenciaY);
				
				ventana.getCanvas().dibujar();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				MousePosition = dialog.getMousePosition();
				//MousePosition.x -= Dialog.BORDE;
				//MousePosition.y -= Dialog.BORDE;
			}
			
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
