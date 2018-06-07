package backend;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import soporteJavaParser.NodeIterator;

public class Metodo {

	private String nombre;
	private int fanIn = -1;
	private int fanOut = -1;
	private int longitud = -1;
	private int volumen = -1;
	private int complejidadCiclomatica = -1;
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
		if(complejidadCiclomatica > 0)
			return;
		complejidadCiclomatica = 1;
		
		new VoidVisitorAdapter<Object>() {
			//Contar ifs
            @Override
            public void visit(IfStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
            }
            
            //Contar cases, que son Entries del Switch con Labels (sin Label es Default, que no cuenta)
            @Override
            public void visit(SwitchEntryStmt n, Object arg) {
                super.visit(n, arg);
                if(n.getLabel().isPresent())
                	complejidadCiclomatica++;
            }
            
            //Contar catchs. Decimos que el par try-catch cuenta como 1
            @Override
            public void visit(CatchClause n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
            }
            
            //Contar throws. Decimos que cada throw cuenta como 1
            @Override
            public void visit(ThrowStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
            }
            
            //Contar whiles. No incluye do-while.
            @Override
            public void visit(WhileStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
            }
            
            //Contar do-while
            @Override
            public void visit(DoStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
            }
            
            //Contar fors
            @Override
            public void visit(ForStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
            }
            
            //Contar foreachs
            @Override
            public void visit(ForeachStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
            }
        }.visit(nodo, null);
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
