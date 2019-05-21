package principal.ventanas;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import principal.paneles.PanelControl;

public class VentanaControl extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @param pControl 
	 */
	public VentanaControl(Ventana ventana, PanelControl pControl) {
		Point p = ventana.getLocation();
		setTitle("Control");
		setIconImage(ventana.getIcon2());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(pControl.getWidth()+5, pControl.getHeight());
		setLocation(p.x+5,p.y+ventana.getHeight()+15);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setSize(pControl.getSize());
		setContentPane(contentPane);
		contentPane.add(pControl);
	}


	public void abrir() {
		setVisible(true);
	}
	public void cerrar() {
		setVisible(false);
	}

}
