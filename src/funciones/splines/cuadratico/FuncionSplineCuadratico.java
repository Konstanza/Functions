package funciones.splines.cuadratico;

import java.io.Serializable;
import java.util.ArrayList;

import funciones.Punto;
import funciones.splines.FuncionGrupoSpline;

public class FuncionSplineCuadratico extends FuncionGrupoSpline implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncionSplineCuadratico(ArrayList<Punto> origen, int grupo) {
		super(origen, grupo);
	}

	@Override
	protected FuncionSplineModeloCuadratico[] obtenerFunciones(ArrayList<Punto> puntos) {
		FuncionSplineModeloCuadratico[] funciones = new FuncionSplineModeloCuadratico[puntos.size()-1];
		
		Double[][] p = new Double[funciones.length][3];
		
		Double[] x = new Double[puntos.size()];
		Double[] y = new Double[puntos.size()];
		
		for(int i = 0; i < x.length; i++){
			x[i] = puntos.get(i).x;
			y[i] = puntos.get(i).y;
		}
		
		p[0][0] = 0.0; // a1
		p[0][1] = (y[1]-y[0])/(x[1]-x[0]); // b1
		p[0][2] = y[0]-p[0][1]*x[0]; // c1
		
		for(int i = 1; i < p.length; i++){
			double num;
			double den;
			
			num = y[i+1]-y[i]-((x[i]*x[i+1]-Math.pow(x[i], 2))*2*p[i-1][0])-(x[i+1]-x[i])*p[i-1][1];
			den = (Math.pow(x[i], 2)+Math.pow(x[i+1], 2)-2*x[i]*x[i+1]);
			
			p[i][0] = num/den; // a
			p[i][1] = (p[i-1][0]-p[i][0])*2*x[i]+p[i-1][1]; // b
			p[i][2] = y[i]-p[i][1]*x[i]-Math.pow(x[i], 2)*p[i][0]; // c
		}
		
		System.out.println();
		for(int i = 0; i < funciones.length; i++){
			
			funciones[i] = new FuncionSplineModeloCuadratico(puntos, puntos.get(i), puntos.get(i+1), p[i][0], p[i][1], p[i][2], this);
		}
		
		return funciones;
	}

}
