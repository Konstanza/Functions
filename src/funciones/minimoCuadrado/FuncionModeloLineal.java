package funciones.minimoCuadrado;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;

import funciones.Punto;


public class FuncionModeloLineal extends FuncionMinimoCuadrado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncionModeloLineal(ArrayList<Punto> origen) {
		super(origen, "Lineal");
		obtenerFuncion();
	}


	public double obtenerY(double x){
		return (b+a*x);
	}
	
	protected void obtenerFuncion(){
		Punto[] puntos = this.origen;
		int n = puntos.length;
		
		double Ex = 0, Ey = 0, Exy = 0, Ex2 = 0, E2x;
		
		for(int i = 0; i < n; i++){
			double x =  puntos[i].getX();
			double y =  puntos[i].getY();
			
			Ex += x;
			Ey += y;
			Exy += x*y;
			Ex2 += Math.pow(x, 2);
		}
		
		E2x = Math.pow(Ex, 2);
		
		double my = Ey/n;
		double mx = Ex/n;
		double a = (n*Exy-Ex*Ey)/(n*Ex2-E2x);
		double b = my - a*mx;
		
		DecimalFormatSymbols s = new DecimalFormatSymbols();
		s.setDecimalSeparator('.');
		
		DecimalFormat form = new DecimalFormat("##0.####E0", s);
		
		this.a = a;
		this.b = b;
		String funcion = "("+form.format(this.b)+")+("+form.format(this.a)+")x";
		
		this.funcion = funcion;
		
		double Est = 0, Esr = 0;
		
		for(int i = 0; i < n; i++){
			double x = puntos[i].getX();
			double y = puntos[i].getY();
			
			Est += (y - my)*(y - my);
			Esr += (y - a*x -b)*(y - a*x -b);
		}
		
		form = new DecimalFormat("0.##", s);
		
		try {
			this.R2 = form.parse(form.format(((Est-Esr)/Est)*100)).floatValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("a: "+a+"       b: "+b);
	}
}
