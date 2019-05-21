package principal.ventanas;

import interfaz.Colores;
import interfaz.botones.BotonOpaco;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import plano.DibujoPlano;

public abstract class ColorChooser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	protected JColorChooser c;

	/**
	 * Create the frame.
	 */
	public ColorChooser(DibujoPlano canvas, Color colorViejo) {
		setTitle("Seleccionar Color");
		contentPane = new JPanel();
		setSize(240, 280);
		setContentPane(contentPane);
		setResizable(false);
		
		c = new JColorChooser();
		c.removeChooserPanel(c.getChooserPanels()[4]);
		c.removeChooserPanel(c.getChooserPanels()[3]);
		c.removeChooserPanel(c.getChooserPanels()[0]);
		c.removeChooserPanel(c.getChooserPanels()[0]);
		c.setPreviewPanel(new JPanel());
		c.setColor(colorViejo);
		
		AbstractColorChooserPanel ccp = c.getChooserPanels()[0];
		ccp.setBackground(Colores.VERDE_AZULADO);
		borrar(ccp);
		ccp.getComponent(2).addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
				cambiarColor();
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				cambiarColor();
			}
			
		});
		ccp.getComponent(3).addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
				cambiarColor();
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				cambiarColor();
			}
			
		});
		
		ccp.getComponent(2).addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				cambiarColor();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		ccp.getComponent(3).addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				cambiarColor();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		contentPane.add(c);
		contentPane.setBackground(Colores.VERDE_AZULADO);
		
		BotonOpaco a = new BotonOpaco("Aceptar"), b = new BotonOpaco("Cancelar");
		a.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				canvas.dibujar();
			}
			
		});
		
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				accionCancelar();
				canvas.dibujar();
			}
			
		});
		
		contentPane.add(a);
		contentPane.add(b);
	}

	protected abstract void cambiarColor();

	protected abstract void accionCancelar();

	private void borrar(Component component) {
		Container c = Container.class.cast(component);
		for(int i = 0; i < c.getComponentCount();i++){
			if(JSpinner.class.isAssignableFrom(c.getComponent(i).getClass()) || JSlider.class.isAssignableFrom(c.getComponent(i).getClass()) || JLabel.class.isAssignableFrom(c.getComponent(i).getClass()) || JRadioButton.class.isAssignableFrom(c.getComponent(i).getClass())) {c.remove(i);i--;}
			else borrar(c.getComponent(i));
		}
	}


}
