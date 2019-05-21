package principal.ventanas;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import principal.paneles.PanelOperacion;

public class VentanaFunciones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param pOperacion 
	 */
	public VentanaFunciones(Ventana ventana, PanelOperacion pOperacion) {

		Point p = ventana.getLocation();
		setTitle("Funciones");
		setIconImage(ventana.getIcon2());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(pOperacion.getWidth()+5, pOperacion.getHeight()+5);
		setLocation(p.x+ventana.getWidth()+15, p.y+5);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setSize(pOperacion.getSize());
		setContentPane(contentPane);
		contentPane.add(pOperacion);
	}

	public void abrir() {
		setVisible(true);
		
	}
	public void cerrar() {
		setVisible(false);
	}
}
