package funciones.splines.cuadratico;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import funciones.Punto;
import funciones.splines.FuncionGrupoSpline;
import funciones.splines.FuncionSpline;

public class FuncionSplineModeloCuadratico extends FuncionSpline implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double a, b, c;
	
	public FuncionSplineModeloCuadratico(ArrayList<Punto> origen, Punto iniX, Punto finX, double a, double b, double c,
			FuncionGrupoSpline grupo) {
		super(origen, "Spline cuadratico", iniX, finX, grupo);
		this.a = a;
		this.b = b;
		this.c = c;
		obtenerFuncion();
	}

	protected void obtenerFuncion() {
		DecimalFormatSymbols s = new DecimalFormatSymbols();
		s.setDecimalSeparator('.');
		
		DecimalFormat form = new DecimalFormat("##0.####E0", s);
		
		this.funcion = "("+form.format(a)+")x²+("+form.format(b)+")x+("+form.format(c)+")";
		
		System.out.println("a: "+a+"     b: "+b+"     c: "+c);
	}
	
	@Override
	public double obtenerY(double x) {
		return (a*Math.pow(x, 2)+b*x+c);
	}
}
