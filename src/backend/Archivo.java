package backend;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
import java.util.Scanner;

public class Archivo {

	private int lineasTotales = -1;
	private double porcentajeComentarios = -1;
	private CompilationUnit arbol;
	private String nombre;
	private String codigo;
	private ArrayList<String> lineas;
	private int cantComentarios;
	
	
	public Archivo(File archivo) throws FileNotFoundException {
		//Abrir archivo, crear arbol, lo que haga falta
		this.nombre = archivo.getName();
		try {
			this.arbol = JavaParser.parse(archivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//IDK
		}
		lineas=new ArrayList<String>();
		calcularLineas(archivo);
		calcularLineasTotales();
		this.cantComentarios=0;
		contarComentariosDobleBarra();
		contarComentariosMultiLinea();
		calcularPorcentajeComentarios();
	}

	private void calcularLineas(File archivo) throws FileNotFoundException {
		Scanner scanner =new Scanner(archivo);
		while(scanner.hasNextLine()) {
			this.lineas.add(scanner.nextLine());
		}
		int i=0;
		while(i<this.lineas.size()) {
			if(this.lineas.get(i).equals("")) {
				this.lineas.remove(i);
			}
			else
				i++;
		}
		scanner.close();
	}
	
	public int getLineasTotales() {
		return lineasTotales;
	}

	private void calcularLineasTotales() {
		this.lineasTotales=this.lineas.size();
	}

	public double getPorcentajeComentarios() {
		return porcentajeComentarios;
	}

	/**
	 * 
	 * Este Ya funka, falta el otro, por ahí cuando haga el otro tenga que ajustar este
	 */
	private void contarComentariosDobleBarra() { //Revisa por linea de comentario, si aparece este tipo de comentario
		for(String linea:this.lineas) {
			linea=linea.replaceAll("\"(?:[^\"\\\\]|\\\\.)*\"", "plainText");
			if(linea.contains("\\\\"))
				this.cantComentarios++;
		}
		
	}
	
	private void contarComentariosMultiLinea() {
		
		//while()
	}
	
	private void calcularPorcentajeComentarios() {
		
	}

	public CompilationUnit getArbol() {
		return arbol;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
