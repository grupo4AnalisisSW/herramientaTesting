package backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Metodo {
	
	private static final String REGEX_METODO = "([a-zA-Z_][\\w\\<\\>]*)";

	private String nombre;
	private static String cuerpo;
	private int fanIn = -1;
	private int fanOut = -1;
	private int longitud = -1;
	private int volumen = -1;
	private int complejidadCiclomatica = -1;
	private MethodDeclaration nodo;
	
	public Metodo(MethodDeclaration nodo) {
		this.nodo = nodo;
		this.setNombre(nodo.getNameAsString());
		
		String declaracion = nodo.getDeclarationAsString().replaceAll("/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/", "").replaceAll("//.*[\n\r]", "").replaceAll("^\\s*\n", "");
        this.setCuerpo("");
        String[] body = declaracion.split("\\n");
        for (int i = 0; i < body.length; i++) {
            if (i != 0 && i != body.length - 1)
                this.setCuerpo(getCuerpo() + body[i]);
        }
	}
	
	/**
	 * Calcula complejidad ciclomatica, longitud y volumen
	 * Fan In y Fan Out deben calcularse a parte
	 */
	public void procesar() {
		calcularComplejidadCiclomatica();
		calcularLongitud();
		calcularVolumen();
		setFanOut(calcularFanOut());
	}
	
	private void calcularVolumen() {
		// TODO Auto-generated method stub
		
	}

	private void calcularLongitud() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Calcula los fan out de cada método usando la regex de métodos
	 * para buscar en el código.
	 * */
	public static int calcularFanOut() {
        int contador = 0;
        String regex = "[\\s.]?" + "(" + REGEX_METODO + ")" + "\\(";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(getCuerpo());
        while(mat.find())
            contador++;
        return contador;
    }

	private void calcularComplejidadCiclomatica() {
		if(complejidadCiclomatica > 0)
			return;
		complejidadCiclomatica = 1;
		
		new VoidVisitorAdapter<Object>() {
			//Contar ifs, AND y OR
            @Override
            public void visit(IfStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
                if(n.getCondition() instanceof BinaryExpr) 
                	contarAndYOr((BinaryExpr)n.getCondition());
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
            
            //Contar whiles, AND y OR. No incluye do-while.
            @Override
            public void visit(WhileStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
                if(n.getCondition() instanceof BinaryExpr) 
                	contarAndYOr((BinaryExpr)n.getCondition());
            }
            
            //Contar do-while, AND y OR
            @Override
            public void visit(DoStmt n, Object arg) {
                super.visit(n, arg);
                complejidadCiclomatica++;
                if(n.getCondition() instanceof BinaryExpr) 
                	contarAndYOr((BinaryExpr)n.getCondition());
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
	
	/**
	 * Cuenta los AND y OR en una expresion
	 * @param cond
	 */
	private void contarAndYOr(BinaryExpr cond) {
		if(cond.getOperator().equals(BinaryExpr.Operator.AND) || cond.getOperator().equals(BinaryExpr.Operator.OR))
			complejidadCiclomatica++;
		if(cond.getRight() instanceof BinaryExpr)
			contarAndYOr((BinaryExpr)cond.getRight());
		if(cond.getLeft() instanceof BinaryExpr)
			contarAndYOr((BinaryExpr)cond.getLeft());
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

	public static String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		Metodo.cuerpo = cuerpo;
	}
}
