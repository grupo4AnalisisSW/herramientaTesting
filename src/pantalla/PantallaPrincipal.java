package pantalla;
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
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;


public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;

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
		setTitle("Herramienta de Testing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 26);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		List motodos = new List();
		motodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				codigo.setText(motodos.getSelectedItem());
			}
		});
		motodos.setBounds(401, 213, 369, 135);
		contentPane.add(motodos);
		
		List clases = new List();
		clases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				motodos.removeAll();
				motodos.add(clases.getSelectedItem());
			}
		});
		clases.setBounds(10, 215, 362, 135);
		contentPane.add(clases);
		
		List archivos = new List();
		archivos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clases.removeAll();
				clases.add(archivos.getSelectedItem());
			}
		});
		archivos.setBounds(10, 57, 362, 130);
		contentPane.add(archivos);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir directorio");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel dlm = new DefaultListModel();
				JFileChooser js = new JFileChooser();
				js.setDialogTitle("Abrir directorio");
				js.setFileSelectionMode(js.DIRECTORIES_ONLY);
				int result = js.showOpenDialog(null);
				if (result==js.APPROVE_OPTION) {
					for (File f : js.getSelectedFile().listFiles()) {
						levantarArchivos(f,".java");
					}
				}
			}

			private void levantarArchivos(File f,String ext) {
				if (f.isDirectory()) {
					for (File arch: f.listFiles()) {
						levantarArchivos(arch,ext);
					}
				} else {
					if (f.getName().endsWith(ext)) {
						archivos.add(f.getPath());
					}						
				}				
			}

		});
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		
		JLabel lblNewLabel = new JLabel("Seleccione un archivo de la lista:");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 35, 276, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblSeleccioneUnaClase = new JLabel("Seleccione una clase de la lista:");
		lblSeleccioneUnaClase.setForeground(new Color(178, 34, 34));
		lblSeleccioneUnaClase.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneUnaClase.setBounds(10, 193, 362, 16);
		contentPane.add(lblSeleccioneUnaClase);
		
		JLabel lblSeleccioneUnMetodo = new JLabel("Seleccione un metodo de la lista:");
		lblSeleccioneUnMetodo.setForeground(new Color(178, 34, 34));
		lblSeleccioneUnMetodo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneUnMetodo.setBounds(401, 193, 369, 16);
		contentPane.add(lblSeleccioneUnMetodo);
		
		JLabel lblLineasDeCdigo = new JLabel("Lineas de c\u00F3digo del archivo:");
		lblLineasDeCdigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLineasDeCdigo.setForeground(new Color(178, 34, 34));
		lblLineasDeCdigo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLineasDeCdigo.setBounds(401, 57, 369, 16);
		contentPane.add(lblLineasDeCdigo);
		
		JLabel lblPorcentajeDeLineas = new JLabel("Porcentaje de lineas de c\u00F3digo del archivo comentadas:");
		lblPorcentajeDeLineas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcentajeDeLineas.setForeground(new Color(178, 34, 34));
		lblPorcentajeDeLineas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPorcentajeDeLineas.setBounds(401, 125, 369, 16);
		contentPane.add(lblPorcentajeDeLineas);
		
		JLabel lblFanIn = new JLabel("Fan In:");
		lblFanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanIn.setForeground(new Color(178, 34, 34));
		lblFanIn.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFanIn.setBounds(10, 374, 362, 16);
		contentPane.add(lblFanIn);
		
		JLabel lblLongitudDeHalstead = new JLabel("Longitud de Halstead:");
		lblLongitudDeHalstead.setHorizontalAlignment(SwingConstants.CENTER);
		lblLongitudDeHalstead.setForeground(new Color(178, 34, 34));
		lblLongitudDeHalstead.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLongitudDeHalstead.setBounds(401, 376, 369, 16);
		contentPane.add(lblLongitudDeHalstead);
		
		JLabel lblFanOut = new JLabel("Fan Out:");
		lblFanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanOut.setForeground(new Color(178, 34, 34));
		lblFanOut.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFanOut.setBounds(10, 438, 362, 16);
		contentPane.add(lblFanOut);
		
		JLabel lblVolumenDeHalstead = new JLabel("Volumen de Halstead:");
		lblVolumenDeHalstead.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolumenDeHalstead.setForeground(new Color(178, 34, 34));
		lblVolumenDeHalstead.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVolumenDeHalstead.setBounds(401, 438, 369, 16);
		contentPane.add(lblVolumenDeHalstead);
		
		JLabel lblNewLabel_1 = new JLabel("[Cant Lineas]");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(401, 84, 369, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("[Cant Lineas]%");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label.setBounds(401, 152, 369, 30);
		contentPane.add(label);
		
		JLabel lblfanIn = new JLabel("[FAN IN]");
		lblfanIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblfanIn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblfanIn.setBounds(10, 401, 362, 30);
		contentPane.add(lblfanIn);
		
		JLabel lblfanOut = new JLabel("[FAN OUT]");
		lblfanOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblfanOut.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblfanOut.setBounds(10, 465, 362, 30);
		contentPane.add(lblfanOut);
		
		JLabel lbllong = new JLabel("[LONG]");
		lbllong.setHorizontalAlignment(SwingConstants.CENTER);
		lbllong.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lbllong.setBounds(401, 401, 369, 30);
		contentPane.add(lbllong);
		
		JLabel lblvol = new JLabel("[VOL]");
		lblvol.setHorizontalAlignment(SwingConstants.CENTER);
		lblvol.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblvol.setBounds(401, 465, 369, 30);
		contentPane.add(lblvol);
		
	}
}
