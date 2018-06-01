import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Pprin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pprin frame = new Pprin();
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
	public Pprin() {
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
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser js = new JFileChooser(new File("c:\\"));
				js.setDialogTitle("Abrir directorio");
				js.setFileFilter(new FileTypeFilter(".java","archivos codigo"));
				int result = js.showOpenDialog(null);
			}
		});
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		List list = new List();
		list.setBounds(10, 57, 600, 89);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("Seleccione un archivo de la lista:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 35, 276, 16);
		contentPane.add(lblNewLabel);
		
		List list_1 = new List();
		list_1.setBounds(10, 172, 276, 135);
		contentPane.add(list_1);
		
		List list_2 = new List();
		list_2.setBounds(292, 172, 320, 135);
		contentPane.add(list_2);
		
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 349, 750, 141);
		contentPane.add(textArea);
	}
}
