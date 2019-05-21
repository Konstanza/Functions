package interfaz.botones.flechas;

import java.awt.Polygon;

public class BotonFlechaAbajo extends BotonFlecha{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BotonFlechaAbajo(int t){
		this.setText("Abajo");
		p1 = new Polygon();
		p1.addPoint(0, 0);
		p1.addPoint(t, 0);
		p1.addPoint(t/2, t);
	}
}
