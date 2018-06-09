package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Ash Nazg durbatul�k, ash Nazg gimbatul, ash Nazg thrakatul�k agh burzum-ishi krimpatul.
 * @author Leyluchy
 *
 */
public class Controlador {
	
	private static final  String REGEX_METODO = "([a-zA-Z_][\\w]*)";
	
	private HashMap<String, Archivo> archivos;
	private HashMap<String, Clase> clases;

	public Controlador() {
		archivos = new HashMap<String, Archivo>();
		clases = new HashMap<String, Clase>();
	}
	
	public void procesar(File directorio) {
		//Procesamiento
		abrirYParsearArchivos(directorio);
		armarClasesYMetodos();
		calcularFans();
	}

	/**
	 * Calcula los fan in de todos los m�todos de cada clase
	 * usando su lista de m�todos.
	 * */
	private void calcularFans() {
		for (Clase clase : clases.values()) {
			for (Metodo metodo : clase.getMetodos().values()) {
				metodo.setFanIn(calcularFanInLlamadoSinPunto(archivos.values(), metodo)
						+ calcularFanInLlamadoConPunto(archivos.values(), metodo));
				metodo.setFanOut(calcularFanOut(metodo,clases.values()));
			}
		}
	}
	
	/**
	 * Calcula los fan in de un metodo si es llamado sin punto adelante 
	 * */
	private static int calcularFanInLlamadoSinPunto(Collection<Archivo> archivos, Metodo metodo) {
        int contador = 0;
        if (metodo.getNombre().equals("main"))
            return 0;
        String regex = "\\s" + metodo.getNombre() + "\\(";
        Pattern pat = Pattern.compile(regex);
        for (Archivo archivo : archivos) {
            Matcher mat = pat.matcher(archivo.getCodigo());
            while(mat.find())
                contador++;
        }
        return (contador==0)? contador:contador - 1;
    }
	
	/**
	 * Calcula los fan in de un metodo si es llamado con punto adelante 
	 * */
	private static int calcularFanInLlamadoConPunto(Collection<Archivo> archivos, Metodo metodo) {
        int contador = 0;
        if (metodo.getNombre().equals("main"))
            return 0;
        String regex = "[a-zA-Z]+[\\w]*[_]*." + metodo.getNombre() + "\\(";
        Pattern pat = Pattern.compile(regex);
        for (Archivo archivo : archivos) {
            Matcher mat = pat.matcher(archivo.getCodigo());
            while(mat.find())
                contador++;
        }
        return (contador==0)? contador:contador - 1;
    }
	
	/**
	 * Calcula los fan out de cada m�todo si los llama sin un punto adelante
	 * */

	public static int calcularFanOut(Metodo metodo, Collection<Clase> clases) {
	int contador = 0;
	String regex = "[\\s.]?" + "(" + REGEX_METODO + ")" + "\\(";
  
	Pattern pat = Pattern.compile(regex);
	Matcher mat = pat.matcher(metodo.getCuerpo());
	while(mat.find()) {
		for (Clase clase : clases) {
			for (Metodo metod : clase.getMetodos().values()) {
				if(mat.toString().contains(metod.getNombre()))
					contador++;
			}
		}
	}
	return contador;
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
	private void abrirYParsearArchivos(File directorio) {
		levantarArchivos(directorio, ".java");
	}

	/**
	 * Funcion recursiva para recorrer directorios buscando .java
	 * Por cada .java agrega un Archivo a la lista
	 * @param f: archivo o directorio tipo File
	 * @param ext: extension de los archivos que queremos levantar
	 * @throws FileNotFoundException 
	 */
	private void levantarArchivos(File f,String ext) {
		if (f.isDirectory()) 
			for (File arch: f.listFiles()) 
				levantarArchivos(arch,ext);
		else
			if (f.getName().endsWith(ext)) {
				Archivo arch;
				try {
					arch = new Archivo(f);
					archivos.put(arch.getNombre(), arch);
				} catch (ParseProblemException | IOException e) {
					if(e instanceof FileNotFoundException)
						System.out.print("No se encontr�");
					else if(e instanceof IOException)
						System.out.print("No se pudo abrir");
					else if(e instanceof ParseProblemException)
						System.out.print("No se pudo parsear");
					System.out.println(" el archivo " + f.getName());
				}
			}
	}
	
	public List<String> traerMetodosDeClase(String nombreClase) {
		ArrayList<String> listaMetodos = new ArrayList<String>();
		for(Metodo met : clases.get(nombreClase).getMetodos().values())
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
		return archivos.
				get(nombreArchivo).
				getLineasTotales();
	}

	public double traerPorcentajeComent(String nombreArchivo) {
		return archivos.get(nombreArchivo).getPorcentajeComentarios();
	}

	public int traerFanIn(String nombreClase, String nombreMetodo) {
		return clases.get(nombreClase).getMetodo(nombreMetodo).getFanIn();
	}
	
	public int traerFanOut(String nombreClase, String nombreMetodo) {
		return clases.get(nombreClase).getMetodo(nombreMetodo).getFanOut();
	}
	
	public int traerLongitud(String nombreClase, String nombreMetodo) {
		return clases.get(nombreClase).getMetodo(nombreMetodo).getLongitud();
	}
	
	public double traerVolumen(String nombreClase, String nombreMetodo) {
		return clases.get(nombreClase).getMetodo(nombreMetodo).getVolumen();
	}
	
	public void procesarMetodo(String nombreClase, String nombreMetodo) {
		clases.
		get(
				nombreClase).
		getMetodo(
				nombreMetodo).
		procesar();
	}

	public int traerVg(String nombreClase, String nombreMetodo) {
		return clases.get(nombreClase).getMetodo(nombreMetodo).getComplejidadCiclomatica();
	}

	public String traerCod(String nombreClase, String nombreMetodo) {
		return clases.get(nombreClase).getMetodo(nombreMetodo).getCod();
	}
}
