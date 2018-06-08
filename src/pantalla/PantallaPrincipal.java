package pantalla;

import backend.Archivo;
import backend.Controlador;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.TextField;
import java.awt.List;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;


public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private Controlador elControlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		elControlador = new Controlador();
		
		setTitle("Herramienta de Testing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 26);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		//Labels con resultados
		
		JLabel locsLabel = new JLabel("[Cant Lineas]");
		locsLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		locsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		locsLabel.setBounds(397, 104, 369, 30);
		contentPane.add(locsLabel);
		
		JLabel porcentajeComentLabel = new JLabel("[Cant Lineas]%");
		porcentajeComentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		porcentajeComentLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		porcentajeComentLabel.setBounds(397, 172, 369, 30);
		contentPane.add(porcentajeComentLabel);
		
		JLabel lblfanIn = new JLabel("[FAN IN]");
		lblfanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblfanIn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblfanIn.setBounds(10, 440, 362, 30);
		contentPane.add(lblfanIn);
		
		JLabel lblfanOut = new JLabel("[FAN OUT]");
		lblfanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblfanOut.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblfanOut.setBounds(10, 504, 362, 30);
		contentPane.add(lblfanOut);
		
		JLabel lbllong = new JLabel("[LONG]");
		lbllong.setHorizontalAlignment(SwingConstants.CENTER);
		lbllong.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lbllong.setBounds(401, 440, 369, 30);
		contentPane.add(lbllong);
		
		JLabel lblvol = new JLabel("[VOL]");
		lblvol.setHorizontalAlignment(SwingConstants.CENTER);
		lblvol.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblvol.setBounds(401, 504, 369, 30);
		contentPane.add(lblvol);
		
		//Listas
		List listMetodos = new List();
		List listClases = new List();
		List listArchivos = new List();
		
		//Lista de metodos
		listMetodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Que hacer cuando clickean un metodo
				String clase = listClases.getSelectedItem();
				String metodo = listMetodos.getSelectedItem();
				elControlador.procesarMetodo(clase, metodo);
				
				lblfanIn.setText(Integer.toString(elControlador.traerFanIn(clase, metodo)));
				lblfanOut.setText(Integer.toString(elControlador.traerFanOut(clase, metodo)));
				lbllong.setText(Integer.toString(elControlador.traerLongitud(clase, metodo)));
				lblvol.setText(Integer.toString(elControlador.traerVolumen(clase, metodo)));
			}
		});
		listMetodos.setBounds(401, 265, 369, 135);
		contentPane.add(listMetodos);
		
		//Lista de clases
		listClases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Que hacer cuando clickean una clase
				//Listar los metodos de esa clase
				listMetodos.removeAll();
				for(String nombreMetodo : elControlador.traerMetodosDeClase(listClases.getSelectedItem()))
					listMetodos.add(nombreMetodo);
			}
		});
		listClases.setBounds(10, 267, 362, 135);
		contentPane.add(listClases);
		
		//Lista de archivos
		listArchivos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Que hacer cuando clickean un archivo
				//Mostrar datos de ese archivo
				
				locsLabel.setText( Integer.toString(
						elControlador.traerLineasArch(
								listArchivos.getSelectedItem() )
						));
				/* Descomentar esta linea cuando esten implementados los % de comentarios
				 * 
				 * 
				porcentajeComentLabel.setText(Double.toString(
						elControlador.traerPorcentajeComent(
							listArchivos.getSelectedItem()
						) * 100) + "%" );
				 */
			}
		});
		listArchivos.setBounds(6, 77, 362, 130);
		contentPane.add(listArchivos);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir directorio");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel dlm = new DefaultListModel();
				JFileChooser js = new JFileChooser();
				js.setDialogTitle("Abrir directorio");
				js.setFileSelectionMode(js.DIRECTORIES_ONLY);
				int result = js.showOpenDialog(null);
				if (result==js.APPROVE_OPTION) {
					try {
						elControlador.procesar(js.getSelectedFile());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();//Poner algo acá
					}
					locsLabel.setText("[Cant Lineas]");
					porcentajeComentLabel.setText("[Cant Lineas]%");
					lblfanIn.setText("[FAN IN]");
					lblfanOut.setText("[FAN OUT]");
					lbllong.setText("[LONG]");
					lblvol.setText("[LONG]");
					listArchivos.removeAll();
					listClases.removeAll();
					listMetodos.removeAll();
					for(String arch : elControlador.traerArchivos())
						listArchivos.add(arch);
					for(String clase : elControlador.traerClases())
						listClases.add(clase);
				}
			}

		});
		
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		
		JLabel lblNewLabel = new JLabel("Seleccione un archivo de la lista:");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(6, 55, 276, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblSeleccioneUnaClase = new JLabel("Seleccione una clase de la lista:");
		lblSeleccioneUnaClase.setForeground(new Color(178, 34, 34));
		lblSeleccioneUnaClase.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneUnaClase.setBounds(10, 245, 362, 16);
		contentPane.add(lblSeleccioneUnaClase);
		
		JLabel lblSeleccioneUnMetodo = new JLabel("Seleccione un metodo de la lista:");
		lblSeleccioneUnMetodo.setForeground(new Color(178, 34, 34));
		lblSeleccioneUnMetodo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneUnMetodo.setBounds(401, 245, 369, 16);
		contentPane.add(lblSeleccioneUnMetodo);
		
		JLabel lblLineasDeCdigo = new JLabel("Lineas de c\u00F3digo del archivo:");
		lblLineasDeCdigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLineasDeCdigo.setForeground(new Color(178, 34, 34));
		lblLineasDeCdigo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLineasDeCdigo.setBounds(397, 77, 369, 16);
		contentPane.add(lblLineasDeCdigo);
		
		JLabel lblPorcentajeDeLineas = new JLabel("Porcentaje de lineas de c\u00F3digo del archivo comentadas:");
		lblPorcentajeDeLineas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcentajeDeLineas.setForeground(new Color(178, 34, 34));
		lblPorcentajeDeLineas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPorcentajeDeLineas.setBounds(397, 145, 369, 16);
		contentPane.add(lblPorcentajeDeLineas);
		
		JLabel lblFanIn = new JLabel("Fan In:");
		lblFanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanIn.setForeground(new Color(178, 34, 34));
		lblFanIn.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFanIn.setBounds(10, 413, 362, 16);
		contentPane.add(lblFanIn);
		
		JLabel lblLongitudDeHalstead = new JLabel("Longitud de Halstead:");
		lblLongitudDeHalstead.setHorizontalAlignment(SwingConstants.CENTER);
		lblLongitudDeHalstead.setForeground(new Color(178, 34, 34));
		lblLongitudDeHalstead.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLongitudDeHalstead.setBounds(401, 415, 369, 16);
		contentPane.add(lblLongitudDeHalstead);
		
		JLabel lblFanOut = new JLabel("Fan Out:");
		lblFanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanOut.setForeground(new Color(178, 34, 34));
		lblFanOut.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFanOut.setBounds(10, 477, 362, 16);
		contentPane.add(lblFanOut);
		
		JLabel lblVolumenDeHalstead = new JLabel("Volumen de Halstead:");
		lblVolumenDeHalstead.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolumenDeHalstead.setForeground(new Color(178, 34, 34));
		lblVolumenDeHalstead.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVolumenDeHalstead.setBounds(401, 477, 369, 16);
		contentPane.add(lblVolumenDeHalstead);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 213, 764, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("Archivos .java");
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_2.setBounds(10, 33, 760, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblClasesYMetodos = new JLabel("Clases y metodos");
		lblClasesYMetodos.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasesYMetodos.setForeground(new Color(0, 0, 128));
		lblClasesYMetodos.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblClasesYMetodos.setBounds(10, 221, 760, 26);
		contentPane.add(lblClasesYMetodos);
		
	}
}
