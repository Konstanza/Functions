package funciones.splines.cubico;

import java.io.Serializable;
import java.util.ArrayList;

import funciones.Punto;
import funciones.splines.FuncionGrupoSpline;

public class FuncionSplineCubico extends FuncionGrupoSpline implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncionSplineCubico(ArrayList<Punto> origen, int grupo) {
		super(origen, grupo);
	}

	protected FuncionSplineModeloCubico[] obtenerFunciones(ArrayList<Punto> puntos){
		FuncionSplineModeloCubico[] funciones = new FuncionSplineModeloCubico[puntos.size()-1];
		Double[] f2 = obtenerDerivadas(puntos);
		
		for(int i = 0; i < funciones.length; i++){
			double f2x_1 = f2[i];
			double f2x = f2[i+1];
			
			Punto punto = new Punto();
			punto.setLocation(f2x_1, f2x);
			
			funciones[i] = new FuncionSplineModeloCubico(puntos, puntos.get(i), puntos.get(i+1), punto, this);
		}
		
		return funciones;
	}

	private Double[] obtenerDerivadas(ArrayList<Punto> puntos){
		Double[] f2 = new Double[puntos.size()];
		f2[0] = 0.0;
		f2[f2.length-1] = 0.0;
		
		if(puntos.size() > 2) {
			Double[][] p = new Double[puntos.size()-2][puntos.size()-1];
			Double[] m = new Double[puntos.size()-2];
			
			Double[] x = new Double[puntos.size()];
			Double[] y = new Double[puntos.size()];
			
			for(int i = 0; i < x.length; i++){
				x[i] = puntos.get(i).x;
				y[i] = puntos.get(i).y;
			}
			
			
			// Llenar partes
			for(int i = 0; i < p.length; i++){
				if(i > 0) p[i][i-1] = (x[i+1]-x[i]);
				p[i][i] = 2*(x[i+2]-x[i]); 
				if(i < p.length-1) p[i][i+1] = (x[i+2]-x[i+1]);
				
				p[i][p.length] = (6/(x[i+2]-x[i+1]))*(y[i+2]-y[i+1]) + (6/(x[i+1]-x[i]))*(y[i]-y[i+1]);
			}
			
			for(int i = 0; i < p.length; i++){
				for(int j = 0; j < p.length; j++){
					if(p[i][j] == null) p[i][j] = 0.0;
				}
			}
			
			// Llenar m
			m[0] = 1.0;
			
			for(int i = 1; i < m.length; i++){
				double num = 0.0;
				double den = p[i][i-1];
				
				for(int j = 0; j < i; j++){
					num += m[j]*p[j][i-1];
				}
				
				num *= -1;
				
				m[i] = num/den;
			}
			
			double num1 = 0;
			double den1 = 0;
			
			for(int i = 0; i < m.length; i++){
				num1 += p[i][p.length]*m[i];
				den1 += p[i][p.length-1]*m[i];
			}
			
			f2[p.length] = num1/den1;
			
			for(int i = p.length-1; i > 0; i--){
				double num = p[i][p.length];
				double den = p[i][i-1];
				
				for(int j = p.length; j >= i; j--){
					if(j != i-1){
						num -= p[i][j]*f2[j+1];
					}
				}
				
				f2[i] = num/den;
			}
			
			/*
			for(int i = 0; i < p.length; i++){
				System.out.println("\n");
				for(int j = 0; j < p.length+1; j++){
					System.out.print(""+p[i][j]+"...");
				}
				System.out.print("...m: "+m[i]);
			}*/
			
			System.out.println();
			for(int i = 0; i < f2.length; i++) System.out.println("f\"x: "+f2[i]);
			
		}
		
		return f2;
	}
	
}
