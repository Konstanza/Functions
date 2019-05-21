package interfaz.barra_titulo;

import interfaz.Fuentes;
import interfaz.botones.BotonTransparente;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import principal.ventanas.Ventana;

public class BarraPrincipal extends BarraTitulo{

	private BotonTransparente botonMinimizar;
	protected BotonTransparente botonSalir;
	
	public BarraPrincipal(int height, Ventana ventana) {
		super(ventana,new Rectangle(ventana.getBorde(), ventana.getBorde(), ventana.getWidth()-ventana.getBorde()*2, height));
		
		int espacioX = 5;
		int anchoBoton = 20;
		int alturaBoton = getHeight();
		Font fuente = Fuentes.BOTON_BARRA_TITULO;
		
		botonSalir = new BotonTransparente("X");
		botonSalir.setBounds(getWidth()-espacioX-anchoBoton, getHeight()/2-alturaBoton/2, anchoBoton, alturaBoton);
		add(botonSalir);
	
		botonMinimizar = new BotonTransparente("―");
		botonMinimizar.setBounds(botonSalir.getX()-espacioX-anchoBoton, botonSalir.getY(), anchoBoton, alturaBoton);
		add(botonMinimizar);
		
		botonSalir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.getDialog().setVisible(true);
				//System.exit(0);
			}
		});
		botonSalir.setFont(fuente);
		
		botonMinimizar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.setState(JFrame.ICONIFIED);
			}
		});
		botonMinimizar.setFont(fuente);
		
		addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				int diferenciaX = (int) (e.getX()-MousePosition.x);
				int diferenciaY = (int) (e.getY()-MousePosition.y);
			
				ventana.setLocation(ventana.getLocationOnScreen().x+diferenciaX, ventana.getLocationOnScreen().y+diferenciaY);
				
				ventana.getCanvas().dibujar();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				MousePosition = ventana.getMousePosition();
				MousePosition.x -= ventana.getBorde();
				MousePosition.y -= ventana.getBorde();
			}
			
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
