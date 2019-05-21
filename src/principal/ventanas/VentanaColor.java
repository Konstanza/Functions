package principal.ventanas;

import interfaz.Colores;
import interfaz.Fuentes;
import interfaz.botones.BotonColor;
import interfaz.botones.BotonOpaco;
import interfaz.check_box.CheckBox;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import plano.DibujoPlano;

public class VentanaColor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BotonColor btnFondo;
	private BotonColor btnCursor;
	private BotonColor btnPuntos;
	private AbstractButton btnEjes;
	private AbstractButton btnFunciones;
	private DibujoPlano canvas;
	/**
	 * Create the frame.
	 */
	public VentanaColor(Ventana ventana) {
		this.canvas = ventana.getCanvas();
		setIconImage(ventana.getIcon2());
		setTitle("Colores");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 140, 245);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setFont(Fuentes.PRINCIPAL);
		contentPane.setBackground(Colores.VERDE_AZULADO);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnFondo = new BotonColor(canvas.getColorFondo());
		btnFondo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color colorViejo = btnFondo.getBackground();
				ColorChooser c = new ColorChooser(canvas, colorViejo){

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void accionCancelar() {
						dispose();
						canvas.setColorFondo(colorViejo);
						canvas.dibujar();
						btnFondo.setBackground(colorViejo);
					}

					@Override
					protected void cambiarColor() {
						canvas.setColorFondo(this.c.getColor());
						canvas.dibujar();
						btnFondo.setBackground(this.c.getColor());
					}
					
				};
				c.setVisible(true);
			}
			
		});
		btnFondo.setBounds(10, 10, 25, 25);
		contentPane.add(btnFondo);
		
		btnEjes = new BotonColor(canvas.getColorEjes());
		btnEjes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color colorViejo = btnEjes.getBackground();
				ColorChooser c = new ColorChooser(canvas, colorViejo){

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void accionCancelar() {
						dispose();
						canvas.setColorEjes(colorViejo);
						canvas.dibujar();
						btnEjes.setBackground(colorViejo);
					}

					@Override
					protected void cambiarColor() {
						canvas.setColorEjes(this.c.getColor());
						canvas.dibujar();
						btnEjes.setBackground(this.c.getColor());
					}
					
				};
				c.setVisible(true);
			}
			
		});
		btnEjes.setBounds(10, 41, 25, 25);
		contentPane.add(btnEjes);
		
		btnPuntos = new BotonColor(canvas.getColorPuntos());
		btnPuntos.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color colorViejo = btnPuntos.getBackground();
				ColorChooser c = new ColorChooser(canvas, colorViejo){

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void accionCancelar() {
						dispose();
						canvas.setColorPuntos(colorViejo);
						canvas.dibujar();
						btnPuntos.setBackground(colorViejo);
					}

					@Override
					protected void cambiarColor() {
						canvas.setColorPuntos(this.c.getColor());
						canvas.dibujar();
						btnPuntos.setBackground(this.c.getColor());
					}
					
				};
				c.setVisible(true);
			}
			
		});
		btnPuntos.setBounds(10, 72, 25, 25);
		contentPane.add(btnPuntos);
		
		btnCursor = new BotonColor(canvas.getColorCursor());
		btnCursor.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color colorViejo = btnCursor.getBackground();
				ColorChooser c = new ColorChooser(canvas, colorViejo){

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void accionCancelar() {
						dispose();
						canvas.setColorCursor(colorViejo);
						canvas.dibujar();
						btnCursor.setBackground(colorViejo);
					}

					@Override
					protected void cambiarColor() {
						canvas.setColorCursor(this.c.getColor());
						canvas.dibujar();
						btnCursor.setBackground(this.c.getColor());
					}
					
				};
				c.setVisible(true);
			}
			
		});
		btnCursor.setBounds(10, 103, 25, 25);
		contentPane.add(btnCursor);
		
		CheckBox chckbxFunciones = new CheckBox(" Funciones", Colores.VERDE_AZULADO, Colores.AZUL_OSCURO);
		chckbxFunciones.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxFunciones.isSelected()) canvas.colorRandomOff();
				else canvas.colorRandomOn();
				
				canvas.dibujar();
			}
			
		});
		chckbxFunciones.setBounds(43, 134, 112, 24);
		contentPane.add(chckbxFunciones);
		
		btnFunciones = new BotonColor(canvas.getColorFunciones());
		btnFunciones.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxFunciones.isSelected()){
					Color colorViejo = btnFunciones.getBackground();
					ColorChooser c = new ColorChooser(canvas, colorViejo){

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						protected void accionCancelar() {
							dispose();
							canvas.setColorFunciones(colorViejo);
							canvas.dibujar();
							btnFunciones.setBackground(colorViejo);
						}

						@Override
						protected void cambiarColor() {
							canvas.setColorFunciones(this.c.getColor());
							canvas.dibujar();
							btnFunciones.setBackground(this.c.getColor());
						}
						
					};
					c.setVisible(true);
				}
				
			}
			
		});
		btnFunciones.setBounds(10, 134, 25, 25);
		contentPane.add(btnFunciones);
		
		JLabel lblFondo = new JLabel("Fondo");
		lblFondo.setForeground(Colores.AZUL_OSCURO);
		lblFondo.setBackground(Colores.VERDE_AZULADO);
		lblFondo.setBounds(47, 14, 55, 16);
		contentPane.add(lblFondo);
		
		JLabel lblEjes = new JLabel("Ejes");
		lblEjes.setForeground(Colores.AZUL_OSCURO);
		lblEjes.setBackground(Colores.VERDE_AZULADO);
		lblEjes.setBounds(47, 45, 55, 16);
		contentPane.add(lblEjes);
		
		JLabel lblPuntos = new JLabel("Puntos");
		lblPuntos.setBounds(47, 76, 55, 16);
		lblPuntos.setForeground(Colores.AZUL_OSCURO);
		lblPuntos.setBackground(Colores.VERDE_AZULADO);
		contentPane.add(lblPuntos);
		
		JLabel lblCursor = new JLabel("Cursor");
		lblCursor.setBounds(47, 107, 55, 16);
		lblCursor.setForeground(Colores.AZUL_OSCURO);
		lblCursor.setBackground(Colores.VERDE_AZULADO);
		contentPane.add(lblCursor);
		
		
		
		BotonOpaco btnReestablecer = new BotonOpaco("Reestablecer");
		btnReestablecer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas.reiniciarColores();
				cargarColores();
				chckbxFunciones.setSelected(false);
				canvas.dibujar();
			}
			
		});
		btnReestablecer.setBounds(10, 167, 112, 25);
		contentPane.add(btnReestablecer);
	}
	

	public void abrir() {
		setVisible(true);
		
	}
	public void cerrar() {
		setVisible(false);
	}


	public void cargarColores() {
		btnFondo.setBackground(canvas.getColorFondo());
		btnEjes.setBackground(canvas.getColorEjes());
		btnPuntos.setBackground(canvas.getColorPuntos());
		btnCursor.setBackground(canvas.getColorCursor());
		btnFunciones.setBackground(canvas.getColorFunciones());
	}
}
