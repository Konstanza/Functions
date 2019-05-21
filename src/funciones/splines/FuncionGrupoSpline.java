package funciones.splines;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import funciones.Punto;

public abstract class FuncionGrupoSpline implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Color color;
	protected int grupo;
	protected FuncionSpline[] funciones;

	public FuncionGrupoSpline(ArrayList<Punto> origen, int grupo){
		this.grupo = grupo;
		
		Random gen = new Random();
		color = new Color(gen.nextInt(256-60)+50, gen.nextInt(256-60)+50, gen.nextInt(256-60)+50);
		
		this.funciones = obtenerFunciones(origen);
	}
	
	public FuncionSpline[] getFunciones() {
		return funciones;
	}

	public int getGrupo() {
		return grupo;
	}
	
	protected abstract FuncionSpline[] obtenerFunciones(ArrayList<Punto> origen);
}
