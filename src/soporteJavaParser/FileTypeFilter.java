package soporteJavaParser;
import java.io.File;
import javax.swing.filechooser.*;

public class FileTypeFilter extends FileFilter {
	
	private final String extension;
	private final String descripcion;
	
	public FileTypeFilter(String extencion,String descripcion) {
		this.descripcion = descripcion;
		this.extension = extencion;
	}
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		return f.getName().endsWith(extension);
	}

	@Override
	public String getDescription() {
		return descripcion + String.format(" (*%s)", extension);
	}

}
