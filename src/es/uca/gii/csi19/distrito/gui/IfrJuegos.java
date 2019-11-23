package es.uca.gii.csi19.distrito.gui;


import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.uca.gii.csi19.distrito.data.Juego;
import es.uca.gii.csi19.distrito.data.TipoMapa;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class IfrJuegos extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtParticipantes;
	private JTable tabResult;
	private Container pnlParent;
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public IfrJuegos(JFrame container) throws Exception {
		JComboBox<TipoMapa> cmbTipoMapa = new JComboBox<TipoMapa>();
		pnlParent = container;
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
					String sTipoMapa = null;
					String sCodigo = null;
					Integer iNParticipantes = null;
					
					if(cmbTipoMapa.getSelectedItem() != null)
						sTipoMapa = cmbTipoMapa.getSelectedItem().toString();
					
					if (!txtCodigo.getText().isEmpty())
						sCodigo = txtCodigo.getText();
						
					if(!txtParticipantes.getText().isEmpty())
						iNParticipantes = Integer.parseInt(txtParticipantes.getText());
					
						tabResult.setModel(new JuegosTableModel(
							Juego.Select(sTipoMapa, sCodigo, iNParticipantes)));
					
				}
				catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Error al buscar", "ERROR", JOptionPane.ERROR_MESSAGE);
				}			
			
			}
		});
		
		JLabel lblTipoMapa = new JLabel("Tipo Mapa");
		panel.add(lblTipoMapa);
		
		cmbTipoMapa.setModel(new TipoMapaListModel(TipoMapa.Select()));
		cmbTipoMapa.setEditable(true);
		panel.add(cmbTipoMapa);
		panel.add(butBuscar);
		
		tabResult = new JTable();
		tabResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int iRow = ((JTable)e.getSource()).getSelectedRow();
					Juego juego = ((JuegosTableModel)tabResult.getModel()).getData(iRow);
					if (juego != null) {
						IfrJuego ifrJuego = null;
						try {
							ifrJuego = new IfrJuego(juego);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Error en la aplicación", "Error", JOptionPane.ERROR_MESSAGE);
						}
						ifrJuego.setBounds(10, 27, 244, 192);
						pnlParent.add(ifrJuego, 0);
						ifrJuego.setVisible(true);
					}
				}
			}
		});
		getContentPane().add(tabResult, BorderLayout.CENTER);

	}

}
