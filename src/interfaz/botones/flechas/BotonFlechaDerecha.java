package interfaz.botones.flechas;

import java.awt.Polygon;

public class BotonFlechaDerecha extends BotonFlecha{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BotonFlechaDerecha(int t){
		this.setText("Derecha");
		p1 = new Polygon();
		p1.addPoint(0, 0);
		p1.addPoint(0, t);
		p1.addPoint(t, t/2);
	}
}
