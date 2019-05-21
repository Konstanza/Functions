package principal.files;

import interfaz.dialogs.DialogOk;

import java.io.File;

import javax.swing.JFileChooser;

import principal.ventanas.Ventana;

public class FileChooserImage extends JFileChooser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Ventana ventana;
	
	public FileChooserImage(Ventana ventana){
		this.ventana = ventana;
		
		setDialogTitle("Guardar imagen");
		setDialogType(JFileChooser.SAVE_DIALOG);
		
		FiltroBasico ff = new FiltroBasico("PNG (*.png)", "png");
		FiltroBasico ff2 = new FiltroBasico("JPG (*.jpg)", "jpg");
		FiltroBasico ff3 = new FiltroBasico("BMP (*.bmp)", "bmp");
		
		removeChoosableFileFilter(getFileFilter());
		addChoosableFileFilter(ff);
		addChoosableFileFilter(ff2);
		addChoosableFileFilter(ff3);
		setFileFilter(ff);
	}
	
	public void guardar(){
		int a = showSaveDialog(ventana);
		
		if(a == JFileChooser.APPROVE_OPTION){
			
			File file = getSelectedFile();
			
			if(file.getName().contains(".")){
				if(ventana.getCanvas().guardar(file)) mostrarMensaje("Imagen guardada");
				else mostrarMensaje("Error al guardar imagen");
			}
			else {
				if(ventana.getCanvas().guardar(file, ((FiltroBasico)getFileFilter()).TIPO)) mostrarMensaje("Imagen guardada");
				else mostrarMensaje("Error al guardar imagen");
			}
		}
	}
	
	private void mostrarMensaje(String string) {
		DialogOk d = new DialogOk(ventana,string);
		d.setVisible(true);
	}
}
