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
		menuBar.setBounds(0, 0, 770, 26);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("archivo");
		menuBar.add(mnArchivo);
		
		JTextArea codigo = new JTextArea();
		codigo.setBounds(12, 349, 750, 141);
		contentPane.add(codigo);
		
		List motodos = new List();
		motodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				codigo.setText(motodos.getSelectedItem());
			}
		});
		motodos.setBounds(292, 172, 320, 135);
		contentPane.add(motodos);
		
		List clases = new List();
		clases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				motodos.removeAll();
				motodos.add(clases.getSelectedItem());
			}
		});
		clases.setBounds(10, 172, 276, 135);
		contentPane.add(clases);
		
		List archivos = new List();
		archivos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clases.removeAll();
				clases.add(archivos.getSelectedItem());
			}
		});
		archivos.setBounds(10, 57, 600, 89);
		contentPane.add(archivos);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
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
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 35, 276, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblSeleccioneUnaClase = new JLabel("Seleccione una clase de la lista:");
		lblSeleccioneUnaClase.setForeground(Color.RED);
		lblSeleccioneUnaClase.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneUnaClase.setBounds(10, 150, 276, 16);
		contentPane.add(lblSeleccioneUnaClase);
		
		JLabel lblSeleccioneUnMetodo = new JLabel("Seleccione un metodo de la lista:");
		lblSeleccioneUnMetodo.setForeground(Color.RED);
		lblSeleccioneUnMetodo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneUnMetodo.setBounds(292, 152, 276, 16);
		contentPane.add(lblSeleccioneUnMetodo);
		
		JLabel lblCodigoDelMetodo = new JLabel("Codigo del metodo seleccionado:");
		lblCodigoDelMetodo.setForeground(Color.RED);
		lblCodigoDelMetodo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoDelMetodo.setBounds(10, 320, 276, 16);
		contentPane.add(lblCodigoDelMetodo);
		
	}
}
