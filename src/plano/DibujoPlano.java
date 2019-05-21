package plano;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import principal.ventanas.Ventana;
import funciones.Funcion;
import funciones.Punto;
import funciones.minimoCuadrado.FuncionMinimoCuadrado;
import funciones.splines.FuncionSpline;

public class DibujoPlano extends Canvas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plano plano;
	private int ancho, alto;
	public ArrayList<Funcion> funciones;
	public boolean verPuntos;
	private Point posicion;
	private Point escala;
	private Point desplazamiento;
	private Color colorFondo;
	private Color colorEjes;
	private Color colorCursor;
	private Color colorPuntos;
	private ControlPlano control;
	private Color colorFunciones;
	private boolean colorRandom;
	
	public DibujoPlano(int ancho, int alto, Ventana ventana){
		this.ancho = ancho;
		this.alto = alto;
		/*
		fondo = new Color(0,50,10);
		ejes = new Color(0,200,0);
		cursor = Color.yellow;
		puntos = Color.red;
		*/
		
		/*
		fondo = Color.black;
		ejes = Color.white;
		cursor = Color.red;
		puntos = Color.magenta;
		*/
		
		colorFondo = new Color(12,12,12);
		colorEjes = new Color(230,230,230);
		colorCursor = new Color(220, 150, 0);
		colorPuntos = Color.ORANGE;
		colorFunciones = colorEjes;
		colorRandom = true;
		
		/*
		fondo = new Color(230,230,230);
		ejes = new Color(100,100,100);
		cursor = new Color(0,0,150);
		puntos = new Color(0,0, 100);
		*/
		
		posicion = new Point();
		posicion.setLocation(0, 0);
		
		funciones = new ArrayList<Funcion>();
		
		verPuntos = true;
		
		desplazamiento = new Point(0,0);
		escala = new Point(0,0);
		
		plano = new Plano(ancho, alto, this);
		
		//Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
		
		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		setCursor(blankCursor);
				
		setIgnoreRepaint(true);
		setPreferredSize(new Dimension(ancho,alto));
		setMinimumSize(new Dimension(ancho,alto));
		setFocusable(true);
		requestFocus();
		
		control = new ControlPlano(ventana, this);
		addMouseListener(control.getMouseAdapter());
		addMouseMotionListener(control.getMouseMotionAdapter());
		addKeyListener(control.getKeyAdapter());
		
	}
	
	
	public void dibujar(){
		BufferStrategy buffer = getBufferStrategy();
		
		if(buffer == null){
			createBufferStrategy(2);
			return;
		}
		
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		
		g.setBackground(colorFondo);
		g.clearRect(0, 0, ancho, alto);
		
		double factorX = 1;
		double factorY = 1;
		
		g.scale(factorX, factorY);
		
		dibujarEjes(g);
		
		for(int i = 0; i < funciones.size(); i++){
			dibujarFuncion(g, funciones.get(i));
		}
		
		if(verPuntos)dibujarPuntos(g);
		
		dibujarCursor(g);
		
		Toolkit.getDefaultToolkit().sync();
		
		g.dispose();
		
		buffer.show();
	}
	
	public boolean guardar(File file){
		BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = imagen.createGraphics();
		
		g.setBackground(colorFondo);
		g.clearRect(0, 0, ancho, alto);
		
		dibujarEjes(g);
		
		for(int i = 0; i < funciones.size(); i++){
			dibujarFuncion(g, funciones.get(i));
		}
		
		if(verPuntos)dibujarPuntos(g);
		
		//Toolkit.getDefaultToolkit().sync();
		
		g.dispose();
		
		String tipo = file.getName();
		
		tipo = tipo.substring(tipo.lastIndexOf('.')+1);
		
		System.out.println(file.getPath());
		
		try {
			ImageIO.write((RenderedImage) imagen, tipo, file);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean guardar(File file, String tipo){
		BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = imagen.createGraphics();
		
		g.setBackground(colorFondo);
		g.clearRect(0, 0, ancho, alto);
		
		
		dibujarEjes(g);
		
		for(int i = 0; i < funciones.size(); i++){
			dibujarFuncion(g, funciones.get(i));
		}
		
		if(verPuntos)dibujarPuntos(g);
		
		//Toolkit.getDefaultToolkit().sync();
		
		g.dispose();
		
		File file2 = new File(file.getPath()+"."+tipo);
		
		System.out.println(file2.getPath());
		try {
			System.out.println(ImageIO.write((RenderedImage) imagen, tipo, file2));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private void dibujarPuntos(Graphics2D g) {

		g.setColor(colorPuntos);
		
		for(int i = 0; i < plano.puntos.size(); i++){
			Point punto = new Point();
			punto.setLocation( plano.convertirPosicionAPantalla(plano.puntos.get(i)));
			g.fillRect(punto.x-1, punto.y-1, 3, 3);
		}
	}


	private void dibujarEjes(Graphics2D g) {
		g.setColor(colorEjes);
		
		//g.drawRect(0, 0, ancho-1, alto-1);
		
		boolean rX = true, rY = true;
		
		Point posicion = new Point(this.desplazamiento);
		
		if(posicion.x > ancho/2){
			posicion.x = ancho/2;
			rX = false;
		}
		else if(posicion.x < -ancho/2){
			posicion.x = -ancho/2;
			rX = true;
		}
		
		if(posicion.y > alto/2){
			posicion.y = alto/2;
			rY = false;
		}
		else if(posicion.y < -alto/2){
			posicion.y = -alto/2;
			rY = true;
		}
		
		
		g.drawLine(ancho/2+posicion.x, 0, ancho/2+posicion.x, alto-1);
		g.drawLine(0, alto/2+posicion.y, ancho-1, alto/2+posicion.y);
		
		g.setFont(new Font("Tahoma", 0, 10));
		
		for(int x = ancho/2+desplazamiento.x; x >= 0; x -= 100){
			if(x == ancho/2+desplazamiento.x) continue;
			
			Punto point = plano.convertirPosicionDePantalla(new Point(x, alto/2));
			
			g.drawLine(x, alto/2-1+posicion.y, x, alto/2+1+posicion.y);
			
			String p = ""+point.getX();
			Rectangle2D r = g.getFont().getStringBounds(p, g.getFontRenderContext());
			
			if(rY) g.drawString(""+point.getX(), x, alto/2+15+posicion.y);
			else g.drawString(""+point.getX(), x, alto/2-(int)r.getHeight()+5+posicion.y);
		}
		
		for(int x = ancho/2+desplazamiento.x; x < ancho; x += 100){
			if(x == ancho/2+desplazamiento.x) continue;
			
			Punto point = plano.convertirPosicionDePantalla(new Point(x, alto/2));
			
			g.drawLine(x, alto/2-1+posicion.y, x, alto/2+1+posicion.y);
			
			String p = ""+point.getX();
			Rectangle2D r = g.getFont().getStringBounds(p, g.getFontRenderContext());
			
			if(rY) g.drawString(""+point.getX(), x, alto/2+15+posicion.y);
			else g.drawString(""+point.getX(), x, alto/2-(int)r.getHeight()+5+posicion.y);
		}
		
		for(int y = alto/2+desplazamiento.y; y >= 0; y -= 100){
			if(y == alto/2+desplazamiento.y) continue;
			
			Punto point = plano.convertirPosicionDePantalla(new Point(ancho/2, y));
			
			
			g.drawLine(ancho/2-1+posicion.x, y, ancho/2+1+posicion.x, y);
			
			String p = ""+point.getY();
			Rectangle2D r = g.getFont().getStringBounds(p, g.getFontRenderContext());
			
			if(rX) g.drawString(p, ancho/2+5+posicion.x, y);
			else g.drawString(p, (ancho/2-(int)r.getWidth()+posicion.x)-3, y);

		}
		
		for(int y = alto/2+desplazamiento.y; y < alto; y += 100){
			if(y == alto/2+desplazamiento.y) continue;
			
			Punto point = plano.convertirPosicionDePantalla(new Point(ancho/2, y));
			
			g.drawLine(ancho/2-1+posicion.x, y, ancho/2+1+posicion.x, y);
			
			String p = ""+point.getY();
			Rectangle2D r = g.getFont().getStringBounds(p, g.getFontRenderContext());
			
			if(rX) g.drawString(p, ancho/2+5+posicion.x, y);
			else g.drawString(p, (ancho/2-(int)r.getWidth()+posicion.x)-3, y);
		}
		
		g.setFont(new Font("Tahoma", 0, 11));
	}


	private void dibujarCoordenadas(Graphics2D g){
		if(posicion == null){
			posicion = new Point();
			posicion.setLocation(0, 0);
		}
		
		Punto p = plano.convertirPosicionDePantalla(posicion);
		Point p2 = new Point(posicion);
		String p3 = "X: "+p.x+" , Y: "+p.y;
		
		Rectangle2D r = g.getFont().getStringBounds(p3, g.getFontRenderContext());
		
		if(p2.x-2-(int)r.getWidth()/2 <= 0) {
			p2.x += 5;
		}
		else if(p2.x+(int)r.getWidth()/2 >= ancho-1){
			p2.x = p2.x-2-(int)r.getWidth()-5;
		}
		else {
			p2.x = p2.x-2-(int)r.getWidth()/2;
		}
		
		if(p2.y-(int)r.getHeight()-5 <= 0) {
			p2.y += 3; 
		}
		else {
			p2.y = p2.y-(int)r.getHeight()-5;
		}
		
		g.setColor(colorFondo);
		g.fillRect(p2.x, p2.y, (int)r.getWidth()+3, (int) r.getHeight()+1);
		
		g.setColor(colorCursor);
		g.drawRect(p2.x, p2.y, (int)r.getWidth()+3, (int) r.getHeight()+1);
		
		g.setColor(colorEjes);
		g.drawString(p3, p2.x+2, p2.y+(int)r.getHeight());
	}
	
	private void dibujarCursor(Graphics2D g){
		dibujarCoordenadas(g);
		
		g.setColor(colorCursor);
		g.fillRect(posicion.x-1, posicion.y-1, 3, 3);
	}
	
	private void dibujarFuncion(Graphics2D g, Funcion funcion){
		
		if(colorRandom)g.setColor(funcion.getColor());
		else g.setColor(colorFunciones);
		
		if(funcion.ver){
		Punto ini = new Punto();
		Punto fin = new Punto();
		
		if(FuncionSpline.class.isAssignableFrom(funcion.getClass())){
			ini.x = FuncionSpline.class.cast(funcion).getIniX().x;
			fin.x = FuncionSpline.class.cast(funcion).getFinX().x;
			
			Point ini2, fin2;
			
			ini2 = plano.convertirPosicionAPantalla(ini);
			fin2 = plano.convertirPosicionAPantalla(fin);
			
			if(ini2.x < 0){ 
				ini = plano.convertirPosicionDePantalla(new Point(0,alto-1));
			}
			else {
				ini.y = plano.convertirPosicionDePantalla(new Point(0,alto-1)).y;
			}
			
			if(fin2.x >= ancho){
				fin = plano.convertirPosicionDePantalla(new Point(ancho-1, 0));
			}
			else {
				fin.y = plano.convertirPosicionDePantalla(new Point(ancho-1, 0)).y;
			}
			
		} else {
			ini = plano.convertirPosicionDePantalla(new Point(0,alto-1));
			fin = plano.convertirPosicionDePantalla(new Point(ancho-1, 0));
		}
		
		double e = (1/(Math.pow(10, escala.x))); 
				
		for(double x = ini.x; x <= fin.x; x += e){
			double y = funcion.obtenerY(x);
			
			if(y >= ini.y && y <= fin.y){
				Point punto = plano.convertirPosicionAPantalla(new Punto(x, y));
				
				g.fillRect(punto.x, punto.y, 1, 1);
			}
		}
		}
		if(funcion.verOrigen){
			Punto[] origen = funcion.getOrigen();
			
			for(int i = 0; i < origen.length; i++){
				Point punto = plano.convertirPosicionAPantalla(new Punto((float)origen[i].getX(), (float)origen[i].getY()));
				g.fillRect(punto.x-1, punto.y-1, 3, 3);
			}
		}
	}
	
	public Plano getPlano(){
		return plano;
	}
	
	public Point getPosicion(){
		return posicion;
	}
	
	public Point obtenerPosicionMouse(){
		Point posicion;
	    posicion = this.getMousePosition();
		return posicion;
	}

	public boolean eliminarFuncion(Funcion funcion){
		return funciones.remove(funcion);
	}


	public boolean agregarFuncion(Funcion funcion) {
		if(FuncionMinimoCuadrado.class.isAssignableFrom(funcion.getClass())){
			for(int i = 0; i < funciones.size(); i++){
				if(funcion.getFuncion().equals(funciones.get(i).getFuncion())){
					return false;
				}
			}
		}
		
		funciones.add(funcion);
		return true;
	}
	
	public int getAncho() {
		return ancho;
	}


	public int getAlto() {
		return alto;
	}


	public int getPosicionX() {
		return posicion.x;
	}
	
	public int getPosicionY() {
		return posicion.y;
	}

	public void setPosicionX(int posicionX) {
		if(posicionX >= 0 && posicionX < ancho)posicion.x = posicionX;
	}
	
	public void setPosicionY(int posicionY){
		if(posicionY >= 0 && posicionY < alto)posicion.y = posicionY;
	}

	public void setPosicion() {
		posicion = obtenerPosicionMouse();
	}

	public int getDesplazamientoX(){
		return desplazamiento.x;
	}

	public int getDesplazamientoY(){
		return desplazamiento.y;
	}
	
	public Point getEscala(){
		return escala;
	}
	
	public Point getDesplazamiento(){
		return desplazamiento;
	}


	public void aumentarEscala(int x, int y) {
		if(escala.x < 4) escala.x += x;
		if(escala.y < 4) escala.y += y;
	}
	
	public void disminuirEscala(int x, int y){
		if(escala.x > -5) escala.x -= x;
		if(escala.y > -5) escala.y -= y;
	}


	public void disminuirDesplazamiento(int x, int y) {
		desplazamiento.x -= x;
		desplazamiento.y -= y;
	}


	public void aumentarDesplazamiento(int x, int y) {
		desplazamiento.x += x;
		desplazamiento.y += y;
	}


	public void setDesplazamiento(int x, int y) {
		desplazamiento.setLocation(x, y);
	}


	public void setEscala(int x, int y) {
		escala.setLocation(x, y);
	}

	public void setSize(int i, int j) {
		this.setBounds(getX(), getY(), i, j);
		plano.setSize(i, j);
		this.ancho = i;
		this.alto = j;
	}


	public ControlPlano getControl() {
		return control;
	}


	public void guardar(ObjectOutputStream os) {
		try {
			os.writeObject(ancho);
			os.writeObject(alto);
			os.writeObject(desplazamiento);
			os.writeObject(escala);
			os.writeObject(colorFondo);
			os.writeObject(colorEjes);
			os.writeObject(colorPuntos);
			os.writeObject(colorCursor);
			os.writeObject(colorFunciones);
			for(int i = 0; i < funciones.size(); i++)os.writeObject(funciones.get(i));
			for(int i = 0; i < plano.puntos.size(); i++)os.writeObject(plano.puntos.get(i)); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void eliminarFunciones() {
		funciones.clear();
	}


	public void setPuntos(ArrayList<Punto> puntos) {
		plano.puntos.clear();
		for(int i = 0; i < puntos.size(); i++)control.agregarPunto(puntos.get(i));
	}


	public void setColorFondo(Color color) {
		this.colorFondo = color;
	}


	public Color getColorFondo() {
		return colorFondo;
	}


	public Color getColorEjes() {
		return colorEjes;
	}

	public Color getColorPuntos() {
		return colorPuntos;
	}
	
	public Color getColorCursor() {
		return colorCursor;
	}


	public void setColorEjes(Color color) {
		colorEjes = color;
	}
	public void setColorPuntos(Color color) {
		colorPuntos = color;
	}
	public void setColorCursor(Color color) {
		colorCursor = color;
	}

	public Color getColorFunciones() {
		return colorFunciones;
	}
	public void setColorFunciones(Color color) {
		colorFunciones = color;
	}


	public void colorRandomOn() {
		colorRandom = true;
	}
	public void colorRandomOff() {
		colorRandom = false;
	}


	public void reiniciarColores() {
		colorFondo = new Color(12,12,12);
		colorEjes = new Color(230,230,230);
		colorCursor = new Color(220, 150, 0);
		colorPuntos = Color.ORANGE;
		colorFunciones = colorEjes;
		colorRandom = true;
	}

}
