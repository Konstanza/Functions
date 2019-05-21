package principal.files;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltroBasico extends FileFilter{

	public final String DESCRIPTION;
	public final String TIPO;
	
	public FiltroBasico(String description, String tipo){
		this.DESCRIPTION = description;
		this.TIPO = tipo;
	}
	
	@Override
	public boolean accept(File file) {
		if(file.isDirectory()) return true;
		
		String tipo = file.getName();
		if(tipo.lastIndexOf("."+TIPO) < tipo.length()-TIPO.length()-1) return false;
		
		return true;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
