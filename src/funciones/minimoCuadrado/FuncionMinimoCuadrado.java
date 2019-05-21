package funciones.minimoCuadrado;

import java.io.Serializable;
import java.util.ArrayList;

import funciones.Funcion;
import funciones.Punto;

public abstract class FuncionMinimoCuadrado extends Funcion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected float R2;
	protected double a;
	protected double b;
	
	public FuncionMinimoCuadrado(ArrayList<Punto> origen, String modelo) {
		super(origen);
		this.modelo = modelo;
	}
	
	protected abstract void obtenerFuncion();
	
	public abstract double obtenerY(double x);
	
	public float getR2() {
		return R2;
		}

}
