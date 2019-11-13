package es.uca.gii.csi19.distrito.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmMain() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Juegos del hambre");
		frame.setBounds(50, 50, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mitNuevo = new JMenu("Nuevo");
		menuBar.add(mitNuevo);
		
		JMenuItem mitNuevoJuego = new JMenuItem("Juego");
		mitNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IfrJuego ifrJuego = new IfrJuego();
				ifrJuego.setBounds(0, 0, 350, 200);
				frame.getContentPane().add(ifrJuego);
				ifrJuego.setVisible(true);
			}
		});
		mitNuevo.add(mitNuevoJuego);
		
		JMenu mitBuscar = new JMenu("Buscar");
		menuBar.add(mitBuscar);
		
		JMenuItem mitBuscarJuego = new JMenuItem("Juego");
		mitBuscarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IfrJuegos ifrJuegos = new IfrJuegos();
				ifrJuegos.setBounds(0, 0, 350, 200);
				frame.getContentPane().add(ifrJuegos, 0);
				ifrJuegos.setVisible(true);
			}
		});
		mitBuscar.add(mitBuscarJuego);
		frame.getContentPane().setLayout(null);
	}

}
