package backend;

import java.io.File;
import java.util.ArrayList;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

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
		
		//Estas podrian ser procesarMetodos()
		calcularFans();
		//Estas 3 podrian estar dentro de Metodo
		//calcularComplejidadesCiclomaticas();
		//calcularLongitudes();
		//calcularVolumenes();
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

	/**
	 * Genera la lista de Clases a partir de los Archivos.
	 * Cada clase genera su lista de Metodos.
	 */
	private void armarClasesYMetodos() {
		for(Archivo arch : archivos) {
			new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    clases.add(new Clase(n));
                }
            }.visit(arch.getArbol(), null);
		}
	}

	/**
	 * Crea la lista de Archivos levantando todos los .java de un directorio
	 * @param directorio
	 */
	private void abrirYParsearArchivos(File directorio) {
		levantarArchivos(directorio, ".java");
	}

	/**
	 * Funcion recursiva para recorrer directorios buscando .java
	 * Por cada .java agrega un Archivo a la lista
	 * @param f: archivo o directorio tipo File
	 * @param ext: extension de los archivos que queremos levantar
	 */
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
