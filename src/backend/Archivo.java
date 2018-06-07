package backend;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class Archivo {

	private int lineasTotales = -1;
	private double porcentajeComentarios = -1;
	private CompilationUnit arbol;
	private String nombre;
	
	public Archivo(File archivo) {
		//Abrir archivo, crear arbol, lo que haga falta
		this.nombre = archivo.getName();
		try {
			this.arbol = JavaParser.parse(archivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calcularLineasTotales();
		calcularPorcentajeComentarios();
	}

	public int getLineasTotales() {
		return lineasTotales;
	}

	private void calcularLineasTotales() {
		// TODO Auto-generated method stub
		
	}

	public double getPorcentajeComentarios() {
		return porcentajeComentarios;
	}

	private void calcularPorcentajeComentarios() {
		
	}

	public CompilationUnit getArbol() {
		return arbol;
	}

	public String getNombre() {
		return nombre;
	}
	
}
