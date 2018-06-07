package backend;

import java.util.ArrayList;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class Clase {
	private ArrayList<Metodo> metodos;
	private String nombre;
	private ClassOrInterfaceDeclaration nodo;
	
	
	public Clase(ClassOrInterfaceDeclaration nodo) {
		this.nodo = nodo;
		this.setNombre(nodo.getNameAsString());
		for(MethodDeclaration metodo : nodo.getMethods())
			metodos.add(new Metodo(metodo));
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
