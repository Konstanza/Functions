package interfaz.botones.flechas;

import java.awt.Polygon;

public class BotonFlechaIzquierda extends BotonFlecha{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BotonFlechaIzquierda(int t){
		this.setText("Izquierda");
		p1 = new Polygon();
		p1.addPoint(t, 0);
		p1.addPoint(t, t);
		p1.addPoint(0, t/2);
	}
}
