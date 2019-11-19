package es.uca.gii.csi19.distrito.gui;

import java.util.ArrayList;

import es.uca.gii.csi19.distrito.data.Juego;

public class JuegosTableModel extends javax.swing.table.AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Juego> _aData;
	
	public JuegosTableModel(ArrayList<Juego> aData) {
		_aData = aData;
	}
	
	public Juego getData(int iRow) {
		return _aData.get(iRow);
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return _aData.size();
	}

	@Override 
	public Object getValueAt(int iRow, int iCol) {
		// TODO Auto-generated method stub
		Juego juego = _aData.get(iRow);
		
		if(iCol == 0)
			return juego.getCodigo();
		
		return juego.getNParticipantes();
	}

}
