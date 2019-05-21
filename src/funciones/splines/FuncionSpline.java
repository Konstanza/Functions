package funciones.splines;
import java.io.Serializable;
import java.util.ArrayList;

import funciones.Funcion;
import funciones.Punto;

public abstract class FuncionSpline extends Funcion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected FuncionGrupoSpline grupo;
	protected Punto iniX;
	protected Punto finX;
	
	public FuncionSpline(ArrayList<Punto> origen, String modelo, Punto iniX, Punto finX, FuncionGrupoSpline grupo) {
		super(origen, grupo.color);
		this.modelo = modelo;
		this.iniX = iniX;
		this.finX = finX;
		this.grupo = grupo;
	}
	
	protected abstract void obtenerFuncion();
	
	public int getGrupo(){
		return grupo.getGrupo();
	}

	public Punto getIniX() {
		return iniX;
	}
	
	public Punto getFinX(){
		return finX;
	}

}
