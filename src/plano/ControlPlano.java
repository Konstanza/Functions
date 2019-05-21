package plano;

import funciones.Punto;
import interfaz.check_box.CheckBoxMenuItem;
import interfaz.menu.MenuItem;
import interfaz.menu.PopupMenu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import principal.ventanas.Ventana;

public class ControlPlano {

	private Ventana ventana;
	private Plano plano;
	private boolean arrastrar;
	private PopupMenu pop;
	private boolean w;
	private boolean a;
	private boolean s;
	private boolean d;
	private boolean e;
	private DibujoPlano canvas;
	
	public ControlPlano(Ventana ventana, DibujoPlano canvas){
		this.ventana = ventana;
		this.canvas = canvas;
		this.plano = canvas.getPlano();
		
		pop = new PopupMenu();
		
		MenuItem i = new MenuItem("Cargar");
		i.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ventana.getFileChooser2().cargar();
				canvas.dibujar();
			}
		}	
		);
		
		MenuItem i2 = new MenuItem("Guardar");
		i2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ventana.getFileChooser2().guardar();
				canvas.dibujar();
			}
		}	
		);
		
		MenuItem i3 = new MenuItem("Guardar imagen");
		i3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ventana.getFileChooser().guardar();
				canvas.dibujar();
			}
		}	
		);
		
		CheckBoxMenuItem i4 = new CheckBoxMenuItem("Controles");
		i4.setSelected(true);
		i4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!i4.isSelected())ventana.getVentanaControl().cerrar();
				else ventana.getVentanaControl().abrir();
			}
			
		});
		
		CheckBoxMenuItem i5 = new CheckBoxMenuItem("Funciones");
		i5.setSelected(true);
		i5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!i5.isSelected()) ventana.getVentanaOperacion().cerrar();
				else ventana.getVentanaOperacion().abrir();
			}
			
		});
		CheckBoxMenuItem i6 = new CheckBoxMenuItem("Colores");
		i6.setSelected(false);
		i6.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!i6.isSelected()) ventana.getVentanaColores().cerrar();
				else ventana.getVentanaColores().abrir();
			}
			
		});
		
		pop.add(i);
		pop.add(i2);
		pop.add(i3);
		pop.add(i4);
		pop.add(i5);
		pop.add(i6);
		pop.add(new MenuItem("Cerrar"));
		
		pop.addPopupMenuListener(new PopupMenuListener(){

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				i4.setSelected(ventana.getVentanaControl().isShowing());
				i5.setSelected(ventana.getVentanaOperacion().isShowing());
				i6.setSelected(ventana.getVentanaColores().isShowing());
			}});
	}
	
	private MouseAdapter ma = new MouseAdapter(){
		
		public void mouseEntered(MouseEvent e){
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) agregarPunto();
			else if(e.getButton() == MouseEvent.BUTTON3) mostrarMenu(e.getX(), e.getY());
		}

		private void mostrarMenu(int x, int y) {
			canvas.dibujar();
			pop.show(canvas, x, y);
		}	
	};
	
	
	private MouseMotionAdapter mma = new MouseMotionAdapter(){
		
		@Override
		public void mouseMoved(MouseEvent arg0) {
			canvas.setPosicion();
			
			canvas.dibujar();
			canvas.dibujar();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int diferenciaX = (int) (e.getX()-canvas.getPosicionX());
			int diferenciaY = (int) (e.getY()-canvas.getPosicionY());
			
			canvas.aumentarDesplazamiento(diferenciaX, diferenciaY);
			canvas.setPosicion();
			
			canvas.dibujar();
			canvas.dibujar();
		}
	};
	
	private KeyAdapter ka = new KeyAdapter(){
		
		@Override
		public void keyReleased(KeyEvent ev) {
			switch(ev.getKeyCode()){
				case KeyEvent.VK_W:
					if(e){
						canvas.aumentarEscala(0,1);
					}
					else if(!arrastrar){
						if(canvas.getPosicionY() > 0){
							canvas.setPosicionY(canvas.getPosicionY()-1);
						
							if(canvas.getDesplazamientoY() < 0){
								canvas.aumentarDesplazamiento(0,1);
							}
						}
						else {
							canvas.aumentarDesplazamiento(0,1);
						}
					}
					
					w = false;
					break;
				case KeyEvent.VK_S:
					if(e){
						canvas.disminuirEscala(0,1);
					}
					else if(!arrastrar){
						if(canvas.getPosicionY() < canvas.getAlto()-1){
							canvas.setPosicionY(canvas.getPosicionY()+1);
						
							if(canvas.getDesplazamientoY() > 0){
								canvas.disminuirDesplazamiento(0,1);
							}
						}
						else {
							canvas.disminuirDesplazamiento(0,1);
						}
					}
					
					s = false;
					break;
				case KeyEvent.VK_D:
					if(e){
						canvas.aumentarEscala(1,0);
					}
					else if(!arrastrar){
						if(canvas.getPosicionX() < canvas.getAncho()-1){
							canvas.setPosicionX(canvas.getPosicionX()+1);
							
							if(canvas.getDesplazamientoX() > 0){
								canvas.disminuirDesplazamiento(1, 0);
							}
						}
						else {
							canvas.disminuirDesplazamiento(1, 0);
						}
					}
					d = false;
					break;
				case KeyEvent.VK_A:
					if(e){
						canvas.disminuirEscala(1,0);
					}
					else if(!arrastrar){
						if(canvas.getPosicionX() > 0){
							canvas.setPosicionX(canvas.getPosicionX()-1);
							
							if(canvas.getDesplazamientoX() < 0){
								canvas.aumentarDesplazamiento(1, 0);
							}
						}
						else {
							canvas.aumentarDesplazamiento(1, 0);
						}
					}
					a = false;
					break;
				case KeyEvent.VK_ENTER:
					agregarPunto();
					break;
				case KeyEvent.VK_SHIFT:
					arrastrar = false;
					break;
				case KeyEvent.VK_E:
					e = false;
					break;
				case KeyEvent.VK_PLUS:
					canvas.aumentarEscala(1,1);
					break;
				case KeyEvent.VK_MINUS:
					canvas.disminuirEscala(1,1);
					break;
				case KeyEvent.VK_Q:
					canvas.setEscala(0, 0);
					canvas.setDesplazamiento(0, 0);
					break;
			}	
			canvas.dibujar();
			canvas.dibujar();
		}
		
		public void keyPressed(KeyEvent ev) {
				switch(ev.getKeyCode()){
					case KeyEvent.VK_W:
						if(arrastrar){
							if(canvas.getPosicionY() > 1){
								canvas.setPosicionY(canvas.getPosicionY()-2);
								
								if(canvas.getDesplazamientoY() < 0){
									canvas.aumentarDesplazamiento(0,1);
								}
							}
							else {
								canvas.aumentarDesplazamiento(0,1);
							}
						}
						w = true;
						break;
					case KeyEvent.VK_S:
						if(arrastrar){
							if(canvas.getPosicionY() < canvas.getAlto()-1){
								canvas.setPosicionY(canvas.getPosicionY()+2);
							
								if(canvas.getDesplazamientoY() > 0){
									canvas.disminuirDesplazamiento(0,1);
								}
							}
							else {
								canvas.disminuirDesplazamiento(0,1);
							}
							
						}
						s = true;
						break;
					case KeyEvent.VK_D:
						if(arrastrar){
							if(canvas.getPosicionX() < canvas.getAncho()-1){
								canvas.setPosicionX(canvas.getPosicionX()+2);
								
								if(canvas.getDesplazamientoX() > 0){
									canvas.disminuirDesplazamiento(1,0);
								}
							} 
							else {
								canvas.disminuirDesplazamiento(1,0);
							}
							
						}
						d = true;
						break;
					case KeyEvent.VK_A:
						if(arrastrar){
							if(canvas.getPosicionX() > 1){
								canvas.setPosicionX(canvas.getPosicionX()-2);
							
								if(canvas.getDesplazamientoX() < 0){
									canvas.aumentarDesplazamiento(1,0);
								}
							}
							else {
								canvas.aumentarDesplazamiento(1,0);
							}
							
						}
						a = true;
						break;
					case KeyEvent.VK_SPACE:
						if(w){
							canvas.aumentarDesplazamiento(0,100);
						}
						else if(s){
							canvas.disminuirDesplazamiento(0,100);
						}
						
						if(a){
							canvas.aumentarDesplazamiento(100,0);
						}
						else if(d){
							canvas.disminuirDesplazamiento(100,0);
						}
						
						break;
					case KeyEvent.VK_ENTER:
						agregarPunto();
						break;
					case KeyEvent.VK_SHIFT:
						arrastrar = true;
						break;
					case KeyEvent.VK_E:
						e = true;
						break;
					case KeyEvent.VK_ESCAPE:
						ventana.getDialog().setVisible(true);
						break;
				}	
				
				canvas.dibujar();
				canvas.dibujar();
		}
		
		};
	
	public MouseAdapter getMouseAdapter() {
		return ma;
	}
	
	protected void agregarPunto() {
		Point punto = (Point) canvas.getPosicion().clone();
		plano.agregarPunto(punto);
		canvas.verPuntos = true;
		
		canvas.dibujar();
		canvas.dibujar();
		
		JTextArea textArea = ventana.getTaPuntos();
		ventana.getBtnVerOrigen().setText("Ocultar puntos");
		
		ArrayList<Punto> puntos = canvas.getPlano().getPuntos();
		
		textArea.setText("");
		
		for(int i = 0; i < puntos.size(); i++){
			Punto punto2 = puntos.get(i);
			textArea.setText(textArea.getText()+"("+punto2.x+", "+punto2.y+")\n");
		}
	}
	
	protected void agregarPunto(Punto punto){
		plano.agregarPunto(punto);
		canvas.verPuntos = true;
		
		canvas.dibujar();
		canvas.dibujar();
		
		JTextArea textArea = ventana.getTaPuntos();
		ventana.getBtnVerOrigen().setText("Ocultar puntos");
		
		ArrayList<Punto> puntos = canvas.getPlano().getPuntos();
		
		textArea.setText("");
		
		for(int i = 0; i < puntos.size(); i++){
			Punto punto2 = puntos.get(i);
			textArea.setText(textArea.getText()+"("+punto2.x+", "+punto2.y+")\n");
		}
	}
	
	public MouseMotionAdapter getMouseMotionAdapter() {
		return mma;
	}

	public KeyAdapter getKeyAdapter() {
		return ka;
	}


	
}
