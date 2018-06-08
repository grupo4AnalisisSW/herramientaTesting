package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Ash Nazg durbatul�k, ash Nazg gimbatul, ash Nazg thrakatul�k agh burzum-ishi krimpatul.
 * @author Leyluchy
 *
 */
public class Controlador {
	private HashMap<String, Archivo> archivos;
	private HashMap<String, Clase> clases;
	
	public void procesar(File directorio) throws FileNotFoundException{
		archivos = new HashMap<String, Archivo>();
		clases = new HashMap<String, Clase>();
		
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

	private void calcularFans() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Genera la lista de Clases a partir de los Archivos.
	 * Cada clase genera su lista de Metodos.
	 */
	private void armarClasesYMetodos() {
		for(Archivo arch : archivos.values()) {
			new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    Clase clase = new Clase(n);
                    clases.put(clase.getNombre(), clase);
                }
            }.visit(arch.getArbol(), null);
		}
	}

	/**
	 * Crea la lista de Archivos levantando todos los .java de un directorio
	 * @param directorio
	 * @throws FileNotFoundException 
	 */
	private void abrirYParsearArchivos(File directorio) throws FileNotFoundException {
		levantarArchivos(directorio, ".java");
	}

	/**
	 * Funcion recursiva para recorrer directorios buscando .java
	 * Por cada .java agrega un Archivo a la lista
	 * @param f: archivo o directorio tipo File
	 * @param ext: extension de los archivos que queremos levantar
	 * @throws FileNotFoundException 
	 */
	private void levantarArchivos(File f,String ext) throws FileNotFoundException {
		if (f.isDirectory()) 
			for (File arch: f.listFiles()) 
				levantarArchivos(arch,ext);
		else
			if (f.getName().endsWith(ext)) {
				Archivo arch = new Archivo(f);
				archivos.put(arch.getNombre(), arch);
			}
	}

	/*
	public HashMap<String, Archivo> getArchivos() {
		return archivos;
	}

	public HashMap<String, Clase> getClases() {
		return clases;
	}
	*/
	
	public List<String> traerMetodosDeClase(String nombreClase) {
		ArrayList<String> listaMetodos = new ArrayList<String>();
		for(Metodo met : clases.get(nombreClase).getMetodos())
			listaMetodos.add(met.getNombre());
		return listaMetodos;
	}
	
	public List<String> traerArchivos(){
		ArrayList<String> listaArchivos = new ArrayList<String>();
		for(Archivo arch : archivos.values())
			listaArchivos.add(arch.getNombre());
		return listaArchivos;
	}

	public List<String> traerClases() {
		ArrayList<String> listaClases = new ArrayList<String>();
		for(Clase clase : clases.values())
			listaClases.add(clase.getNombre());
		return listaClases;
	}

	public int traerLineasArch(String nombreArchivo) {
		return archivos.get(nombreArchivo).getLineasTotales();
	}

	public double traerPorcentajeComent(String nombreArchivo) {
		return archivos.get(nombreArchivo).getPorcentajeComentarios();
	}
	
}
