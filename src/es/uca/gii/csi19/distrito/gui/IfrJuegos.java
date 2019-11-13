package es.uca.gii.csi19.distrito.gui;


import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.uca.gii.csi19.distrito.data.Juego;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrJuegos extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtParticipantes;
	private JTable tabResult;
	/**
	 * Create the frame.
	 */
	public IfrJuegos() {
		setResizable(true);
		setClosable(true);
		setTitle("Juegos");
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		panel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblParticipantes = new JLabel("Participantes");
		panel.add(lblParticipantes);
		
		txtParticipantes = new JTextField();
		panel.add(txtParticipantes);
		txtParticipantes.setColumns(10);
		
		JButton butBuscar = new JButton("Buscar");
		butBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Integer iNParticipantes = null;
					if(!txtParticipantes.getText().isEmpty())
						iNParticipantes = Integer.parseInt(txtParticipantes.getText());
					tabResult.setModel(
							new JuegosTableModel(Juego.Select(txtCodigo.getText(), iNParticipantes)));
				}
				catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Error al buscar", "ERROR", JOptionPane.ERROR_MESSAGE);
				}			
				
			}
		});
		panel.add(butBuscar);
		
		tabResult = new JTable();
		getContentPane().add(tabResult, BorderLayout.CENTER);

	}

}
