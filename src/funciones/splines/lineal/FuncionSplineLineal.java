package funciones.splines.lineal;

import java.io.Serializable;
import java.util.ArrayList;

import funciones.Punto;
import funciones.splines.FuncionGrupoSpline;
import funciones.splines.FuncionSpline;

public class FuncionSplineLineal extends FuncionGrupoSpline implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncionSplineLineal(ArrayList<Punto> origen, int grupo) {
		super(origen, grupo);
	}

	protected FuncionSpline[] obtenerFunciones(ArrayList<Punto> puntos){
		FuncionSplineModeloLineal[] funciones = new FuncionSplineModeloLineal[puntos.size()-1];
		
		for(int i = 0; i < funciones.length; i++){
			funciones[i] = new FuncionSplineModeloLineal(puntos, puntos.get(i), puntos.get(i+1), this);
		}
		
		return funciones;
	}

}
