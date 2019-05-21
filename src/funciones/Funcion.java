package funciones;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Funcion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String funcion;
	protected String modelo;
	protected Color color;
	protected Punto[] origen;
	public boolean verOrigen;
	public boolean ver;
	
	public Punto[] getOrigen() {
		return origen;
	}

	public Funcion(ArrayList<Punto> origen){
		verOrigen = false;
		ver = true;
		
		this.origen = new Punto[origen.size()];
		for(int i = 0; i < this.origen.length; i++){
			this.origen[i] = new Punto();
			this.origen[i].setLocation(origen.get(i).getX(), origen.get(i).getY());
		}
		
		Random gen = new Random();
		color = new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
	}
	
	public Funcion(ArrayList<Punto> origen, Color color){
		verOrigen = false;
		ver = true;
		
		this.origen = new Punto[origen.size()];
		for(int i = 0; i < this.origen.length; i++){
			this.origen[i] = new Punto();
			this.origen[i].setLocation(origen.get(i).getX(), origen.get(i).getY());
		}
		
		this.color = color;
	}

	public abstract double obtenerY(double x);
	
	public String getFuncion(){
		return funcion;
	}
	
	public String getModelo() {
		return modelo;
	}

	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
}
