package backend;

import com.github.javaparser.ast.CompilationUnit;

public class Archivo {

	private int lineasTotales = -1;
	private double porcentajeComentarios = -1;
	private CompilationUnit arbol;
	private String path;
	
	public Archivo(String path) {
		//Abrir archivo, crear arbol, lo que haga falta
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

	public String getPath() {
		return path;
	}
	
}
