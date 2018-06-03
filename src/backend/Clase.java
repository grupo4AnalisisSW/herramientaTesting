package backend;

import java.util.ArrayList;

public class Clase {
	private ArrayList<Metodo> metodos;
	private String nombre;
	
	public Clase(String nombre) {
		this.setNombre(nombre);
	}

	public ArrayList<Metodo> getMetodos() {
		return metodos;
	}

	//Ver si esta es necesaria o no
	public void setMetodos(ArrayList<Metodo> metodos) {
		this.metodos = metodos;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
