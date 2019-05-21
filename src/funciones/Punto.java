package funciones;

import java.awt.Point;
import java.io.Serializable;

public class Punto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double x;
	public double y;
	
	public Punto(Point point) {
		x = point.x;
		y = point.y;
	}

	public Punto() {
		x = 0.0;
		y = 0.0;
	}

	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
