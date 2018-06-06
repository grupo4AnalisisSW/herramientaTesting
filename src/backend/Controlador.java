package backend;

import java.io.File;
import java.util.ArrayList;

/**
 * Ash Nazg durbatulūk, ash Nazg gimbatul, ash Nazg thrakatulūk agh burzum-ishi krimpatul.
 * @author Leyluchy
 *
 */
public class Controlador {
	private ArrayList<Archivo> archivos;
	private ArrayList<Clase> clases;
	
	public void procesar(File directorio){
		archivos = new ArrayList<Archivo>();
		clases = new ArrayList<Clase>();
		
		/*El orden de procesamiento tiene que ser este
		 * Cada parte puede hacerlo esta misma clase o delegarselo a otras
		 */
		abrirYParsearArchivos(directorio);
		armarClasesYMetodos();
		calcularFans();
		calcularComplejidadesCiclomaticas();
		calcularLongitudes();
		calcularVolumenes();
	}

	private void calcularVolumenes() {
		// TODO Auto-generated method stub
		
	}

	private void calcularLongitudes() {
		// TODO Auto-generated method stub
		
	}

	private void calcularComplejidadesCiclomaticas() {
		// TODO Auto-generated method stub
		
	}

	private void calcularFans() {
		// TODO Auto-generated method stub
		
	}

	private void armarClasesYMetodos() {
		// TODO Auto-generated method stub
		
	}

	private void abrirYParsearArchivos(File directorio) {
		levantarArchivos(directorio, ".java");
	}
	


	private void levantarArchivos(File f,String ext) {
		if (f.isDirectory()) 
			for (File arch: f.listFiles()) 
				levantarArchivos(arch,ext);
		else
			if (f.getName().endsWith(ext))
				archivos.add(new Archivo(f));								
	}

	public ArrayList<Archivo> getArchivos() {
		return archivos;
	}

	public ArrayList<Clase> getClases() {
		return clases;
	}
	
}
