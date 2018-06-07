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
		this.metodos = new ArrayList<Metodo>();
		for(MethodDeclaration metodo : nodo.getMethods())
			metodos.add(new Metodo(metodo));
	}

	public ArrayList<Metodo> getMetodos() {
		return metodos;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
