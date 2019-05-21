package funciones.splines.lineal;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import funciones.Punto;
import funciones.splines.FuncionGrupoSpline;
import funciones.splines.FuncionSpline;

public class FuncionSplineModeloLineal extends FuncionSpline implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double m;
	
	public FuncionSplineModeloLineal(ArrayList<Punto> origen, Punto iniX, Punto finX, FuncionGrupoSpline grupo) {
		super(origen, "Spline Lineal", iniX, finX, grupo);
		obtenerFuncion();
	}

	protected void obtenerFuncion() {
		double xi =  iniX.getX();
		double xi1 =  finX.getX();
		double fxi =  iniX.getY();
		double fxi1 =  finX.getY();
		double m =  ((fxi1-fxi)/(xi1-xi));
		
		DecimalFormatSymbols s = new DecimalFormatSymbols();
		s.setDecimalSeparator('.');
		
		DecimalFormat form = new DecimalFormat("##0.####E0", s);
		
		this.m = m;
		this.funcion = "("+form.format(fxi)+")+("+form.format(m)+")(x-("+form.format(xi)+"))";
	}

	@Override
	public double obtenerY(double x) {
		
		double fxi =  iniX.getY();
		double m = this.m;
		double xi =  iniX.getX();
		
		return (fxi+m*(x-xi));
	}

}
