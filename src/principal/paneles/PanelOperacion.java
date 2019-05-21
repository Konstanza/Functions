package principal.paneles;

import funciones.Funcion;
import funciones.Punto;
import funciones.minimoCuadrado.FuncionMinimoCuadrado;
import funciones.minimoCuadrado.FuncionModeloExponencial;
import funciones.minimoCuadrado.FuncionModeloLineal;
import funciones.minimoCuadrado.FuncionModeloPotencial;
import funciones.splines.FuncionGrupoSpline;
import funciones.splines.FuncionSpline;
import funciones.splines.cuadratico.FuncionSplineCuadratico;
import funciones.splines.cubico.FuncionSplineCubico;
import funciones.splines.lineal.FuncionSplineLineal;
import interfaz.Colores;
import interfaz.Fuentes;
import interfaz.botones.BotonColorFuncion;
import interfaz.botones.BotonOpaco;
import interfaz.check_box.CheckBox;
import interfaz.combo_box.ComboBox;
import interfaz.scroll_pane.ScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import plano.DibujoPlano;
import principal.ventanas.ColorChooser;
import principal.ventanas.Ventana;

public class PanelOperacion extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComboBox cbTipo;
	private ComboBox cbModelo;
	
	JPanel pFunciones;
	
	private ScrollPane spPuntos;
	ScrollPane spFunciones;
	
	JTextField tFuncion;
	private JTextField tX;
	private JTextField tY;
	
	JTextArea taPuntos;
	
	private BotonOpaco btnObtenerFuncion;
	
	private BotonOpaco btnLimpiarPnts;
	private BotonOpaco btnEliminarPunto;
	private BotonOpaco btnAgregarPunto;
	
	private BotonOpaco btnLimpiarFunciones;
	private BotonOpaco btnEliminarFunciones;
	private BotonOpaco btnSeleccionar;
	
	private DibujoPlano canvas;
	
	private ArrayList<Funcion> funciones;
	private ArrayList<JCheckBox> aOrigen;
	private ArrayList<JCheckBox> aFuncion;
	
	public PanelOperacion(Ventana ventana, DibujoPlano canvas, Rectangle bounds){
		addContainerListener(ventana.getControl());
		setBounds(bounds);
		setBackground(Colores.VERDE_AZULADO);
		setLayout(null);
		
		this.canvas = canvas;
		
		setPreferredSize(new Dimension((int)bounds.getWidth(), (int)bounds.getHeight()));
		
		funciones = new ArrayList<Funcion>();
		aOrigen = new ArrayList<JCheckBox>();
		aFuncion = new ArrayList<JCheckBox>();
		
		iniciarComponentes(ventana);
		
	}
	

	protected void agregarFuncion(FuncionMinimoCuadrado funcion) {
		tFuncion.setText("f(x)="+funcion.getFuncion());
		
		if(canvas.agregarFuncion(funcion)){
			
			CheckBox cbo = new CheckBox("Or", Colores.BLANCO, Colores.NEGRO);
			
			CheckBox cb = new CheckBox("f(x) = "+funcion.getFuncion()+"  |  M: "+funcion.getModelo()+"  |  R²: "+funcion.getR2()+"%", Colores.BLANCO, Colores.NEGRO);
			cb.setSelected(true);
			cb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(cb.isSelected()){
					    canvas.eliminarFuncion(funciones.get(pFunciones.getComponentZOrder(cb.getParent())));
						canvas.agregarFuncion((FuncionMinimoCuadrado) funciones.get(pFunciones.getComponentZOrder(cb.getParent())));
						funciones.get(pFunciones.getComponentZOrder(cb.getParent())).ver = true;
					}
					else {
						funciones.get(pFunciones.getComponentZOrder(cb.getParent())).ver = false;
					}
					
					canvas.dibujar();
				}
			});
			
			cbo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(cbo.isSelected()){
						funciones.get(pFunciones.getComponentZOrder(cbo.getParent())).verOrigen = true;
					}
					else {
						funciones.get(pFunciones.getComponentZOrder(cbo.getParent())).verOrigen = false;
					}
					canvas.dibujar();
				}
			});
			
			JPanel p = new JPanel();
			p.setSize(pFunciones.getWidth(), cb.getHeight());
			p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
			p.setAlignmentX(LEFT_ALIGNMENT);	
			
			p.setBackground(Color.white);
			cbo.setBackground(Color.white);
			cb.setBackground(Color.white);
			
			cbo.setFont(Fuentes.AREA_TEXTO);
			cb.setFont(Fuentes.AREA_TEXTO);
			
			BotonColorFuncion btnColor = new BotonColorFuncion(funcion.getColor(), p.getBackground());
			btnColor.setSize(10, 10);
			btnColor.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Color colorViejo = btnColor.getBackground();
					ColorChooser c = new ColorChooser(canvas, colorViejo){

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						protected void accionCancelar() {
							dispose();
							btnColor.setBackground(colorViejo);
							funcion.setColor(colorViejo);
							canvas.dibujar();
						}

						@Override
						protected void cambiarColor() {
							btnColor.setBackground(this.c.getColor());
							funcion.setColor(this.c.getColor());
							canvas.dibujar();
						}
						
					};
					c.setVisible(true);
				}
				
			});
			
			p.add(btnColor);
			p.add(cbo);
			p.add(cb);
			pFunciones.add(p);
			
			spFunciones.setViewportView(pFunciones);

			funciones.add(funcion);
			aOrigen.add(cbo);
			aFuncion.add(cb);
			
			canvas.dibujar();
		}
		
	}

	protected void agregarFuncion(FuncionGrupoSpline grupoFunciones) {
		tFuncion.setText("");
		
		FuncionSpline[] funcion = grupoFunciones.getFunciones();
		
		for(int i = 0; i < funcion.length; i++){
			agregarFuncion(funcion[i]);
		}
	}
	

	private void iniciarComponentes(Ventana ventana){

		Font fuente = Fuentes.BOTON_BARRA_TITULO;
		
		int espacioX = 5;
		int espacioY = 10;
		int anchoComboBox = getWidth()-1-espacioX*2;
		int alturaComboBox = 25;
		int anchoBoton = 100;
		int alturaBoton = 25;
		
		cbTipo = new ComboBox();
		cbTipo.addItem("Regresión por mínimos cuadrados");
		cbTipo.addItem("Splines");
		
		cbTipo.setBounds(espacioX, 12, anchoComboBox, alturaComboBox);
		add(cbTipo);
		
		cbModelo = new ComboBox();
		cbModelo.addItem("Modelo lineal");
		cbModelo.addItem("Modelo exponencial");
		cbModelo.addItem("Modelo potencial");
		cbModelo.setBounds(espacioX, cbTipo.getY()+alturaComboBox+espacioY, anchoComboBox, alturaComboBox);
		add(cbModelo);
		
		btnObtenerFuncion = new BotonOpaco("Obtener función");
		
		btnObtenerFuncion.setBounds(espacioX, cbModelo.getY()+espacioY+alturaComboBox, anchoBoton, alturaBoton);
		add(btnObtenerFuncion);
		
		tFuncion = new JTextField();
		tFuncion.setBounds(espacioX, btnObtenerFuncion.getY()+alturaBoton+espacioY, anchoComboBox, alturaComboBox);
		tFuncion.setEditable(false);
		tFuncion.setBackground(Color.white);	
		tFuncion.setFont(Fuentes.AREA_TEXTO);
		add(tFuncion);
		
		JLabel lblPuntos = new JLabel("Puntos");
		lblPuntos.setFont(fuente);
		lblPuntos.setForeground(Colores.AZUL);
		lblPuntos.setBounds(espacioX, tFuncion.getY()+alturaComboBox+espacioY, anchoComboBox, alturaComboBox);
		add(lblPuntos);
		
		taPuntos = new JTextArea();
		taPuntos.setEditable(false);
		taPuntos.setFont(Fuentes.AREA_TEXTO);
		
		spPuntos = new ScrollPane(ventana);
		spPuntos.setViewportView(taPuntos);
		spPuntos.setBounds(espacioX, lblPuntos.getHeight()+lblPuntos.getY()+espacioY, anchoComboBox, alturaComboBox*6);
		add(spPuntos);
		
		btnLimpiarPnts = new BotonOpaco("Limpiar");
		btnAgregarPunto = new BotonOpaco("+");
		btnEliminarPunto = new BotonOpaco("-");
		tX = new JTextField();
		tY = new JTextField();
		
		btnLimpiarPnts.setBounds(espacioX, spPuntos.getHeight()+spPuntos.getY()+espacioY, anchoBoton, alturaBoton);
		add(btnLimpiarPnts);
		
		btnEliminarPunto.setBounds(espacioX+anchoBoton+espacioX/2+1, btnLimpiarPnts.getY(), anchoBoton/2, alturaBoton);
		add(btnEliminarPunto);
		
		tX.setBounds(btnEliminarPunto.getX()+anchoBoton/2+espacioX/2+1, btnLimpiarPnts.getY(), anchoBoton/2, alturaBoton);
		add(tX);
		
		tY.setBounds(tX.getX()+anchoBoton/2+espacioX/2+1, btnLimpiarPnts.getY(), anchoBoton/2, alturaBoton);
		add(tY);
		
		btnAgregarPunto.setBounds(tY.getX()+anchoBoton/2+espacioX/2+1, btnLimpiarPnts.getY(), anchoBoton/2, alturaBoton);
		add(btnAgregarPunto);
		
		
		JLabel lblFunciones = new JLabel("Funciones");
		lblFunciones.setFont(fuente);
		lblFunciones.setForeground(Colores.AZUL);
		lblFunciones.setBounds(espacioX, btnLimpiarPnts.getY()+alturaBoton+espacioY, anchoComboBox, alturaComboBox);
		add(lblFunciones);
		
		pFunciones = new JPanel();
		pFunciones.setLayout(new BoxLayout(pFunciones, BoxLayout.Y_AXIS));
		pFunciones.setBackground(Color.white);
		
		spFunciones = new ScrollPane(ventana);
		spFunciones.setBounds(espacioX, lblFunciones.getY()+lblFunciones.getHeight()+espacioY, anchoComboBox, alturaComboBox*6);
		spFunciones.setViewportView(pFunciones);
		
		add(spFunciones);
		
		btnLimpiarFunciones = new BotonOpaco("Limpiar");
		btnEliminarFunciones = new BotonOpaco("Eliminar");
		btnSeleccionar = new BotonOpaco("Seleccionar");
		
		btnLimpiarFunciones.setBounds(espacioX, spFunciones.getY()+spFunciones.getHeight()+espacioY, anchoBoton, alturaBoton);
		add(btnLimpiarFunciones);
		
		btnEliminarFunciones.setBounds(espacioX+anchoBoton+espacioX+1,  btnLimpiarFunciones.getY(), anchoBoton, alturaBoton);
		add(btnEliminarFunciones);
		
		btnSeleccionar.setBounds(btnEliminarFunciones.getX()+anchoBoton+espacioX+1, btnEliminarFunciones.getY(), anchoBoton, alturaBoton);
		add(btnSeleccionar);
		
		iniciarAccion();
	}
	
	private void iniciarAccion(){
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(cbTipo.getSelectedIndex()){
					case 0:
						cbModelo.removeAllItems();
						cbModelo.addItem("Modelo lineal");
						cbModelo.addItem("Modelo exponencial");
						cbModelo.addItem("Modelo potencial");
						break;
					case 1:
						cbModelo.removeAllItems();
						cbModelo.addItem("Lineales");
						cbModelo.addItem("Cuadráticos");
						cbModelo.addItem("Cúbicos");
						break;
				}
			}
		});
		
		btnObtenerFuncion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFuncion.setText(""); 
				ArrayList<Punto> puntos = canvas.getPlano().getPuntos();
				if(puntos.size() > 1 && cbTipo.getSelectedIndex() == 0){
					
					FuncionMinimoCuadrado funcion = null;
					switch(cbModelo.getSelectedIndex()){
						case 0:
							funcion = new FuncionModeloLineal(puntos);
							break;
						case 1:
							for(int i = 0; i < puntos.size(); i++){
								if(puntos.get(i).y < 0) {
									tFuncion.setText("Error: el método no puede utilizarse");
									return;
								}
							}
							funcion = new FuncionModeloExponencial(puntos);
							break;
						case 2:
							for(int i = 0; i < puntos.size(); i++){
								if(puntos.get(i).x < 0 || puntos.get(i).y < 0) {
									tFuncion.setText("Error: el método no puede utilizarse");
									return;
								}
							}
							funcion = new FuncionModeloPotencial(puntos);
							break;
					}
					
					if(funcion != null){
						agregarFuncion(funcion);
					}
				}
				else if(puntos.size() > 1 && cbTipo.getSelectedIndex() == 1){
					
					FuncionGrupoSpline funciones1 = null;
					
					int grupo = 0;
					
					for(int i = 0; i < funciones.size(); i++){
						if(FuncionSpline.class.isAssignableFrom(funciones.get(i).getClass())){
							FuncionSpline aux = FuncionSpline.class.cast(funciones.get(i));
							
							if(aux.getGrupo() == grupo) grupo++;
						}
					}
					
					switch(cbModelo.getSelectedIndex()){
						case 0:
							funciones1 = new FuncionSplineLineal(puntos, grupo);
							break;
						case 1 :
							funciones1 = new FuncionSplineCuadratico(puntos, grupo);
							break;
						case 2:
							funciones1 = new FuncionSplineCubico(puntos, grupo);
							break;
					}
					
					if(funciones1 != null){
						agregarFuncion(funciones1);
					}
				}
				else if(puntos.isEmpty()){
					tFuncion.setText("");
				}
				
				canvas.dibujar();
			}
		});
	
		btnAgregarPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					double x = Double.parseDouble(tX.getText());
					double y = Double.parseDouble(tY.getText());
					
					canvas.getPlano().agregarPunto(new Punto(x, y));
					canvas.verPuntos = true;
					
					ArrayList<Punto> puntos = canvas.getPlano().getPuntos();
					
					taPuntos.setText("");
					
					for(int i = 0; i < puntos.size(); i++){
						Punto punto2 = puntos.get(i);
						taPuntos.setText(taPuntos.getText()+"("+punto2.x+", "+punto2.y+")\n");
					}
					
					canvas.dibujar();
					canvas.dibujar();
				} catch(NumberFormatException ex){
					
				}
			}
		});
		btnEliminarPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					double x = Double.parseDouble(tX.getText());
					double y = Double.parseDouble(tY.getText());
					
					canvas.getPlano().eliminarPunto(new Punto(x, y));
					canvas.verPuntos = true;
					
					ArrayList<Punto> puntos = canvas.getPlano().getPuntos();
					
					taPuntos.setText("");
					
					for(int i = 0; i < puntos.size(); i++){
						Punto punto2 = puntos.get(i);
						taPuntos.setText(taPuntos.getText()+"("+punto2.x+", "+punto2.y+")\n");
					}
					
					canvas.dibujar();
					canvas.dibujar();
				} catch(NumberFormatException ex){
					
				}
			}
		});
		btnLimpiarPnts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taPuntos.setText("");
				tFuncion.setText("");
				canvas.getPlano().borrarPuntos();
				canvas.dibujar();
				canvas.dibujar();
			}
		});
		
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = 0;
				
				while(i < funciones.size() && funciones.get(i).ver == true) i++;
				
				if(i < funciones.size()){
					for(int j = 0; j < funciones.size(); j++) {
						funciones.get(j).ver = true;
						aFuncion.get(j).setSelected(true);
					}
				}
				else {
					for(int j = 0; j < funciones.size(); j++) {
						funciones.get(j).ver = false;
						aFuncion.get(j).setSelected(false);
					}
				}
				
				canvas.dibujar();
				canvas.dibujar();
			}
		});
		btnEliminarFunciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < funciones.size(); i++){
					if(aFuncion.get(i).isSelected()){
						canvas.eliminarFuncion(funciones.get(i));
						funciones.remove(i);
						aOrigen.remove(i);
						aFuncion.remove(i);
						pFunciones.remove(i);
						i--;
					}
				}
				
				spFunciones.setViewportView(pFunciones);
				
				tFuncion.setText("");
				
				canvas.dibujar();
				canvas.dibujar();
			}
		});
		btnLimpiarFunciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(!funciones.isEmpty()){
					canvas.eliminarFuncion(funciones.get(0));
					funciones.remove(0);
					aOrigen.remove(0);
					aFuncion.remove(0);
					pFunciones.remove(0);
				}
				spFunciones.setViewportView(pFunciones);
				
				tFuncion.setText("");
				
				canvas.dibujar();
				canvas.dibujar();
			}
		});
		
		
	}
	
	
	public JTextArea getTaPuntos(){
		return taPuntos;
	}


	public ArrayList<Funcion> getFunciones() {
		return funciones;
	}


	public void setFunciones(ArrayList<Funcion> funciones2) {
		// TODO Auto-generated method stub
		pFunciones.removeAll();
		funciones.clear();
		
		Funcion aux;
		for(int i = 0; i < funciones2.size(); i++){
			aux = funciones2.get(i);
			if(FuncionSpline.class.isAssignableFrom(aux.getClass())){
				agregarFuncion(FuncionSpline.class.cast(aux));
			}
			else if(FuncionMinimoCuadrado.class.isAssignableFrom(aux.getClass())){
				agregarFuncion(FuncionMinimoCuadrado.class.cast(aux));
			}
		}
	}


	private void agregarFuncion(FuncionSpline funcion) {
		tFuncion.setText("");
		
		if(canvas.agregarFuncion(funcion)){
			
			String intervalo = "  |  I: ";
			
			int ini = (int) funcion.getIniX().x;
			int fin = (int) funcion.getFinX().x;
			
			intervalo += ini+" ≤ x ≤ "+fin;
			
			CheckBox cbo = new CheckBox("Or", Colores.BLANCO, Colores.NEGRO);
			
			CheckBox cb = new CheckBox(funcion.getGrupo()+"  |  f(x) = "+funcion.getFuncion()+intervalo+"  |  M: "+funcion.getModelo(), Colores.BLANCO, Colores.NEGRO);
			cb.setSelected(true);
			cb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(cb.isSelected()){
					    canvas.eliminarFuncion(funciones.get(pFunciones.getComponentZOrder(cb.getParent())));
						canvas.agregarFuncion(funciones.get(pFunciones.getComponentZOrder(cb.getParent())));
						funciones.get(pFunciones.getComponentZOrder(cb.getParent())).ver = true;
					}
					else {
						funciones.get(pFunciones.getComponentZOrder(cb.getParent())).ver = false;
					}
					
					canvas.dibujar();
				}
			});
			
			cbo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(cbo.isSelected()){
						funciones.get(pFunciones.getComponentZOrder(cbo.getParent())).verOrigen = true;
					}
					else {
						funciones.get(pFunciones.getComponentZOrder(cbo.getParent())).verOrigen = false;
					}
					canvas.dibujar();
				}
			});
			
			JPanel p = new JPanel();
			p.setSize(pFunciones.getWidth(), cb.getHeight());
			p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
			p.setAlignmentX(LEFT_ALIGNMENT);
				
			p.setBackground(Color.white);
			cbo.setBackground(Color.white);
			cb.setBackground(Color.white);
			
			cbo.setFont(Fuentes.AREA_TEXTO);
			cb.setFont(Fuentes.AREA_TEXTO);
			
			BotonColorFuncion btnColor = new BotonColorFuncion(funcion.getColor(), p.getBackground());
			btnColor.setSize(10, 10);
			btnColor.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Color colorViejo = btnColor.getBackground();
					ColorChooser c = new ColorChooser(canvas, colorViejo){

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						protected void accionCancelar() {
							dispose();
							btnColor.setBackground(colorViejo);
							funcion.setColor(colorViejo);
							canvas.dibujar();
						}

						@Override
						protected void cambiarColor() {
							btnColor.setBackground(this.c.getColor());
							funcion.setColor(this.c.getColor());
							canvas.dibujar();
						}
						
					};
					c.setVisible(true);
				}
				
			});
			p.add(btnColor);
			p.add(cbo);
			p.add(cb);
			pFunciones.add(p);
			
			spFunciones.setViewportView(pFunciones);

			funciones.add(funcion);
			aOrigen.add(cbo);
			aFuncion.add(cb);
			
			canvas.dibujar();
		}
	}
}
