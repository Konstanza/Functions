package principal.files;

import interfaz.dialogs.DialogOk;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import principal.ventanas.Ventana;

public class FileChooserFx extends JFileChooser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Ventana ventana;
	
	public FileChooserFx(Ventana ventana){
		this.ventana = ventana;
		
		setDialogTitle("Archivo");
		
		FiltroBasico ff = new FiltroBasico("DAT (*.dat)", "dat");
		
		removeChoosableFileFilter(getFileFilter());
		addChoosableFileFilter(ff);
		setFileFilter(ff);
		
	}
	
	public void guardar(){
		int a = showSaveDialog(ventana);
		
		if(a == JFileChooser.APPROVE_OPTION){
			
			File file = getSelectedFile();
			
			if(file.getName().endsWith("."+((FiltroBasico)getFileFilter()).TIPO)){
				if(file.exists()) {
					if(!ventana.guardar(file)) mostrarMensaje("Error al guardar archivo");
					else mostrarMensaje("Archivo guardado");
					}
				else {
					try {
						file.createNewFile();
						if(!ventana.guardar(file)) mostrarMensaje("Error al guardar archivo");
						else mostrarMensaje("Archivo guardado");
					} catch (IOException e) {
						mostrarMensaje("Error al guardar archivo");
						e.printStackTrace();
					}
				}
			}
			else {
				file = new File(file.getPath()+"."+((FiltroBasico)getFileFilter()).TIPO);
				if(!file.exists()) {
					try {
						file.createNewFile();
						if(!ventana.guardar(file)) mostrarMensaje("Error al guardar archivo");
						else mostrarMensaje("Archivo guardado");
					} catch (IOException e) {
						mostrarMensaje("Error al guardar archivo");
						e.printStackTrace();
					}
				}
				else {
					if(!ventana.guardar(file)) mostrarMensaje("Error al guardar archivo");
					else mostrarMensaje("Archivo guardado");
				}
			}
		}
	}

	public void cargar() {
		int a = showOpenDialog(ventana);
		
		if(a == JFileChooser.APPROVE_OPTION){
			
			File file = getSelectedFile();
			
			if(!file.exists() || !ventana.cargar(file)) mostrarMensaje("Error al cargar archivo");
			else mostrarMensaje("Archivo cargado");
		}
		
	}

	private void mostrarMensaje(String string) {
		DialogOk d = new DialogOk(ventana,string);
		d.setVisible(true);
	}
}
