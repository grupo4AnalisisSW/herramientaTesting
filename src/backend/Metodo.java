package backend;

import com.github.javaparser.ast.body.MethodDeclaration;

public class Metodo {

	private String nombre;
	private int fanIn;
	private int fanOut;
	private int longitud;
	private int volumen;
	private int complejidadCiclomatica;
	private MethodDeclaration nodo;
	
	public Metodo(MethodDeclaration nodo) {
		this.nodo = nodo;
		this.setNombre(nodo.getNameAsString());
	}
	
	/**
	 * Calcula complejidad ciclomatica, longitud y volumen
	 * Fan In y Fan Out deben calcularse a parte
	 */
	public void procesar() {
		calcularComplejidadCiclomatica();
		calcularLongitud();
		calcularVolumen();
	}
	
	private void calcularVolumen() {
		// TODO Auto-generated method stub
		
	}

	private void calcularLongitud() {
		// TODO Auto-generated method stub
		
	}

	private void calcularComplejidadCiclomatica() {
	}

	public String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getFanIn() {
		return fanIn;
	}
	public void setFanIn(int fanIn) {
		this.fanIn = fanIn;
	}
	public int getFanOut() {
		return fanOut;
	}
	public void setFanOut(int fanOut) {
		this.fanOut = fanOut;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public int getComplejidadCiclomatica() {
		return complejidadCiclomatica;
	}
	public void setComplejidadCiclomatica(int complejidadCiclomatica) {
		this.complejidadCiclomatica = complejidadCiclomatica;
	}
}
