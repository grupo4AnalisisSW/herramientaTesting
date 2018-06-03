package backend;

/**
 * Ash Nazg durbatulûk, ash Nazg gimbatul, ash Nazg thrakatulûk agh burzum-ishi krimpatul.
 * @author Leyluchy
 *
 */
public class Controlador {
	private Archivo[] archivos;
	private Clase[] clases;
	
	public void procesar(String directorio){
		/*El orden de procesamiento tiene que ser este
		 * Cada parte puede hacerlo esta misma clase o delegarselo a otras
		 */
		abrirYParsearArchivos();
		armarClasesYMetodos();
		calcularFans();
		calcularComplejidadesCiclomaticas();
		calcularLongitudes();
		calcularVolumenes();
	}

	private void calcularVolumenes() {
		// TODO Auto-generated method stub
		
	}

	private void calcularLongitudes() {
		// TODO Auto-generated method stub
		
	}

	private void calcularComplejidadesCiclomaticas() {
		// TODO Auto-generated method stub
		
	}

	private void calcularFans() {
		// TODO Auto-generated method stub
		
	}

	private void armarClasesYMetodos() {
		// TODO Auto-generated method stub
		
	}

	private void abrirYParsearArchivos() {
		// TODO Auto-generated method stub
		
	}
	
	
}
