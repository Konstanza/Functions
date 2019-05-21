package principal.paneles;

import funciones.Funcion;
import interfaz.Colores;
import interfaz.botones.BotonOpaco;
import interfaz.botones.flechas.BotonFlechaAbajo;
import interfaz.botones.flechas.BotonFlechaArriba;
import interfaz.botones.flechas.BotonFlechaDerecha;
import interfaz.botones.flechas.BotonFlechaIzquierda;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import plano.DibujoPlano;
import principal.ventanas.Ventana;

public class PanelControl extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BotonFlechaIzquierda btnIzquierda;
	private BotonFlechaDerecha btnDerecha;
	private BotonFlechaArriba btnArriba;
	private BotonFlechaAbajo btnAbajo;
	
	private BotonOpaco btnRo;
	private BotonOpaco btnRe;
	
	private BotonOpaco btnVerOrigen;
	private BotonOpaco btnLimpiarCanvas;
	
	private BotonFlechaDerecha btnX;
	private BotonFlechaArriba btnY;
	private BotonFlechaIzquierda btnX_1;
	private BotonFlechaAbajo btnY_1;
	
	private ArrayList<Funcion> funciones;
	
	public static final Color FONDO = Colores.GRIS;
	
	public PanelControl(Ventana ventana, PanelOperacion pOperacion, DibujoPlano canvas, Rectangle bounds){
		setLayout(null);
		setBounds(bounds);
		setBackground(FONDO);
		
		this.funciones = pOperacion.getFunciones();
		
		int anchoBoton = 50;
		int alturaBoton = 25;
		int ladoBotonFlecha = 25;
		int espacio = 5;
		int t = 12;
		
		btnLimpiarCanvas = new BotonOpaco("Limpiar");
		btnVerOrigen = new BotonOpaco("Ocultar puntos");
		
		btnRo = new BotonOpaco("0.0");
		btnRe = new BotonOpaco("E");
		
		btnIzquierda = new BotonFlechaIzquierda(t);
		btnDerecha = new BotonFlechaDerecha(t);
		btnArriba = new BotonFlechaArriba(t);
		btnAbajo = new BotonFlechaAbajo(t);
		
		btnX_1 = new BotonFlechaIzquierda(t);
		btnX = new BotonFlechaDerecha(t);
		btnY = new BotonFlechaArriba(t);
		btnY_1 = new BotonFlechaAbajo(t);
		
		btnRo.setBounds(this.getWidth()/2-anchoBoton/2, this.getHeight()/2-alturaBoton/2-15, anchoBoton, alturaBoton);
		add(btnRo);
		
		btnRe.setBounds(this.getWidth()-10-anchoBoton*2, btnRo.getY(), anchoBoton, alturaBoton);
		add(btnRe);
		
		btnLimpiarCanvas.setBounds(10, btnRo.getY()-alturaBoton, anchoBoton*2, alturaBoton);
		add(btnLimpiarCanvas);
		
		btnVerOrigen.setBounds(btnLimpiarCanvas.getX(), btnLimpiarCanvas.getY()+alturaBoton*2, anchoBoton*2, alturaBoton);
		add(btnVerOrigen);
		
		btnArriba.setBounds(btnRo.getX()+anchoBoton/4, btnRo.getY()-alturaBoton-espacio, ladoBotonFlecha, ladoBotonFlecha);
		add(btnArriba);
		
		btnAbajo.setBounds(btnRo.getX()+anchoBoton/4, btnRo.getY()+alturaBoton+espacio, ladoBotonFlecha, ladoBotonFlecha);
		add(btnAbajo);
		
		btnIzquierda.setBounds(btnRo.getX()-ladoBotonFlecha-espacio, btnRo.getY(), ladoBotonFlecha, ladoBotonFlecha);
		add(btnIzquierda);
		
		btnDerecha.setBounds(btnRo.getX()+anchoBoton+espacio, btnRo.getY(), ladoBotonFlecha, ladoBotonFlecha);
		add(btnDerecha);
		
		btnY.setBounds(btnRe.getX()+anchoBoton/4, btnRe.getY()-ladoBotonFlecha-espacio, ladoBotonFlecha, ladoBotonFlecha);
		add(btnY);
		
		btnY_1.setBounds(btnRe.getX()+anchoBoton/4, btnRe.getY()+alturaBoton+espacio, ladoBotonFlecha, ladoBotonFlecha);
		add(btnY_1);
		
		btnX_1.setBounds(btnRe.getX()-ladoBotonFlecha-espacio, btnRe.getY(), ladoBotonFlecha, ladoBotonFlecha);
		add(btnX_1);
		
		btnX.setBounds(btnRe.getX()+anchoBoton+espacio, btnRe.getY(), ladoBotonFlecha, ladoBotonFlecha);
		add(btnX);
		
		
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aumentarDesplazamiento(100, 0);
				
				canvas.dibujar();
			}
		});
		
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.disminuirDesplazamiento(100, 0);
				
				canvas.dibujar();
			}
		});
		
		
		btnX_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.disminuirEscala(1, 0);
				
				canvas.dibujar();
			}
		});
		
		
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aumentarEscala(0, 1);
				
				canvas.dibujar();
			}
		});
		
		
		btnY_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.disminuirEscala(0, 1);
				
				canvas.dibujar();
			}
		});
		
		
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aumentarDesplazamiento(0, 100);
				
				canvas.dibujar();
			}
		});
		
		
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.disminuirDesplazamiento(0, 100);
				
				canvas.dibujar();
			}
		});
		
		
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aumentarEscala(1, 0);
				
				canvas.dibujar();
			}
		});
		
		
		btnRo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.setDesplazamiento(0,0);
				
				canvas.dibujar();
				canvas.dibujar();
			}
		});
		
		
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setEscala(0,0);
				
				canvas.dibujar();
				canvas.dibujar();
			}
		});
		
		btnLimpiarCanvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(!funciones.isEmpty()){
					canvas.eliminarFuncion(funciones.get(0));
					funciones.remove(0);
					pOperacion.pFunciones.remove(0);
				}
				pOperacion.spFunciones.setViewportView(pOperacion.pFunciones);
				
				pOperacion.taPuntos.setText("");
				pOperacion.tFuncion.setText("");
				canvas.getPlano().borrarPuntos();
				
				canvas.dibujar();
			}
		});
		btnVerOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.verPuntos = canvas.verPuntos == true ? false:true;
				
				if(canvas.verPuntos){
					btnVerOrigen.setText("Ocultar puntos");
				} else {
					btnVerOrigen.setText("Mostrar puntos");
				}
				
				canvas.dibujar();
			}
		});
		
	}

        
	public JButton getBtnVerOrigen() {
		return btnVerOrigen;
	}
	
}
