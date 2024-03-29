package es.uca.gii.csi19.distrito.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.Integer;

import es.uca.gii.csi19.distrito.data.Data;

public class Juego {
	private TipoMapa _tipoMapa;
	private String _sCodigo;
	private int _iNParticipantes, _iId;
	private boolean _bIsDeleted = false;
	
	public TipoMapa getTipoMapa() {
		return _tipoMapa;
	}

	public void setTipoMapa(TipoMapa tipoMapa) {
		_tipoMapa = tipoMapa;
	}

	public int getId() {
		return _iId;
	}
	
	public String getCodigo() {
		return _sCodigo;
	}

	public void setCodigo(String sCodigo) throws Exception {
		if (sCodigo.equals(""))
			throw new Exception("El c�digo es obligatorio");
		_sCodigo = sCodigo;
	}

	public int getNParticipantes() {
		return _iNParticipantes;
	}

	public void setNParticipantes(int iNParticipantes) {
		_iNParticipantes = iNParticipantes;
	}
	
	public boolean getIsDeleted() {
		return _bIsDeleted;
	}

	/**
	 * Constructor de la clase Juego.
	 * @param iId 
	 * @throws Exception cuando hay un error en la conexi�n con la base de datos.
	 */
	//TODO: Precondition: debe existir un Juego con c�digo sCodigo en la base de datos
	public Juego (int iId) throws Exception{
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT Id_TipoMapa, codigo, nParticipantes"
					+ " FROM Juego WHERE id = " + iId);
			
			rs.next();
			_iId = iId;
			_tipoMapa = new TipoMapa(rs.getInt("Id_TipoMapa"));
			_sCodigo = rs.getString("codigo");
			_iNParticipantes = rs.getInt("nParticipantes");
		}
		catch (SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
		
	}

	/**
	 * Procedimiento para mostrar informaci�n en tipo String de un objeto Juego.
	 * @return String con la informaci�n del juego.
	 */
	public String toString () {
		return super.toString() + ":" + _iId + ":" + _tipoMapa.toString() + ":" + _sCodigo + ":" + _iNParticipantes;
	}
	
	/**
	 * Crea una instancia de Juego.
	 * @param tipoMapa
	 * @param sCodigo
	 * @param iNParticipantes
	 * @return una instancia de Juego.
	 * @throws Exception
	 */
	//TODO: Precondition: no debe existir un Juego con c�digo igual a sCodigo ni nParticipantes igual a iNParticipantes.
	public static Juego Create (TipoMapa tipoMapa, String sCodigo, int iNParticipantes) throws Exception {
		Connection con = null;
		
		try {
			if (sCodigo.equals("")) 
				throw new Exception("El c�digo es obligatorio");
			
			con = Data.Connection();
			con.createStatement().executeUpdate(String.format("INSERT INTO Juego (Id_TipoMapa, codigo, nParticipantes) VALUES (%d, %s, %d)", tipoMapa.getId(), Data.String2Sql(sCodigo, true, false), iNParticipantes));
			
			return new Juego(Data.LastId(con));
		}
		catch (SQLException ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * @return n�mero de juegos existentes actualmente en la base de datos.
	 * @throws Exception
	 */
	public static int Size() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT COUNT(id) as nJuegos FROM Juego");
			
			rs.next();
			return rs.getInt("nJuegos");
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * Elimina un registro de la base de datos.
	 * @throws Exception
	 */
	public void Delete() throws Exception {
		Connection con = null;
		
		if(_bIsDeleted)
			throw new Exception("El juego " + _sCodigo + " ya ha sido eliminado");
		try {
			con = Data.Connection();
			con.createStatement().executeUpdate("DELETE FROM Juego WHERE id = " + _iId);
			_bIsDeleted = true;
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * Modifica un registro de la base de datos.
	 * @throws Exception
	 */
	public void Update() throws Exception {
		Connection con = null;
		
		if(_bIsDeleted)
			throw new Exception("El juego " + _sCodigo + " est� eliminado");
		try {
			con = Data.Connection();
			con.createStatement().executeUpdate(String.format("UPDATE Juego SET Id_TipoMapa = %d, codigo = %s, nParticipantes = %d WHERE id = %d", _tipoMapa.getId(), Data.String2Sql(_sCodigo, true, false), _iNParticipantes, _iId));
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * Selecciona los registros que coinciden con un cierto criterio de b�squeda
	 * @param sTipoMapa
	 * @param sCodigo
	 * @param iNParticipantes
	 * @return ArrayList de Juego 
	 * @throws Exception
	 */
	public static ArrayList<Juego> Select(String sTipoMapa, String sCodigo, Integer iNParticipantes) throws Exception {
		ArrayList<Juego> aResultado = new ArrayList<Juego>();
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			//System.out.print(b);
			rs = con.createStatement().executeQuery("SELECT Juego.id id FROM Juego JOIN tipomapa ON juego.Id_TipoMapa = tipomapa.id "
					+ Where(sTipoMapa, sCodigo, iNParticipantes));
			
			while(rs.next())
				aResultado.add(new Juego(rs.getInt("id")));
			
			return aResultado;
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * Genera la condici�n o condiciones de b�squeda de la cl�usula WHERE
	 * @param sTipoMapa
	 * @param sCodigo
	 * @param iNParticipantes
	 * @return String
	 */
	private static String Where(String sTipoMapa, String sCodigo, Integer iNParticipantes) {
		String sResultado = "WHERE";
		
		if(sTipoMapa != null)
			sResultado += " tipomapa.nombre LIKE " + Data.String2Sql(sTipoMapa, true, true) + " AND ";
		if(sCodigo != null)
			sResultado += " codigo LIKE " + Data.String2Sql(sCodigo, true, true) + " AND ";
		if(iNParticipantes != null)
			sResultado += " nParticipantes = " + iNParticipantes.intValue() + " AND ";
		return sResultado.substring(0, sResultado.length() - 5);
	}
	
}
