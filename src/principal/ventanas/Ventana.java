package principal.ventanas;

import funciones.Funcion;
import funciones.Punto;
import interfaz.Colores;
import interfaz.dialogs.DialogSalir;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import plano.DibujoPlano;
import principal.files.FileChooserFx;
import principal.files.FileChooserImage;
import principal.paneles.PanelControl;
import principal.paneles.PanelOperacion;

public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pVentana;
	
	public PanelOperacion pOperacion;
	public PanelControl pControlCanvas;
	
	private JTextArea taPuntos;

	private final int BORDE = 0;

	private Image icon;
	private Image icon2;
	
	private DibujoPlano canvas;
	private DialogSalir dialog;
	private FileChooserImage fileChooser;
	private final String TITULO = "Funciones";
	
	private ContainerListener control;

	private FileChooserFx fileChooser2;
	
	private final int ancho, alto;

	private VentanaFunciones ventanaOperacion;

	private VentanaControl ventanaControl;

	private VentanaColor ventanaColores;

	
	
	/**
	 * Launch the application.
	 */
	public void run() {
		setVisible(true);
		ventanaOperacion.setVisible(true);
		ventanaControl.setVisible(true);
	}

	/**
	 * Create the frame.
	 * @param s 
	 */
	
	public Ventana(SplashScreen s) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	
		ancho = 501+16;
		alto = 501+38;
		
		//setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//setUndecorated(true);
		setPreferredSize(new Dimension(ancho, alto));
		setSize(ancho, alto);
		setLocationRelativeTo(null);
		Point p = getLocation();
		setLocation(p.x-ancho/4, p.y-50);
		setTitle("Plano");
		this.addComponentListener(new ComponentListener(){

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				canvas.dibujar();
			}

			@Override
			public void componentResized(ComponentEvent ev) {
				canvas.setSize(pVentana.getSize());
				canvas.dibujar();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				dialog.setVisible(true);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		iniciarIcono();
		s.subirBarra();
		iniciarControl();
		s.subirBarra();
		iniciarPanelPrincipal();
		s.subirBarra();
		iniciarCanvas();
		s.subirBarra();
		iniciarPanelOperacion();
		s.subirBarra();
		iniciarPanelControl();
		s.subirBarra();
		
		dialog = new DialogSalir(this);
		
		fileChooser = new FileChooserImage(this);
		fileChooser2 = new FileChooserFx(this);
		ventanaColores = new VentanaColor(this);
		
		s.subirBarra();
	}
	
	private void iniciarIcono(){
		icon = null;
		icon2 = null;
		try {
			icon = ImageIO.read(ClassLoader.class.getResource("/icon.png"));
			
			
			Image icon256 = ImageIO.read(ClassLoader.class.getResource("/icon256.png")), icon128 = ImageIO.read(ClassLoader.class.getResource("/icon128.png")), icon48 = ImageIO.read(ClassLoader.class.getResource("/icon48.png")), icon32 = ImageIO.read(ClassLoader.class.getResource("/icon32.png")), icon16 = ImageIO.read(ClassLoader.class.getResource("/icon16.png"));
			
			List<Image> icons = Arrays.asList(icon256, icon128, icon48, icon32, icon16);
			setIconImages(icons);
			
			icon2 = getIconImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void iniciarControl(){
		control = (new ContainerAdapter()
		{
			@Override
			public void componentAdded(ContainerEvent e) {
				e.getChild().addKeyListener(new KeyAdapter(){

					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ESCAPE) dialog.setVisible(true);
					}
				});
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowOpened(WindowEvent e) {
				canvas.dibujar();
				canvas.dibujar();
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				canvas.dibujar();
				canvas.dibujar();
			}
		});
		this.addContainerListener(control);
	}
	
	private void iniciarPanelPrincipal(){
		pVentana = new JPanel();
		pVentana.setLayout(null);
		pVentana.setBackground(Colores.GRIS);
		pVentana.setSize(getWidth(), getHeight());
		pVentana.setMinimumSize(getMinimumSize());
		setContentPane(pVentana);
	}
	
	private void iniciarCanvas(){
		canvas = new DibujoPlano(pVentana.getWidth(), pVentana.getHeight(), this);
		pVentana.add(canvas);
	}

	private void iniciarPanelOperacion(){
		pOperacion = new PanelOperacion(this, canvas, new Rectangle(0,0,324, 635));
		taPuntos = pOperacion.getTaPuntos();
		ventanaOperacion = new VentanaFunciones(this,pOperacion);
	}
	
	private void iniciarPanelControl(){
		pControlCanvas = new PanelControl(this, pOperacion, canvas, new Rectangle(0,0,501,134));
		ventanaControl = new VentanaControl(this,pControlCanvas);
	}

	public JTextArea getTaPuntos() {
		return taPuntos;
	}

	public JButton getBtnVerOrigen() {
		// TODO Auto-generated method stub
		return pControlCanvas.getBtnVerOrigen();
	}

	public VentanaFunciones getVentanaOperacion() {
		return ventanaOperacion;
	}

	public VentanaControl getVentanaControl() {
		return ventanaControl;
	}

	public Image getIcon() {
		return icon;
	}

	public Image getIcon2() {
		return icon2;
	}

	

	public boolean guardar(File file) {
		
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(file));
			canvas.guardar(os);
			os.close();
			System.out.println("GUARDADO");
		} catch (IOException e1) {
			
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cargar(File file) {
		int ancho, alto;
		Point escala, desplazamiento;
		ArrayList<Funcion> funciones;
		ArrayList<Punto> puntos;
		funciones = new ArrayList<Funcion>();
		puntos = new ArrayList<Punto>();
		Color colorFondo, colorEjes, colorPuntos, colorCursor, colorFunciones;
		
		ObjectInputStream is;
		try {
			is = new ObjectInputStream(new FileInputStream(file));
			
			try {
				ancho = (int) is.readObject();
				alto = (int) is.readObject();
				desplazamiento = (Point) is.readObject();
				escala = (Point) is.readObject();
				colorFondo  = (Color)is.readObject();
				colorEjes = (Color)is.readObject();;
				colorPuntos = (Color)is.readObject();;
				colorCursor = (Color)is.readObject();;
				colorFunciones = (Color)is.readObject();;
                                
				Object aux = null;
				do{
					System.out.println("leyendo funciones");
					try{
						aux = is.readObject();
						if(Funcion.class.isAssignableFrom(aux.getClass())){
							funciones.add(Funcion.class.cast(aux));
						}
					} catch(EOFException e){
						break;
					}
					
				} while(Funcion.class.isAssignableFrom(aux.getClass()));
				
				while(aux != null && Punto.class.isAssignableFrom(aux.getClass())){
					System.out.println("leyendo puntos");
					if(Punto.class.isAssignableFrom(aux.getClass())){
						puntos.add(Punto.class.cast(aux));
					}
					try{
						aux = is.readObject();
					} catch(EOFException e){
						break;
					}
					
				}
				
				setSize(ancho+16, alto+38);
				canvas.setDesplazamiento(desplazamiento.x, desplazamiento.y);
				canvas.setEscala(escala.x, escala.y);
				canvas.setColorFondo(colorFondo);
				canvas.setColorEjes(colorEjes);
				canvas.setColorPuntos(colorPuntos);
				canvas.setColorCursor(colorCursor);
				canvas.setColorFunciones(colorFunciones);
				canvas.eliminarFunciones();
				pOperacion.setFunciones(funciones);
				canvas.setPuntos(puntos);
				ventanaColores.cargarColores();
				System.out.println("f:"+funciones.size());
				System.out.println("p:"+puntos.size());
				
				is.close();
			} catch (IOException | ClassNotFoundException e) {
				is.close();
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}

	public VentanaColor getVentanaColores() {
		return ventanaColores;
	}

	public DibujoPlano getCanvas() {
		return canvas;
	}

	public DialogSalir getDialog() {
		return dialog;
	}

	public FileChooserImage getFileChooser() {
		return fileChooser;
	}

	public ContainerListener getControl() {
		return control;
	}

	public FileChooserFx getFileChooser2() {
		return fileChooser2;
	}

	public int getBorde() {
		return BORDE;
	}

	public String getTitulo() {
		return TITULO;
	}
	
	
	
}
