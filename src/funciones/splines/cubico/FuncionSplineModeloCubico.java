package funciones.splines.cubico;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import funciones.Punto;
import funciones.splines.FuncionGrupoSpline;
import funciones.splines.FuncionSpline;

public class FuncionSplineModeloCubico extends FuncionSpline implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Punto f2;
	
	public FuncionSplineModeloCubico(ArrayList<Punto> origen, Punto iniX, Punto finX, Punto f2, FuncionGrupoSpline grupo) {
		super(origen, "Spline Cubico", iniX, finX, grupo);
		this.f2 = f2;
		obtenerFuncion();
	}

	protected void obtenerFuncion() {
		DecimalFormatSymbols s = new DecimalFormatSymbols();
		s.setDecimalSeparator('.');
		
		DecimalFormat form = new DecimalFormat("##0.####E0", s);
		
		double x_1 = iniX.getX();
		double x =  finX.getX();
		
		double fx_1 =  iniX.getY();
		double fx = finX.getY();
		
		double f2x_1 = f2.getX();
		double f2x = f2.getY();
		
		this.funcion = "("+form.format(f2x_1/(6*(x-x_1)))+")("+form.format(x)+"-x)³+("+form.format(f2x/(6*(x-x_1)))+")(x-("+form.format(x_1)+"))³+("+form.format((fx_1/(x-x_1)) - (((f2x_1*(x-x_1)))/6))+")("+form.format(x)+"-x)+("+form.format((fx/(x-x_1)) - ((f2x*(x-x_1))/6))+")(x-("+form.format(x_1)+"))";
	
		
	}

	@Override
	public double obtenerY(double x1) {
		
		double x_1 =  iniX.getX();
		double x = finX.getX();
		
		double fx_1 =  iniX.getY();
		double fx =  finX.getY();
		
		double f2x_1 = f2.getX();
		double f2x =  f2.getY();
		
		double y = 0;
		
		y += (f2x_1/(6*(x-x_1)))*Math.pow(x-x1, 3);
		
		y += (f2x/(6*(x-x_1)))*Math.pow(x1-x_1, 3);
		
		y += (x-x1)*((fx_1/(x-x_1)) - ((f2x_1*(x-x_1))/6));
		
		y += (x1-x_1)*((fx/(x-x_1)) - ((f2x*(x-x_1))/6));
		
		return y;
	}
}
