package es.uca.gii.csi19.distrito.gui;

import java.util.List;

import javax.swing.*;

import es.uca.gii.csi19.distrito.data.TipoMapa;

public class TipoMapaListModel extends AbstractListModel<TipoMapa> implements ComboBoxModel<TipoMapa> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TipoMapa> _aData;
	private Object _oSelectedItem = null;
	
	public List<TipoMapa> getData() {
		return _aData;
	}

	public void setData(List<TipoMapa> aData) {
		_aData = aData;
	}

	public Object getSelectedItem() {
		return _oSelectedItem;
	}

	public void setSelectedItem(Object oSelectedItem) {
		_oSelectedItem = oSelectedItem;
	}

	@Override
	public TipoMapa getElementAt(int iIndex) {
		// TODO Auto-generated method stub
		return _aData.get(iIndex);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return _aData.size();
	}
	
	public TipoMapaListModel (List<TipoMapa> aData) {
		_aData = aData;
	}

}
