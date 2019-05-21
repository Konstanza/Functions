package plano;

import java.awt.Point;
import java.util.ArrayList;

import funciones.Punto;

public class Plano{
	
	private int ancho;
	private int alto;
	
	private DibujoPlano canvas;
	
	ArrayList<Punto> puntos;
	
	public Plano(int ancho, int alto, DibujoPlano canvas){
		this.ancho = ancho;
		this.alto = alto;
		this.canvas = canvas;
		puntos = new ArrayList<Punto>();
	}
	
	public Punto convertirPosicionDePantalla(Point puntoDibujo){
		Point escala = canvas.getEscala();
		Point desplazamiento = canvas.getDesplazamiento();
		
		Punto posicion = new Punto();
		
		posicion.setLocation(puntoDibujo.x - ancho/2, (puntoDibujo.y - alto/2)*(-1));
		
		posicion.x -= desplazamiento.x;
		posicion.y += desplazamiento.y;
		
		if(escala.x > 0){
			posicion.x /= Math.pow(10, escala.x);
		}
		else if(escala.x < 0){
			posicion.x *= Math.pow(10, escala.x*-1);
		}
		
		if(escala.y > 0){
			posicion.y /= Math.pow(10, escala.y);
		}
		else if(escala.y < 0){
			posicion.y *= Math.pow(10, escala.y*-1);
		}
		
	    return posicion;
	}
	
	public void agregarPunto(Punto point){
		for(int i = 0; i < puntos.size(); i++){
			if(point.x == puntos.get(i).x && point.y == puntos.get(i).y){
				return;
			}
			if(puntos.get(i).x >= point.x){
				puntos.add(i, point);
				return;
			}
		}
		
		puntos.add(point);
	}
	
	public void agregarPunto(Point punto){
		Punto point = convertirPosicionDePantalla(punto);
		
		for(int i = 0; i < puntos.size(); i++){
			if(point.x == puntos.get(i).x && point.y == puntos.get(i).y){
				return;
			}
			if(puntos.get(i).x >= point.x){
				puntos.add(i, point);
				return;
			}
		}
		
		puntos.add(point);
	}
	
	public void setSize(int ancho, int alto){
		this.ancho = ancho;
		this.alto = alto;
	}

	public ArrayList<Punto> getPuntos() {
	    return puntos;
	}

	public Point convertirPosicionAPantalla(Punto puntoPlano) {
		Point escala = canvas.getEscala();
		Point desplazamiento = canvas.getDesplazamiento();
		
		Punto posicion = new Punto();
		
		posicion.setLocation(puntoPlano.x, puntoPlano.y);
		
		if(escala.x > 0){
			posicion.x *= Math.pow(10, escala.x);
		} 
		else if(escala.x < 0){
			posicion.x /= Math.pow(10, escala.x*-1);
		}
		
		if(escala.y > 0){
			posicion.y *= Math.pow(10, escala.y);
		} 
		else if(escala.y < 0){
			posicion.y /= Math.pow(10, escala.y*-1);
		}
		
		posicion.x += ancho/2+desplazamiento.x;
		
		posicion.y -= desplazamiento.y;
		posicion.y /= -1;
		posicion.y += alto/2;
		
		Point p = new Point();
		
		p.setLocation(posicion.x, posicion.y);
	    return p;
	}
	

	public void eliminarPunto(Punto point){
		for(int i = 0; i < puntos.size(); i++){
			if(point.x == puntos.get(i).x && point.y == puntos.get(i).y){
				puntos.remove(i);
				return;
			}
		}
		
	}
	
	public void borrarPuntos() {
		puntos = new ArrayList<Punto>();
	}
}
