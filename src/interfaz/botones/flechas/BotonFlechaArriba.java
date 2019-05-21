package interfaz.botones.flechas;

import java.awt.Polygon;

public class BotonFlechaArriba extends BotonFlecha{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BotonFlechaArriba(int t) {
		this.setText("Arriba");
		p1 = new Polygon();
		p1.addPoint(0, t);
		p1.addPoint(t, t);
		p1.addPoint(t/2, 0);
	}


}
