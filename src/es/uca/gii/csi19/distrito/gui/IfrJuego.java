package es.uca.gii.csi19.distrito.gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.uca.gii.csi19.distrito.data.Juego;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrJuego extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtNumeroDeParticipantes;
	private Juego _juego = null;

	/**
	 * Create the frame.
	 */
	public IfrJuego() {
		setResizable(true);
		setClosable(true);
		setTitle("Juego");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 11, 46, 14);
		getContentPane().add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(10, 29, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNumeroDeParticipantes = new JLabel("N\u00FAmero de participantes");
		lblNumeroDeParticipantes.setBounds(10, 60, 131, 14);
		getContentPane().add(lblNumeroDeParticipantes);
		
		txtNumeroDeParticipantes = new JTextField();
		txtNumeroDeParticipantes.setBounds(10, 78, 86, 20);
		getContentPane().add(txtNumeroDeParticipantes);
		txtNumeroDeParticipantes.setColumns(10);
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (_juego == null)
						_juego = Juego.Create(txtCodigo.getText(), Integer.parseInt(txtNumeroDeParticipantes.getText()));
					else {
						_juego.setCodigo(txtCodigo.getText());
						_juego.setNParticipantes(Integer.parseInt(txtNumeroDeParticipantes.getText()));
						_juego.Update();
					}
				}
				catch(NumberFormatException eformato) {
					JOptionPane.showMessageDialog(null, "Debe introducir un número",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception ee) { 
					JOptionPane.showMessageDialog(null, 
							"Ha habido un problema en la aplicación. Compruebe que ha rellenado todas las casillas",
							"Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		butGuardar.setBounds(30, 120, 89, 23);
		getContentPane().add(butGuardar);

	}
}
