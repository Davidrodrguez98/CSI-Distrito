package es.uca.gii.csi19.distrito.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoMapa {
	private int _iId;
	private String _sNombre;
	
	public int getId() {
		return _iId;
	}

	public String getNombre() {
		return _sNombre;
	}
	
	public void set_sNombre(String sNombre) {
		_sNombre = sNombre;
	}
	
	/**
	 * Constructor de la clase TipoMapa
	 * @param iId
	 * @throws Exception
	 */
	public TipoMapa (int iId) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT nombre FROM TipoMapa WHERE id = " + iId);
			
			rs.next();
			_iId = iId;
			_sNombre = rs.getString("nombre");
			
		}
		catch (SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * Método Select de la clase TipoMapa
	 * @return ArrayList<TipoMapa>
	 * @throws Exception
	 */
	public static ArrayList<TipoMapa> Select() throws Exception {
		ArrayList<TipoMapa> aResultado = new ArrayList<TipoMapa>();
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT id, nombre FROM TipoMapa ORDER BY nombre");
			
			while(rs.next())
				aResultado.add(new TipoMapa(rs.getInt("id")));
			
			return aResultado;
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * Procedimiento para mostrar información en tipo String de un objeto TipoMapa 
	 * @return String con el nombre del tipo de mapa
	 */
	public String toString () {
		return _sNombre;
	}
	
}
