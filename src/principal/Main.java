package principal;

import principal.ventanas.SplashScreen;
import principal.ventanas.Ventana;


public class Main {

	private static Ventana v;
	
	public static void main(String[] args) {
		
		SplashScreen s = new SplashScreen();
		v = new Ventana(s);
		
		try {
			for(int i = 0; i < 3; i++){
				Thread.sleep(1000);
				s.subirBarra();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		s.dispose();
		v.run();
	}
	
	
}
