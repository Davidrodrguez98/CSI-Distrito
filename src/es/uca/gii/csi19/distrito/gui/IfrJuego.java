package es.uca.gii.csi19.distrito.gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.uca.gii.csi19.distrito.data.Juego;
import es.uca.gii.csi19.distrito.data.TipoMapa;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

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
	 * @throws Exception 
	 */
	public IfrJuego(Juego juego) throws Exception {	
		JComboBox<TipoMapa> cmbTipoMapa = new JComboBox<TipoMapa>();
		
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
		
		_juego = juego;

		if(_juego != null) {
			cmbTipoMapa.setSelectedItem(_juego.getTipoMapa());
			txtCodigo.setText(_juego.getCodigo());
			txtNumeroDeParticipantes.setText(String.valueOf(_juego.getNParticipantes()));
		}	
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (_juego == null)
						_juego = Juego.Create((TipoMapa) cmbTipoMapa.getModel().getSelectedItem(), txtCodigo.getText(), Integer.parseInt(txtNumeroDeParticipantes.getText()));
					else {
						if ((TipoMapa) cmbTipoMapa.getModel().getSelectedItem() != null)
							_juego.setTipoMapa((TipoMapa) cmbTipoMapa.getModel().getSelectedItem());
						else 
							throw new NullPointerException();
						
						_juego.setCodigo(txtCodigo.getText());
						_juego.setNParticipantes(Integer.parseInt(txtNumeroDeParticipantes.getText()));
						_juego.Update();
					}
				}
				catch(NullPointerException eNulo) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de mapa",
							"Error", JOptionPane.ERROR_MESSAGE);
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
		butGuardar.setBounds(232, 129, 89, 23);
		getContentPane().add(butGuardar);
		
		JLabel lblTipoDeMapa = new JLabel("Tipo de mapa");
		lblTipoDeMapa.setBounds(10, 109, 86, 14);
		getContentPane().add(lblTipoDeMapa);
		
		
		cmbTipoMapa.setModel(new TipoMapaListModel(TipoMapa.Select()));
		cmbTipoMapa.setBounds(10, 130, 86, 20);
		getContentPane().add(cmbTipoMapa);

	}
}
