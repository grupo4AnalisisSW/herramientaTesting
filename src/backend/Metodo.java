package backend;

public class Metodo {

	private String nombre;
	private int fanIn;
	private int fanOut;
	private int longitud;
	private int volumen;
	private int complejidadCiclomatica;
	
	public Metodo(String nombre) {
		this.setNombre(nombre);
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
