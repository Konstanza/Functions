package interfaz.dialogs;

import interfaz.Colores;

import javax.swing.JDialog;
import javax.swing.JPanel;

import principal.ventanas.Ventana;

public class Dialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JPanel contentPane;
	
	/*
	public Dialog(int ancho, int alto){
		addContainerListener(Ventana.control);
		
		int altoBarra = 20;
		
		this.setSize(ancho+BORDE*2, alto+BORDE*2+altoBarra);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setAutoRequestFocus(true);
		this.setUndecorated(true);
		this.setBackground(Colores.AZUL_OSCURO);
		
		RoundRectangle2D rect = new RoundRectangle2D.Double(getX(),getY(), getWidth(), getHeight(), 5, 5);
		setShape(rect);
		
		contentPane = new JPanel();
		contentPane.setBackground(Colores.AZUL_OSCURO);
		contentPane.setLayout(null);
		
		barra = new BarraTitulo(new Rectangle(BORDE,BORDE, ancho, altoBarra));
		
		contenido = new JPanel();
		contenido.setBounds(BORDE, BORDE+barra.getHeight(), ancho, alto);
		contenido.setBackground(Colores.GRIS_CLARO);
		
		contentPane.add(barra);
		contentPane.add(contenido);
		
		this.setContentPane(contentPane);
	}*/
	
	public Dialog(Ventana ventana, int ancho, int alto){
		addContainerListener(ventana.getControl());
		
		setSize(ancho+16, alto+16);
		setResizable(false);
		setAlwaysOnTop(true);
		setAutoRequestFocus(true);
		setBackground(Colores.AZUL_OSCURO);
		
		
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, ancho, alto);
		contentPane.setBackground(Colores.GRIS_CLARO);
		contentPane.setLayout(null);
		
		
		setContentPane(contentPane);
	}
}
